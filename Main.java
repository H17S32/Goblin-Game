// Goblin game
// Created 6/1/2024
package Final2;

import java.util.*;
import java.text.*;;
public class Main {
	public static void main(String[] args) {
		DecimalFormat Format = new DecimalFormat();
		boolean Play = true;
		double Level = 0;
		while (Play) {
			Code Extender = new Code();
			double Muti = Level/2*5;
			// TOOLS
			Extender.CreateWeapon("Sharp Sword", "A bright sword that brings power.", 10+Muti);
			Extender.CreateWeapon("Wand", "A wooden stick holding a orange rock that throws fire.", 6+Muti);
			Extender.CreateWeapon("Spear", "A spear used in wars.", 10+Muti);
			Extender.CreateWeapon("Crossbow", "A powerful bow that shoots arrows", 10+Muti);
			Extender.CreateWeapon("Axe", "Axe used by vikings long ago", 13+Muti);
			Extender.CreateWeapon("Claymore", "A normal sword", 11+Muti);
			Extender.CreateWeapon("Heavy sword", "A huge sword that skips a turn every swing", 15+Muti);
			//
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("<{###############################}> [!] GOBLIN SLAYER v2.0 [!] <{###############################}>");
			System.out.println("--------------------------------------------------------------------------------------------------");

			System.out.println("-> [!] Level: " +Format.format(Level));
			System.out.println("CHOOSE A WEAPON: ");
			for (int i = 0; i < Extender.GetArraySize(); i++) {
				if (i >=0 && i <Extender.GetArraySize()) {
					System.out.println("["+(i+1)+"]"+" ---> "+Extender.GetToolname(i)+" <-- "+Extender.GetToolsDisc(i)+"");
				}
			}
			System.out.print("-> Choose Weapon: ");
			Scanner Input = new Scanner(System.in);
			Scanner Input2 = new Scanner(System.in);
			int ToolIndex = Input.nextInt();
			if (ToolIndex >Extender.GetArraySize()) {
				System.out.println("[!] PROGRAM ERROR: INVALID WEAPON");
				System.exit(0);
			}
			int GameRun = 1;
			Game Game = new Game(100,5); //health +damage
			int RareType = new Random().nextInt(8);
			if (RareType == 0) {
				RareType = 5;
			}
			ToolIndex=ToolIndex-1;
			Game.SetDamage(Extender.GetToolsdamage(ToolIndex));
			System.out.println("------------------------------------------------------------------------");
			System.out.println("-->[!] YOUR STATS:\n--> Health: "+Format.format(Game.PlayerHealth)+"\n--> Damage: "+Format.format(Game.PlayerDamage));
			System.out.println("------------------------------------------------------------------------");
			if (RareType == 2) {
				System.out.println("[!] A RARE BUFF GOBLIN SPAWNED!");
				Game.SetUpGoblin(300, 25); //buff goblin
			} else if (RareType == 3) {
				System.out.println("[!] A strong goblin came to fight you!");
				Game.SetUpGoblin(150, 23); //strong goblin
			}else if (RareType == 4) {
				System.out.println("[!] A weak zombie goblin has spawned!");
				Game.SetUpGoblin(50, 5); //zombie goblin
			}else if (RareType == 5) { // LORD OF GOBLINS
				System.out.println("[!] OH NO ITS THE LORD OF GOBLINS!!! (HARDEST BOSS)");
				Game.SetUpGoblin(1000, 30); //LORD goblin
			}else {
				System.out.println("[!] A goblin has came to fight you!");
				Game.SetUpGoblin(100, 15); //goblin
			}
			
			//TOOL AND STUFF 
			double DealDamage = Extender.ToolDamages.get(ToolIndex); // damage
			// MAIN CODE (game)
			int SKIPTURN = 0;
			int RanTimes = 0;
			int UsedShieldMax = 3;
			int UsedFreezeMax = 5;
			while (GameRun == 1) {
				RanTimes=RanTimes+1;
				boolean Freeze = false;
				if (Game.GoblinHealth <=1) {
					System.out.println("############################## -<< YOU WIN! >>- ##############################");
					Level = Level+1;
					System.out.println("[!] YOU HAVE LEVELED UP TO "+Format.format(Level));
					System.out.println("Restart program to try again.");
					break;
				}
				if (Game.GetHealth() <=1) {
					System.out.println("[!] You died!");	
					System.out.println("   _____");
					System.out.println("  /     \\");
					System.out.println("  |() ()|");
					System.out.println("  \\  ^  /");
					System.out.println("   ||||| ");
					System.out.println("   ||||| ");
					break;
				}
				System.out.println("CHOOSE A ACTION: ");
				System.out.println("[1] --> USE SHIELD");
				System.out.println("[2] --> USE WEAPON");
				System.out.println("[3] --> USE HEAL SPELL");
				System.out.println("[4] --> FREEZE ATTACK SPELL"); // PROGRAM PROGRAM PROGRAM
				int Choosen = Input.nextInt();
				int IsDefendGoblin = new Random().nextInt(4);
				int DoAttack = new Random().nextInt(3);
				boolean DoBlock = false;
				////////-------------------------
				if (SKIPTURN >=1) {
					System.out.println("[!] Your turn has been skipped.");
				}
				//------------------------------------------
				if (SKIPTURN >1) {
					System.out.println("SKIIP");
				}
				if (SKIPTURN <1) {
					switch(Choosen) { // PLAYER ACTIONS SWITCH
					case 1:{ //BLOCK
						if (UsedShieldMax >0) {
							UsedShieldMax = UsedShieldMax-1;
							if (UsedShieldMax<=0) {
								System.out.println("[!] Your shield has broke!");
							}
							DoBlock = true;
							break;
						} else {
							System.out.println("[!] You can't use shield (Shield broke) ");
						}
					}break;
					case 2:{ //ATTACK
						if (IsDefendGoblin != 1) {
							if (Extender.GetToolname(ToolIndex) == "Heavy Sword") {
								SKIPTURN = SKIPTURN+1;
							}
							Game.TakeGoblinDamage(DealDamage);
						} else {
							System.out.println("The goblin has blocked your attack!");
						}
						break;
					}
					case 3:{ //HEAL
						Game.UsePotion();
					}break;
					case 4:{ //FREEZE
						if (UsedFreezeMax >0) {
							Freeze = true;
							UsedFreezeMax=UsedFreezeMax-1;
							System.out.println("[!] you have frooze the goblin!");
						} else {
							System.out.println("[!] You freeze attack spell ran out of energy!");
						}
					}break;
				}
				//---------
					if (Freeze == true) {
						System.out.println("You froozen the goblin and he could not attack!");
					} else {
						if (DoAttack == 1 && IsDefendGoblin != 1) { // GOBLIN ATTACKS
							if (DoBlock== false) {
								Game.TakeDamage(Game.GetGoblinDamage());
							} else {
								System.out.println("[!] The goblin tried to attack but your shield stopped it!");
							}
						}
					}
			}
			}
			System.out.println("--> # DO YOU WANT TO PLAY AGAIN? [Y/N]");
			Game.PlayerDamage = 0;
			Game.PlayerHealth = 0;
			Game.GoblinDamage = 0;
			Game.GoblinHealth = 0;
			Game.PotionsLeft = 3;
			String Choosen2 = Input2.nextLine();
			switch(Choosen2.toLowerCase()) {
				case "n": {
					System.out.println("--> # See you later!");
					Play = false;
				}break;
				case "y": {
					System.out.println("--> # You leveled up! (Each level you get more health and damage");
				}break;
				default: {
					Play = false;
					System.out.println("[!] Error! invalid response.");
				}
			}
		}

	}
}
class Code extends Main {
	ArrayList<Double> ToolDamages = new ArrayList<>();
	ArrayList<String> Tools = new ArrayList<>();
	ArrayList<String> ToolsDisc = new ArrayList<>();
	public int GetArraySize() {
		return Tools.size();
	}
	public String GetToolname(int i) {
		return Tools.get(i);
	}
	public Double GetToolsdamage(int i) {
		return ToolDamages.get(i);
	}
	public String GetToolsDisc(int i) {
		return ToolsDisc.get(i);
	}
	public void CreateWeapon(String Name, String Disc, double Damage) {
		Tools.add(Name);
		ToolsDisc.add(Disc);
		ToolDamages.add(Damage);
	}
}
