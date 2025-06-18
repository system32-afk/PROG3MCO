//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {

        int input;
        Scanner SC = new Scanner(System.in);
        Armor SelectedArmor = null;
        Weapon SelectedWeapon = null;
        Opponent SelectedEnemy = null;
        Environment SelectedEnvironment = null;


        //-----------------------Armour SELECTION-----------------------------------
        System.out.println("=======CREATE YOUR LOADOUT=========");
        System.out.println();
        System.out.println("==============================================");
        System.out.println("Armor Selection:");
        System.out.println("1] Light Armor\n2] Medium Armor\n3] Heavy armor");
        System.out.println("==============================================");
        System.out.print("Select your armor (1-3): ");
        input = SC.nextInt();

        if(input == 1){
             SelectedArmor = new Armor("Light");
        } else if (input==2) {
             SelectedArmor = new Armor("Medium");
        } else if (input == 3) {
             SelectedArmor = new Armor("Heavy");
        }


        //-----------------------WEAPON SELECTION-----------------------------------
        System.out.println("==============================================");
        System.out.println("Weapon Selection:");
        System.out.println("1] Dagger\n2] Sword\n3] Battle Axe");
        System.out.println("==============================================");
        System.out.print("Select your weapon (1-3): ");
        input = SC.nextInt();

        if(input == 1){
            SelectedWeapon = new Weapon("Dagger");
        } else if (input==2) {
            SelectedWeapon = new Weapon("Sword");
        } else if (input == 3) {
            SelectedWeapon = new Weapon("Battle Axe");
        }

        Character Player = new Character(SelectedArmor,SelectedWeapon);

        //-----------------------ENEMY SELECTION-----------------------------------
        System.out.println("=======SELECT YOUR ENEMY=========");
        System.out.println();
        System.out.println("==============================================");
        System.out.println("1] Thief\n2] Viking\n3] Minotaur");
        System.out.println("==============================================");
        System.out.print("Select your weapon (1-3): ");
        input = SC.nextInt();
        if(input == 1){
            SelectedEnemy = new Opponent("Thief",Player);
        } else if (input==2) {
            SelectedEnemy = new Opponent("Viking",Player);
        } else if (input == 3) {
            SelectedEnemy = new Opponent("Minotaur",Player);
        }

        Player.SetOpponent(SelectedEnemy); //Assigns the Selected Enemy as the Player's Opponent

        //-----------------------ENVIRONMENT SELECTION-----------------------------------
        System.out.println("=======SELECT YOUR ENVIRONMENT=========");
        System.out.println();
        System.out.println("==============================================");
        System.out.println("1] Arena\n2] Swamp\n3] Colosseum");
        System.out.println("==============================================");
        System.out.print("Select your weapon (1-3): ");
        input = SC.nextInt();
        if(input == 1){
            SelectedEnvironment = new Environment("Arena");
        } else if (input==2) {
            SelectedEnvironment = new Environment("Swamp");
        } else if (input == 3) {
            SelectedEnvironment = new Environment("Colosseum");
        }



        }
    }
