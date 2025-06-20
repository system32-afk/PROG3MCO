import java.lang.reflect.Method;
import java.util.ArrayList;

public class Opponent {
    private String Type;
    private int HitPoints = 100;
    private int Attack = 1;
    private int Defense = 1;
    private int Speed = 50;
    private Character Player;
    ArrayList<String> MoveSet = new ArrayList<>();


    public Opponent(String type, Character Player){
        if (type == "Thief") {
            this.Type = type;
            this.HitPoints = 150;
            this.Attack = 20;
            this.Defense= 20;
            this.Speed = 40;

            MoveSet.add("Attack");
        }else if (type == "Viking"){
            this.Type = type;
            this.HitPoints = 250;
            this.Attack = 30;
            this.Defense= 30;
            this.Speed = 30;

            MoveSet.add("Attack");
            MoveSet.add("Defend");
        } else if (type == "Minotaur") {
            this.Type = type;
            this.HitPoints = 350;
            this.Attack = 40;
            this.Defense= 40;
            this.Speed = 20;

            MoveSet.add("Attack");
            MoveSet.add("Charge");
        }

        this.Player = Player;
    }


    public String GetType(){
        return Type;
    }
    public void SetHP(int Damage){
        this.HitPoints -= Damage;
    }

    public int GetHP(){
        return HitPoints;
    }

    public void SetAttack(int Buff){
        Attack += Buff;
    }
    public int GetAttack(){
        return Attack;
    }

    public void SetDefense(int Debuff){
        this.HitPoints += Debuff;
    }
    public int GetDefense(){
        return Defense;
    }

    public int GetSpeed(){
        return Speed;
    }

    public void Attack(Character Player,int Damage){
        Player.SetHP(-Attack);
    }

    public void ChargeAttack(){
        this.Attack *= 3;
    }

    public  void ResetAttack(){
        this.Attack /= 3;
    }


    //
    //         THIS IS A ROUGH SKETCH FOR FAUX-AI
    //      It does the job but IDK if it can be better
    //
    int Move=0;
    public String Think(){
        String ReturnValue;


        if(Move == MoveSet.size())
            Move = 0;

        ReturnValue = MoveSet.get(Move);

        Move++;

        return ReturnValue;
    }
}
