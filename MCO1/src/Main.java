import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static char input;
    static Scanner SC = new Scanner(System.in);
    static Armor SelectedArmor = null;
    static Weapon SelectedWeapon = null;
    static Opponent SelectedEnemy = null;
    static Environment SelectedEnvironment = null;
    static String EnemyChoice = null, PlayerChoice = null;
    static Character Player = null;
    static int GameOver = 0;

    public static void main(String[] args) throws InterruptedException {

        //-----------------------Armour SELECTION-----------------------------------
        System.out.println("=======CREATE YOUR LOADOUT=========");
        System.out.println();
        do {
            System.out.println("==============================================");
            System.out.println("Armor Selection:");
            System.out.println("1] Light Armor\n2] Medium Armor\n3] Heavy armor");
            System.out.println("==============================================");
            System.out.print("Select your armor (1-3): ");
            input = SC.next().charAt(0);
            if(!isInputValid(input)) {
                System.out.println("Invalid input... Please try again.");
                TimeUnit.SECONDS.sleep(1);
            }

        }while (!isInputValid(input));


        if(input == '1'){
            SelectedArmor = new Armor("Light");
        } else if (input== '2') {
            SelectedArmor = new Armor("Medium");
        } else if (input == '3') {
            SelectedArmor = new Armor("Heavy");
        }


        //-----------------------WEAPON SELECTION-----------------------------------
        System.out.println();
        System.out.println();
        do {
            System.out.println("==============================================");
            System.out.println("Weapon Selection:");
            System.out.println("1] Dagger\n2] Sword\n3] Battle Axe");
            System.out.println("==============================================");
            System.out.print("Select your weapon (1-3): ");
            input = SC.next().charAt(0);
            if(!isInputValid(input)) {
                System.out.println("Invalid input... Please try again.");
                TimeUnit.SECONDS.sleep(1);
            }
        }while(!isInputValid(input));

        if(input == '1'){
            SelectedWeapon = new Weapon("Dagger");
        } else if (input=='2') {
            SelectedWeapon = new Weapon("Sword");
        } else if (input == '3') {
            SelectedWeapon = new Weapon("Battle Axe");
        }

        Player = new Character(SelectedArmor,SelectedWeapon);

        //-----------------------ENEMY SELECTION-----------------------------------
        System.out.println();
        System.out.println();
        do {
            System.out.println();
            System.out.println();
            System.out.println("=======SELECT YOUR ENEMY=========");
            System.out.println();
            System.out.println("==============================================");
            System.out.println("1] Thief\n2] Viking\n3] Minotaur");
            System.out.println("==============================================");
            System.out.print("Select your weapon (1-3): ");
            input = SC.next().charAt(0);

            if(!isInputValid(input)) {
                System.out.println("Invalid input... Please try again.");
                TimeUnit.SECONDS.sleep(1);
            }

        }while(!isInputValid(input));

        if(input == '1'){
            SelectedEnemy = new Opponent("Thief",Player);
        } else if (input=='2') {
            SelectedEnemy = new Opponent("Viking",Player);
        } else if (input == '3') {
            SelectedEnemy = new Opponent("Minotaur",Player);
        }

        Player.SetOpponent(SelectedEnemy); //Assigns the Selected Enemy as the Player's Opponent

        //-----------------------ENVIRONMENT SELECTION-----------------------------------
        System.out.println();
        System.out.println();
        do {
            System.out.println("=======SELECT YOUR ENVIRONMENT=========");
            System.out.println();
            System.out.println("==============================================");
            System.out.println("1] Arena\n2] Swamp\n3] Colosseum");
            System.out.println("==============================================");
            System.out.print("Select your weapon (1-3): ");
            input = SC.next().charAt(0);

            if(!isInputValid(input)) {
                System.out.println("Invalid input... Please try again.");
                TimeUnit.SECONDS.sleep(1);
            }

        }while(!isInputValid(input));
        if(input == '1'){
            SelectedEnvironment = new Environment("Arena");
        } else if (input=='2') {
            SelectedEnvironment = new Environment("Swamp");
        } else if (input == '3') {
            SelectedEnvironment = new Environment("Colosseum");
        }


        //-----------------------TURN BASED FIGHTING ALGORITHM-----------------------------------

        System.out.println("==============================================");
        System.out.println("\t\t\t  ⚔\uFE0F FIGHT!!! ⚔\uFE0F");
        System.out.println("==============================================");



        while(GameOver == 0){
            DisplayFight();

            Player.Defend(false);
            SelectedEnemy.Defend(false);

            PlayerChoice = PlayerMoves();
            EnemyChoice = SelectedEnemy.Think();

            if(PlayerChoice.equals("Defend")){
                Player.Defend(true);
                System.out.println("You raised your defense!");
            }

            if(EnemyChoice.equals("Defend")){
                SelectedEnemy.Defend(true);
                System.out.println("Enemy chose defense");
            }else if(EnemyChoice.equals("Charge")){
                SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()*3);
                System.out.println("The Enemy Charged! their next attack will deal "+SelectedEnemy.GetAttack());
            }

            //Player Moves first
            if(Player.GetSpeed() > SelectedEnemy.GetSpeed()){

                if(PlayerChoice.equals("Attack")){

                    PlayerAttack();

                    GameOver = isGameOver();

                } else if (PlayerChoice.equals("Charge")) {
                    System.out.println("You buffed your attack! Your next attack will deal "+ Player.GetAttack() );
                }

                TimeUnit.SECONDS.sleep(2); //pause for 2 seconds

                if (EnemyChoice.equals("Attack")){
                    EnemyAttack();

                    GameOver = isGameOver();

                } else if (EnemyChoice.equals("Charge")) {
                    System.out.println("Enemy chose to Charge! their next attack will deal "+(SelectedEnemy.GetAttack()*3));
                    SelectedEnemy.Charge(true);
                    SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()*3 );
                }

                ClearScreen();

            }

            //Enemy moves first
            if(Player.GetSpeed() < SelectedEnemy.GetSpeed()){

                if(EnemyChoice.equals("Attack")){
                    EnemyAttack();

                    GameOver = isGameOver();

                } else if (EnemyChoice.equals("Charge")) {
                    System.out.println("Enemy chose to Charge! their next attack will deal "+(SelectedEnemy.GetAttack()*3));
                    SelectedEnemy.Charge(true);
                    SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()*3 );
                }

                TimeUnit.SECONDS.sleep(2);

                if(PlayerChoice.equals("Attack")){
                    PlayerAttack();

                    GameOver = isGameOver();

                } else if (PlayerChoice.equals("Charge")) {
                    System.out.println("You buffed your attack! Your next attack will deal "+ Player.GetAttack() );
                }

                ClearScreen();

            }


        }

        if(GameOver == 1){
            System.out.println("GAMEOVER!!! YOU LOST.");
        }else if (GameOver == 2){
            System.out.println("VICTORY!! YOU WON!");
        }






    }

    static int isGameOver(){
        if(Player.GetHP() < 0){
            return  1; //Player Died. Player lost
        }

        if (SelectedEnemy.GetHP() < 0){
            return 2; //Enemy Died. Player Lost
        }

        return 0; // no winners yet
    }

    static void PlayerAttack(){
        if(SelectedEnemy.GetDefend()){
            SelectedEnemy.SetHP(-((Player.GetAttack() - SelectedEnemy.GetDefense())/2));

            if(((Player.GetAttack() - SelectedEnemy.GetDefense())/2) <= 0){
                System.out.println("The enemy was defended and you dealt no damage!");
            }else{
                System.out.println("The enemy was defended but you dealt "+((Player.GetAttack() - SelectedEnemy.GetDefense())/2));
            }


        }else{
            SelectedEnemy.SetHP(-(Player.GetAttack() - SelectedEnemy.GetDefense()));
            System.out.println("You attacked and dealt "+(Player.GetAttack() - SelectedEnemy.GetDefense()));
        }



        //resets the player's damage after attacking after charging
        if(Player.GetCharge()){
            Player.Charge(false);
            Player.SetAttack(Player.GetAttack()/3);
        }
    }

    static void EnemyAttack(){
        if(Player.isDefended()){
            Player.SetHP(-((SelectedEnemy.GetAttack() - Player.GetDefense())/2));

            if(((SelectedEnemy.GetAttack() - Player.GetDefense())/2) <= 0){
                System.out.println("You were protected and the enemy did no damage!");
            }else {
                System.out.println("You were protected but the enemy managed to deal "+((SelectedEnemy.GetAttack() - Player.GetDefense())/2));
            }

        }else{
            Player.SetHP(-(SelectedEnemy.GetAttack() - Player.GetDefense()));
            System.out.println("Enemy chose to attack! They dealt"+SelectedEnemy.GetAttack());
        }


        //resets the enemy's damage after attacking after charging
        if(SelectedEnemy.GetCharge()){
            SelectedEnemy.Charge(false);
            SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()/3);
        }
    }
    static String PlayerMoves() throws InterruptedException {

        do{
            do {
                System.out.println(">> Pick your Action: ");
                System.out.println("1] Attack\n2] Defend\n3] Charge");
                System.out.print("> Your Choice: ");
                input = SC.next().charAt(0);

                if(!isInputValid(input)) {
                    System.out.println("Invalid input... Please try again.");
                    TimeUnit.SECONDS.sleep(1);
                }

            } while (!isInputValid(input));

            if(input == '1'){

                return  "Attack";

            } else if (input == '2') {
                return "Defend";


            } else if (input == '3') {

                if(!Player.GetCharge()){
                    Player.Charge(true);
                    Player.SetAttack(Player.GetAttack()*3);
                    input = '0';
                    return "Charge";

                }else{
                    System.out.println("You're already Charged!");
                }

            }

        }while(input == '3' && Player.GetCharge());

        return "";
    }

    static void DisplayFight(){
        System.out.println("\uD83E\uDD3A PLAYER:");
        System.out.println("HP ❤\uFE0E: "+ Player.GetHP());
        System.out.println("ATK \uD83D\uDDE1\uFE0F: "+ Player.GetAttack());
        System.out.println("DEF\uD83D\uDEE1\uFE0F: "+ Player.GetDefense());
        System.out.println("SPD ⚡: "+ Player.GetSpeed());
        System.out.println("================== VS =====================");
        System.out.println("\uD83D\uDC79 ENEMY:");
        System.out.println("HP ❤\uFE0E: "+ SelectedEnemy.GetHP());
        System.out.println("ATK \uD83D\uDDE1\uFE0F: "+ SelectedEnemy.GetAttack());
        System.out.println("HP ❤\uFE0E: "+ SelectedEnemy.GetHP());
        System.out.println("DEF\uD83D\uDEE1\uFE0F: "+ SelectedEnemy.GetDefense());
        System.out.println("SPD ⚡: "+ SelectedEnemy.GetSpeed());
    }

    static void ClearScreen() {
        SC.nextLine();
        System.out.print("Press Enter to continue...");

        String buffer = SC.nextLine();
        for(int i = 0; i < 50; i++) {
            System.out.println();
        }
    }



    static boolean isInputValid(char selection){
        int input = (int)selection - '0';

        if (input < 1 || input > 3){
            return false;
        }else{
            return true;
        }
    }
}
