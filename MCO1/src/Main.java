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
    static int TurnCounter = 1;
    static String playerActionResult;
    static String enemyActionResult;
    static boolean playerFirst;

    public static void main(String[] args) throws InterruptedException {
        // Armor Selection
        System.out.println("=======CREATE YOUR LOADOUT=========");
        System.out.println();
        do {
            System.out.println("==============================================");
            System.out.println("Armor Selection:");
            System.out.println("1] Light Armor\n2] Medium Armor\n3] Heavy armor");
            System.out.println("==============================================");
            System.out.print("Select your armor (1-3): ");
            input = SC.next().charAt(0);
            if (!isInputValid(input)) {
                System.out.println("Invalid input... Please try again.");
                TimeUnit.SECONDS.sleep(1);
            }
        } while (!isInputValid(input));

        if (input == '1') SelectedArmor = new Armor("Light");
        else if (input == '2') SelectedArmor = new Armor("Medium");
        else if (input == '3') SelectedArmor = new Armor("Heavy");

        // Weapon Selection
        System.out.println();
        do {
            System.out.println("==============================================");
            System.out.println("Weapon Selection:");
            System.out.println("1] Dagger\n2] Sword\n3] Battle Axe");
            System.out.println("==============================================");
            System.out.print("Select your weapon (1-3): ");
            input = SC.next().charAt(0);
            if (!isInputValid(input)) {
                System.out.println("Invalid input... Please try again.");
                TimeUnit.SECONDS.sleep(1);
            }
        } while (!isInputValid(input));

        if (input == '1') SelectedWeapon = new Weapon("Dagger");
        else if (input == '2') SelectedWeapon = new Weapon("Sword");
        else if (input == '3') SelectedWeapon = new Weapon("Battle Axe");

        Player = new Character(SelectedArmor, SelectedWeapon);

        // Enemy Selection
        System.out.println();
        do {
            System.out.println("=======SELECT YOUR ENEMY=========");
            System.out.println("==============================================");
            System.out.println("1] Thief\n2] Viking\n3] Minotaur");
            System.out.println("==============================================");
            System.out.print("Select your enemy (1-3): ");
            input = SC.next().charAt(0);
            if (!isInputValid(input)) {
                System.out.println("Invalid input... Please try again.");
                TimeUnit.SECONDS.sleep(1);
            }
        } while (!isInputValid(input));

        if (input == '1') SelectedEnemy = new Opponent("Thief", Player);
        else if (input == '2') SelectedEnemy = new Opponent("Viking", Player);
        else if (input == '3') SelectedEnemy = new Opponent("Minotaur", Player);

        Player.SetOpponent(SelectedEnemy);

        // Environment Selection
        System.out.println();
        do {
            System.out.println("=======SELECT YOUR ENVIRONMENT=========");
            System.out.println("==============================================");
            System.out.println("1] Arena\n2] Swamp\n3] Colosseum");
            System.out.println("==============================================");
            System.out.print("Select your environment (1-3): ");
            input = SC.next().charAt(0);
            if (!isInputValid(input)) {
                System.out.println("Invalid input... Please try again.");
                TimeUnit.SECONDS.sleep(1);
            }
        } while (!isInputValid(input));

        if (input == '1') SelectedEnvironment = new Environment("Arena");
        else if (input == '2') SelectedEnvironment = new Environment("Swamp");
        else if (input == '3') SelectedEnvironment = new Environment("Colosseum");

        // Combat Loop
        System.out.println("==============================================");
        System.out.println("\t\t\t  ⚔️ FIGHT!!! ⚔️");
        System.out.println("==============================================");

        while (GameOver == 0) {
            DisplayFight();

            Player.Defend(false);
            SelectedEnemy.Defend(false);

            PlayerChoice = PlayerMoves();
            EnemyChoice = SelectedEnemy.Think();

             playerActionResult = "";
             enemyActionResult = "";

            if (PlayerChoice.equals("Defend")) {
                Player.Defend(true);
                playerActionResult = "You raised your defense!";
            }
            if (EnemyChoice.equals("Defend")) {
                SelectedEnemy.Defend(true);
                enemyActionResult = "Enemy chose to defend.";
            }

            playerFirst = Player.GetSpeed() >= SelectedEnemy.GetSpeed();

            if (playerFirst) System.out.println("You are faster! You move first.");
            else System.out.println("Enemy is faster! They move first.");

            System.out.println("============== TURN " + TurnCounter++ + " ==============");
            System.out.println("🌿 Environment: " + SelectedEnvironment.getName());
			SelectedEnvironment.PenalizeEntity(Player, SelectedEnemy);

            if (playerFirst) {
                if (!PlayerChoice.equals("Defend")) {
                    if (PlayerChoice.equals("Attack")) {
                        playerActionResult = PlayerAttack();
                        GameOver = isGameOver();
                    } else if (PlayerChoice.equals("Charge")) {
                        playerActionResult = "You buffed your attack! Your next attack will deal " + Player.GetAttack();
                    }
                    System.out.println("\n🤺 You used: " + PlayerChoice);
                    System.out.println("→ " + playerActionResult);
                }

                TimeUnit.SECONDS.sleep(2); // Delays during player mid-fight to look cool.

                if (!EnemyChoice.equals("Defend")) {
                    if (EnemyChoice.equals("Attack")) {
                        enemyActionResult = EnemyAttack();
                        GameOver = isGameOver();
                    } else if (EnemyChoice.equals("Charge")) {
                        SelectedEnemy.Charge(true);
                        SelectedEnemy.SetAttack(SelectedEnemy.GetAttack() * 3);
                        enemyActionResult = "Enemy charged! Next attack will deal " + SelectedEnemy.GetAttack();
                    }
                    System.out.println("\n👹 Enemy used: " + EnemyChoice);
                    System.out.println("→ " + enemyActionResult);
                }
            } else {
                if (!EnemyChoice.equals("Defend")) {
                    if (EnemyChoice.equals("Attack")) {
                        enemyActionResult = EnemyAttack();
                        GameOver = isGameOver();
                    } else if (EnemyChoice.equals("Charge")) {
                        SelectedEnemy.Charge(true);
                        SelectedEnemy.SetAttack(SelectedEnemy.GetAttack() * 3);
                        enemyActionResult = "Enemy charged! Next attack will deal " + SelectedEnemy.GetAttack();
                    }
                    System.out.println("\n👹 Enemy used: " + EnemyChoice);
                    System.out.println("→ " + enemyActionResult);
                }

                TimeUnit.SECONDS.sleep(2);

                if (!PlayerChoice.equals("Defend")) {
                    if (PlayerChoice.equals("Attack")) {
                        playerActionResult = PlayerAttack();
                        GameOver = isGameOver();
                    } else if (PlayerChoice.equals("Charge")) {
                        playerActionResult = "You buffed your attack! Your next attack will deal " + Player.GetAttack();
                    }
                    System.out.println("\n🤺 You used: " + PlayerChoice);
                    System.out.println("→ " + playerActionResult);
                }
            }

            GameOver = isGameOver();
            System.out.println("========================================\n");
            ClearScreen();
        }

        if (GameOver == 1) System.out.println("GAMEOVER!!! YOU LOST.");
        else if (GameOver == 2) System.out.println("VICTORY!! YOU WON!");
    }

    static int isGameOver() {
        if (Player.GetHP() <= 0) return 1;
        if (SelectedEnemy.GetHP() <= 0) return 2;
        return 0;
    }

    static String PlayerAttack() {
        int damage;
        if (SelectedEnemy.GetDefend()) {
            damage = (Player.GetAttack() - SelectedEnemy.GetDefense()) / 2;

            if (damage <= 0){
                //Reset's attack points
                if(Player.isCharged()){
                    Player.Charge(false);
                    Player.SetAttack(Player.GetAttack()/3);
                }
                return "The enemy defended successfully. You dealt no damage!";
            }

            Player.Attack(damage);
            //Reset's attack points
            if(Player.isCharged()){
                Player.Charge(false);
                Player.SetAttack(Player.GetAttack()/3);
            }
            return "The enemy was defended but you dealt " + damage + " damage.";
        } else {
            damage = Player.GetAttack() - SelectedEnemy.GetDefense();
            if (damage <= 0){
                //Reset's attack points
                if(Player.isCharged()){
                    Player.Charge(false);
                    Player.SetAttack(Player.GetAttack()/3);
                }
                return "Your attack didn't penetrate the enemy's defense!";
            }

            Player.Attack(damage);

            //Reset's attack points
            if(Player.isCharged()){
                Player.Charge(false);
                Player.SetAttack(Player.GetAttack()/3);
            }
            return "You attacked and dealt " + damage + " damage.";
        }
    }

    static String EnemyAttack() {
        int damage;
        if (Player.isDefended()) {
            damage = (SelectedEnemy.GetAttack() - Player.GetDefense()) / 2;
            if (damage <= 0){
                //resets attack points
                if(SelectedEnemy.isCharged()){
                    SelectedEnemy.Charge(false);
                    SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()/3);
                }

                return "You blocked the enemy's attack completely!";
            }
            SelectedEnemy.Attack(damage);

            //resets attack points
            if(SelectedEnemy.isCharged()){
                SelectedEnemy.Charge(false);
                SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()/3);
            }
            return "Enemy attacked but you defended, and took " + damage + " damage.";
        } else {
            damage = SelectedEnemy.GetAttack() - Player.GetDefense();
            if (damage <= 0){
                //resets attack points
                if(SelectedEnemy.isCharged()){
                    SelectedEnemy.Charge(false);
                    SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()/3);
                }
                return "Enemy attacked but it had no effect.";
            }
            SelectedEnemy.Attack(damage);
            //resets attack points
            if(SelectedEnemy.isCharged()){
                SelectedEnemy.Charge(false);
                SelectedEnemy.SetAttack(SelectedEnemy.GetAttack()/3);
            }
            return "Enemy attacked and dealt " + damage + " damage.";
        }
    }

    static String PlayerMoves() throws InterruptedException {
        do {
            do {
                System.out.println(">> Pick your Action: ");
                System.out.println("1] Attack\n2] Defend\n3] Charge");
                System.out.print("> Your Choice: ");
                input = SC.next().charAt(0);

                if (!isInputValid(input)) {
                    System.out.println("Invalid input... Please try again.");
                    TimeUnit.SECONDS.sleep(1);
                }
            } while (!isInputValid(input));

            if (input == '1') return "Attack";
            else if (input == '2') return "Defend";
            else if (input == '3') {
                if (!Player.isCharged()) {
                    Player.Charge(true);
                    Player.SetAttack(Player.GetAttack() * 3);
                    input = '0';
                    return "Charge";
                } else {
                    System.out.println("You're already Charged!");
                }
            }
        } while (input == '3' && Player.isCharged());

        return "";
    }

    static void DisplayFight() {
        System.out.println("🧔 PLAYER:");
        System.out.println("HP ❤: " + Player.GetHP());
        System.out.println("ATK 🗡: " + Player.GetAttack());
        System.out.println("DEF🛡: " + Player.GetDefense());
        System.out.println("SPD ⚡: " + Player.GetSpeed());
        System.out.println("================== VS =====================");
        System.out.println("👹 ENEMY:");
        System.out.println("HP ❤: " + SelectedEnemy.GetHP());
        System.out.println("ATK 🗡: " + SelectedEnemy.GetAttack());
        System.out.println("DEF🛡: " + SelectedEnemy.GetDefense());
        System.out.println("SPD ⚡: " + SelectedEnemy.GetSpeed());
    }

    static void ClearScreen() {
        SC.nextLine();
        System.out.print("Press Enter to continue...");
        SC.nextLine();
        for (int i = 0; i < 50; i++) System.out.println();
    }

    static boolean isInputValid(char selection) {
        int input = (int) selection - '0';
        return input >= 1 && input <= 3;
    }
}