public class Character {
    private int HitPoints = 100;
    private int Attack = 1;
    private int Defense = 1;
    private int Speed = 50;
    private Armor Armor;
    private Weapon weapon;
    private Opponent Enemy;
    private boolean IsCharged; //to be changed, im not sure pa

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

    public int GetDefense(){
        return Defense;
    }

    public int GetSpeed(){
        return Speed;
    }

    public void Attack(Opponent Enemy){
        Enemy.SetHP(Attack);
    }
}
