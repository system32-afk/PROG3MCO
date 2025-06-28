/**
 * Represents the battlefield environment that applies special effects during combat.
 */
public class Environment {
    private String Environment;

    /**
     * Constructs an environment with a given name.
     * @param Environment "Arena", "Swamp", or "Colosseum"
     */
    public Environment(String Environment){
        this.Environment = Environment;
    }



    /**
     * Applies penalties or buffs to the player and opponent based on the environment type.
     *
     * - "Swamp": Player loses 1 HP, opponent gains +1 ATK
     * - "Colosseum": Player gains +1 ATK, opponent loses 1 DEF
     * - "Arena": No effects
     *
     * @param Player the player character
     * @param Enemy the opponent
     */
    public void PenalizeEntity(Character Player, Opponent Enemy){
        if (Environment.equals("Swamp")){
            Player.SetHP(-1);
            Enemy.SetAttack(Enemy.GetAttack()+1);

            System.out.println("SWAMP EFFECT: PLAYER LOSES 1HP, ENEMY GETS 1PT ATTACK BUFF...");

        } else if (Environment.equals("Colosseum")) {
            Player.SetAttack( Player.GetAttack()+1);
            Enemy.SetDefense(-1);
            System.out.println("COLOSSEUM EFFECT: PLAYER GAINS 1 ATK, ENEMY LOSES 1 DEF");
        }else{
            System.out.println("ARENA EFFECT: NONE...");
        }
    }
	
	public String getName() {
		return this.Environment;
	}
	
	

}
