/**
 * Represents the player's character in the game, equipped with armor and a weapon.
 * Handles combat stats, opponent targeting, and charge/defend state.
 */
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

    /**
     * Constructs a new player character using the given armor and weapon.
     * Initializes the character's stats (HP, attack, defense, speed) based on equipment.
     *
     * @param armor the armor selected by the player (affects defense and speed)
     * @param weapon the weapon selected by the player (affects attack)
     */
    public Character(Armor armor, Weapon weapon){

        //Stat change due to armor
        this.Armor = armor;
        this.Defense += Armor.GetDefense();
        this.Speed -= Armor.GetSpeedPenalty();

        //Stat change due to Weapon
        this.weapon = weapon;
        this.Attack += weapon.getAttack();
        this.Speed -= weapon.GetSpeedPenalty();

    }


    /**
     * Sets the opponent for the player.
     * @param enemy the opponent to assign
     */
    public void SetOpponent(Opponent enemy){
        this.Enemy = enemy;
    }
    /**
     * Adjusts the player's HP by a given value.
     * Positive values heal; negative values inflict damage.
     * @param value the amount to adjust HP
     */
    public void SetHP(int value){
        this.HitPoints += value;
    }


    /**
     * Gets the current HP of the player.
     * @return the current HP
     */
    public int GetHP(){
        return HitPoints;
    }

    /**
     * Sets the player's attack stat directly.
     * @param Value new attack value
     */
    public void SetAttack(int Value){
        Attack = Value;
    }

    /**
     * Gets the player's current attack stat.
     * @return the current attack
     */
    public int GetAttack(){
        return Attack;
    }

    /**
     * Sets the player's defense state.
     * @param value true if defending, false otherwise
     */
    public void Defend(boolean value){
        IsDefended = value;
    }

    /**
     * Checks if the player is currently defending.
     * @return true if defending, false otherwise
     */
    public boolean isDefended() {
        return IsDefended;
    }

    /**
     * Gets the player's defense stat.
     * @return the defense value
     */
    public int GetDefense(){
        return Defense;
    }

    /**
     * Gets the player's speed stat.
     * @return the speed value
     */
    public int GetSpeed(){
        return Speed;
    }

    /**
     * Applies damage to the opponent based on attack value.
     * @param Damage the amount of damage to deal
     */
    public void Attack(int Damage){
        Enemy.SetHP(-Damage);
    }

    /**
     * Indicates whether the player has charged their attack.
     * @return true if charged, false otherwise
     */
    public boolean isCharged(){
        return this.IsCharged;
    }

    /**
     * Sets the player's charged state.
     * @param Condition true to set as charged, false otherwise
     */
    public void Charge(boolean Condition){
        this.IsCharged = Condition;
    }
}
