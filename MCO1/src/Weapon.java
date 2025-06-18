public class Weapon {
    private String Type;
    private int Attack;
    private int SpeedPenalty;

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

    public String GetType(){
        return Type;
    }

    public int getAttack(){
        return Attack;

    }

    public int GetSpeedPenalty(){
        return SpeedPenalty;
    }
}
