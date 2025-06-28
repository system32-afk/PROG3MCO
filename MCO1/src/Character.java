public class Character {
    private int HitPoints = 100;
    private int Attack = 1;
    private int Defense = 1;
    private int Speed = 50;
    private Armor Armor;
    private Weapon weapon;
    private Opponent Enemy;
    private boolean IsCharged;
    private boolean IsDefended;

    public Character(Armor armor, Weapon wpn){

        //Stat change due to armor
        this.Armor = armor;
        this.Defense += Armor.GetDefense();
        this.Speed -= Armor.GetSpeedPenalty();

        //Stat change due to Weapon
        this.weapon = wpn;
        this.Attack += weapon.getAttack();
        this.Speed -= weapon.GetSpeedPenalty();

    }



    public void SetOpponent(Opponent enemy){
        this.Enemy = enemy;
    }
    public void SetHP(int value){
        this.HitPoints += value;
    }

    public int GetHP(){
        return HitPoints;
    }

    public void SetAttack(int Value){
        Attack = Value;
    }
    public int GetAttack(){
        return Attack;
    }

    public void Defend(boolean value){
        IsDefended = value;
    }

    public boolean isDefended() {
        return IsDefended;
    }

    public int GetDefense(){
        return Defense;
    }

    public int GetSpeed(){
        return Speed;
    }

    public void Attack(int Damage){
        Enemy.SetHP(-Damage);
    }

    public boolean isCharged(){
        return this.IsCharged;
    }

    public void Charge(boolean Condition){
        this.IsCharged = Condition;
    }
}
