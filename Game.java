package Final2;

import java.util.*;

public class Game {
	double PlayerHealth = 0;
	double PlayerDamage = 0;
	double GoblinHealth = 0;
	double GoblinDamage = 0;
	int PotionsLeft = 3;
	public Game(double health, double damage) {
		PlayerHealth = health;
		PlayerDamage = damage;
	}
	public void SetUpGoblin(double Health,double Damage) {
		GoblinDamage = Damage;
		GoblinHealth = Health;
	}
	void SetDamage(double a) {
		PlayerDamage = a;
	}
	double GetHealth() {
		return PlayerHealth;
	}
	public double GetGoblinDamage() {
		return GoblinDamage;
	}
	public void TakeGoblinDamage(double s) {
		GoblinHealth = GoblinHealth-s;
		System.out.println("------------------------------------------------------------------------");
		System.out.println("[!] You damaged the goblin! Damage: "+s+" | Goblins health: "+GoblinHealth);
		System.out.println("------------------------------------------------------------------------");
	}
	public double GoblinHealth() {
		return GoblinHealth;
	}
	public void TakeDamage(double s) {
		PlayerHealth=PlayerHealth-s;
		System.out.println("------------------------------------------------------------------------");
		System.out.println("[!] Goblin attacked you! And you have lost "+s+" health!\nYour health is: "+PlayerHealth);
		System.out.println("------------------------------------------------------------------------");
	}
	public void UsePotion() {
		if (PotionsLeft <=0) {
			System.out.println("[!] --> You have no more potions left!");
		} else {
			PotionsLeft = PotionsLeft-1;
			PlayerHealth=PlayerHealth+10;
			System.out.println("------------------------------------------------------------------------");
			System.out.println("You used a healing potion and you got +10 health! Health: "+PlayerHealth);
			System.out.println("------------------------------------------------------------------------");
		}
	}
}
