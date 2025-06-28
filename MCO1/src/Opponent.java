import com.sun.jdi.Value;

import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 * Represents the enemy in the game.
 * Has type-based attributes and basic AI behavior for selecting moves.
 */
public class Opponent {
    private String Type;
    private int HitPoints = 100;
    private int Attack = 1;
    private int Defense = 1;
    private int Speed = 50;
    private Character Player;
    private boolean IsCharged;
    private boolean IsDefended;
    ArrayList<String> MoveSet = new ArrayList<>();


    /**
     * Constructs an opponent with a specific type and target player.
     * @param type the enemy type ("Thief", "Viking", or "Minotaur")
     * @param Player the player instance to target
     */
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

    /**
     * Gets the type of the opponent.
     * @return the type as a string
     */
    public String GetType(){
        return Type;
    }

    /**
     * Adjusts the opponent's HP by a given amount.
     * @param value amount to adjust (negative for damage)
     */
    public void SetHP(int value){
        this.HitPoints += value;
    }

    /**
     * Gets the opponent's current HP.
     * @return HP value
     */
    public int GetHP(){
        return HitPoints;
    }

    /**
     * Adjusts the opponent's attack stat.
     * @param value value to add (can be negative)
     */
    public void SetAttack(int value){
        Attack = value;
    }

    /**
     * Gets the opponent's current attack stat.
     * @return attack value
     */
    public int GetAttack(){
        return Attack;
    }

    /**
     * Sets the opponent's defense stat.
     * @param value value to add (can be negative)
     */
    public void SetDefense(int value){
        this.Defense += value;
    }
    /**
     * Gets the opponent's defense stat.
     * @return defense value
     */
    public int GetDefense(){
        return Defense;
    }
    /**
     * Gets the opponent's speed stat.
     * @return speed value
     */
    public int GetSpeed(){
        return Speed;
    }

    /**
     * Applies damage to the player.
     * @param Damage the amount to deal
     */
    public void Attack(int Damage){
        Player.SetHP(-Attack);
    }
    /**
     * Sets the opponent's charged status.
     * @param value true if charged, false otherwise
     */
    public void Charge(boolean value){
        this.IsCharged = value;
    }
    /**
     * Returns the opponent's charged state.
     * @return true if charged, false otherwise
     */
    public Boolean isCharged(){
        return IsCharged;
    }
    /**
     * Sets whether the opponent is defending.
     * @param value true if defending, false otherwise
     */
    public void Defend(boolean value){
        this.IsDefended = value;
    }
    /**
     * Returns whether the opponent is defending.
     * @return true if defending, false otherwise
     */
    public boolean GetDefend(){
        return IsDefended;
    }


    //
    //         THIS IS A ROUGH SKETCH FOR FAUX-AI
    //                  It does the job tho
    //


    int Move=0;
    /**
     * Decides the opponent's next move based on its pattern.
     * @return the chosen move ("Attack", "Defend", or "Charge")
     */
    public String Think(){
        String ReturnValue;


        if(Move == MoveSet.size())
            Move = 0;

        ReturnValue = MoveSet.get(Move);

        Move++;

        return ReturnValue;
    }
}
