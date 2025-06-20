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
        DisplayFight();
        ClearScreen();

        while(Player.GetHP() != 0 || SelectedEnemy.GetHP() != 0){
            /*
            * If player is faster than the Enemy
            * He is prompted first
            * */
            if (Player.GetSpeed() > SelectedEnemy.GetSpeed()){

                PlayerMoves();
                DisplayFight();
                ClearScreen();
                EnemyMoves();
                SelectedEnvironment.PenalizeEntity(Player, SelectedEnemy);
            }else{

                EnemyMoves();
                ClearScreen();
                DisplayFight();
                PlayerMoves();
                SelectedEnvironment.PenalizeEntity(Player, SelectedEnemy);
            }


        }

        if(Player.GetHP() <= 0){
            System.out.println("GAMEOVER... YOU LOST!!");
        }else{
            System.out.println("VICTORY!!!");
        }






    }
    static void PlayerMoves(){

        do{

            System.out.println(">> Pick your Action: ");
            System.out.println("1] Attack\n2] Defend\n3] Charge");

            System.out.print("> Your Choice: ");
            input = SC.nextInt();

            if(input == 1){

                System.out.println("You attacked!\uD83D\uDDE1\uFE0F \nYou dealt "+ Player.GetAttack()+"!");

                if(EnemyLastMove != "Defend"){
                    Player.Attack(SelectedEnemy,-Player.GetAttack());
                }else{
                    Player.Attack(SelectedEnemy,(Player.GetAttack() - SelectedEnemy.GetDefense()));
                }

                //Resets Player attack stats to base after attacking
                if(PlayerLastMove == "Charge")
                    Player.ResetAttack();

                PlayerLastMove = "Attack";
            } else if (input == 2) {
                System.out.println("You Defend!\uD83D\uDEE1\uFE0F \nThe enemy's next attack will deal "+ Player.GetDefense()+" less Damage!");
                PlayerLastMove = "Defend";

            } else if (input == 3) {

                if(PlayerLastMove != "Charge"){
                    System.out.println("You Charged!\uD83D\uDCAA \nYour attack rose to "+ (Player.GetAttack()*3)+"!");
                    Player.ChargeAttack();
                    input = 0;

                }else{
                    System.out.println("You're already Charged!");
                }

                PlayerLastMove = "Charge";
            }




            //Player is prompted again if he's already charged and picks Again
        }while(input == 3 && PlayerLastMove == "Charge");

    }

    static void EnemyMoves(){

        if(SelectedEnemy.Think() == "Attack"){

            System.out.println(SelectedEnemy.GetType()+" Chose to Attack and dealt "+ SelectedEnemy.GetAttack() +"!\uD83E\uDE78");
            if(PlayerLastMove != "Defend"){
                SelectedEnemy.Attack(Player, -SelectedEnemy.GetAttack());
            }else{
                SelectedEnemy.Attack(Player, -(SelectedEnemy.GetAttack() - Player.GetDefense()));
            }

            if(EnemyLastMove == "Charge")
                SelectedEnemy.SetAttack(Player.GetAttack()/3);

            EnemyLastMove = "Attack";

        } else if (SelectedEnemy.Think() == "Defend") {

            System.out.println(SelectedEnemy.GetType()+" Chose to Defend!\uD83D\uDEE1\uFE0F \nyour next attack will deal "+ SelectedEnemy.GetDefense() +" Less Damage!");
            EnemyLastMove = "Defend";

        } else if (SelectedEnemy.Think() == "Charge") {

            System.out.println("Careful! "+SelectedEnemy.GetType()+" Chose to Charge!\uD83D\uDCAA \nIt's next attack will deal "+SelectedEnemy.GetAttack()*3+"Damage!");
            SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()*3);
            EnemyLastMove = "Charge";

        }
    }

    static void DisplayFight(){
        System.out.println("\uD83E\uDD3A PLAYER:");
        System.out.println("ATK \uD83D\uDDE1\uFE0F: "+ Player.GetAttack());
        System.out.println("HP ❤\uFE0E: "+ Player.GetHP());
        System.out.println("DEF\uD83D\uDEE1\uFE0F: "+ Player.GetDefense());
        System.out.println("SPD ⚡: "+ Player.GetSpeed());
        System.out.println("================== VS =====================");
        System.out.println("\uD83D\uDC79 ENEMY:");
        System.out.println("ATK \uD83D\uDDE1\uFE0F: "+ SelectedEnemy.GetAttack());
        System.out.println("HP ❤\\uFE0E: "+ SelectedEnemy.GetHP());
        System.out.println("DEF\uD83D\uDEE1\uFE0F: "+ SelectedEnemy.GetDefense());
        System.out.println("SPD ⚡: "+ SelectedEnemy.GetSpeed());
    }

    static void ClearScreen() {
        System.out.print("Press Enter to continue");
        String buffer = SC.nextLine();
        for(int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    }
