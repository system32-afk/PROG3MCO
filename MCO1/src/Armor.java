/**
 * Represents a weapon that affects the player's attack stat.
 */
public class Armor {
    private String Type;
    private int Defense;
    private int SpeedPenalty;

    /**
     * Constructs an armor with a given type.
     * @param type "Light", "Medium", or "Heavy"
     */
    public Armor(String type){
        if (type == "Light"){
            this.Type = type;
            this.Defense = 20;
            this.SpeedPenalty = 5;
        } else if (type == "Medium") {
            this.Type = type;
            this.Defense = 30;
            this.SpeedPenalty = 15;
        } else if (type == "Heavy") {
            this.Type = type;
            this.Defense = 40;
            this.SpeedPenalty = 25;
        }
    }

    /**
     * Returns the type of the armor.
     * @return armor type
     */
    public String GetType(){
        return Type;
    }
    /**
     * Returns the defense value provided by the armor.
     * @return defense stat
     */
    public int GetDefense(){
        return Defense;
    }

    /**
     * Returns the speed penalty caused by the armor.
     * @return speed penalty
     */
    public int GetSpeedPenalty(){
        return SpeedPenalty;
    }

}
