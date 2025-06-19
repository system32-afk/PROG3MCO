//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    static int input;
    static Scanner SC = new Scanner(System.in);
    static Armor SelectedArmor = null;
    static Weapon SelectedWeapon = null;
    static Opponent SelectedEnemy = null;
    static Environment SelectedEnvironment = null;
    static String EnemyLastMove = null, PlayerLastMove = null;
    static Character Player = null;

    public static void main(String[] args) {



        //-----------------------Armour SELECTION-----------------------------------


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

         Player = new Character(SelectedArmor,SelectedWeapon);

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


        //-----------------------TURN BASED FIGHTING ALGORITHM-----------------------------------

        System.out.println("==============================================");
        System.out.println("\t\t\t  ⚔\uFE0F FIGHT!!! ⚔\uFE0F");
        System.out.println("==============================================");


            while(Player.GetHP() != 0 || SelectedEnemy.GetHP() != 0){

                System.out.println("\uD83E\uDD3A PLAYER: ❤\uFE0E "+ Player.GetHP());
                System.out.println("\\uD83D\uDC79 ENEMY: ❤\uFE0E "+ SelectedEnemy.GetHP());
                /*
                * If player is faster than the Enemy
                * He is prompted first
                * */
                if (Player.GetSpeed() > SelectedEnemy.GetSpeed()){

                    while(Player.GetHP() != 0 || SelectedEnemy.GetHP() != 0){
                        PlayerMoves();
                        EnemyMoves();
                        SelectedEnvironment.PenalizeEntity(Player, SelectedEnemy);
                    }

                }else{
                    EnemyMoves();
                    PlayerMoves();
                    SelectedEnvironment.PenalizeEntity(Player, SelectedEnemy);
                }


            }

            if(Player.GetHP() == 0){
                System.out.println("GAMEOVER... YOU LOST!!");
            }else{
                System.out.println("VICTORY!!!");
            }






    }
    static void PlayerMoves(){
        System.out.println(">> Pick your Action: ");
        System.out.println("1] Attack\n2] Defend\n3] Charge");

        System.out.print("> Your Choice: ");
        input = SC.nextInt();

        do{
            if(input == 1){
                if(EnemyLastMove != "Defend"){
                    Player.Attack(SelectedEnemy,-Player.GetAttack());
                }else{
                    Player.Attack(SelectedEnemy,(Player.GetAttack() - SelectedEnemy.GetDefense()));
                }

                if(PlayerLastMove == "Charge")
                    Player.SetAttack(Player.GetAttack()/3);

                PlayerLastMove = "Attack";
            } else if (input == 2) {

                PlayerLastMove = "Defend";

            } else if (input == 3) {

                if(PlayerLastMove != "Charge"){
                    Player.SetAttack(Player.GetAttack()*3);
                }else{
                    System.out.println("You're already Charged!");
                    input = 0;
                }

                PlayerLastMove = "Charge";
            }


            //Player is prompted again if he's already charged and picks Again
        }while(input == 3 && PlayerLastMove == "Charge");

    }

    static void EnemyMoves(){

        if(SelectedEnemy.Think() == "Attack"){
            if(PlayerLastMove != "Defend"){
                SelectedEnemy.Attack(Player, -SelectedEnemy.GetAttack());
            }else{
                SelectedEnemy.Attack(Player, -(SelectedEnemy.GetAttack() - Player.GetDefense()));
            }

            if(EnemyLastMove == "Charge")
                SelectedEnemy.SetAttack(Player.GetAttack()/3);

            EnemyLastMove = "Attack";
        } else if (SelectedEnemy.Think() == "Defend") {
            EnemyLastMove = "Defend";
        } else if (SelectedEnemy.Think() == "Charge") {
            SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()*3);
            EnemyLastMove = "Charge";

        }
    }


    }
