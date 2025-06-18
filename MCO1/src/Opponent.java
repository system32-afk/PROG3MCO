public class Opponent {
    private String Type;
    private int HitPoints = 100;
    private int Attack = 1;
    private int Defense = 1;
    private int Speed = 50;
    private Character Player;


    public Opponent(String type, Character Player){
        if (type == "Thief") {
            this.Type = type;
            this.HitPoints = 150;
            this.Attack = 20;
            this.Defense= 20;
            this.Speed = 40;
        }else if (type == "Viking"){
            this.Type = type;
            this.HitPoints = 250;
            this.Attack = 30;
            this.Defense= 30;
            this.Speed = 30;
        } else if (type == "Minotaur") {
            this.Type = type;
            this.HitPoints = 350;
            this.Attack = 40;
            this.Defense= 40;
            this.Speed = 20;
        }

        this.Player = Player;
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
        this.HitPoints -= Debuff;
    }
    public int GetDefense(){
        return Defense;
    }

    public int GetSpeed(){
        return Speed;
    }

    public void Attack(Character Player){
        Player.SetHP(Attack);
    }
}
