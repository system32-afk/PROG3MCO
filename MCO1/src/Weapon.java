/**
 * Represents a weapon that affects the player's attack stat.
 */
public class Weapon {

    private String Type;
    private int Attack;
    private int SpeedPenalty;

    /**
     * Constructs a weapon with a specific type.
     * @param type "Dagger", "Sword", or "Battle Axe"
     */
    public Weapon(String type){
        if (type == "Dagger"){
            this.Type = type;
            this.Attack = 20;
            this.SpeedPenalty = 0;
        } else if (type == "Sword") {
            this.Type = type;
            this.Attack = 30;
            this.SpeedPenalty = 10;
        } else if (type == "Battle Axe") {
            this.Type = type;
            this.Attack = 40;
            this.SpeedPenalty = 20;
        }
    }
    /**
     * Returns the type of weapon.
     * @return Weapon type
     */
    public String GetType(){
        return Type;
    }

    /**
     * Returns the base attack value of the weapon.
     * @return attack stat
     */
    public int getAttack(){
        return Attack;

    }

    /**
     * Returns the speed penalty caused by the weapon.
     * @return Speed penalty
     */
    public int GetSpeedPenalty(){
        return SpeedPenalty;
    }
}
