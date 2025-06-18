public class Armor {
    private String Type;
    private int Defense;
    private int SpeedPenalty;

    public Armor(String type){
        if (type == "Light"){
            this.Type = type;
            this.Defense = 20;
            this.SpeedPenalty = 0;
        } else if (type == "Medium") {
            this.Type = type;
            this.Defense = 30;
            this.SpeedPenalty = 10;
        } else if (type == "Heavy") {
            this.Type = type;
            this.Defense = 40;
            this.SpeedPenalty = 20;
        }
    }

    public String GetType(){
        return Type;
    }

    public int GetDefense(){
        return Defense;
    }

    public int GetSpeedPenalty(){
        return SpeedPenalty;
    }

}
