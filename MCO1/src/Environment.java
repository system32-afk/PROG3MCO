public class Environment {
    private String Environment;


    public Environment(String Environment){
        this.Environment = Environment;
    }



    //PenalizeEntity method
    //for every turn it will get called

    //if Environment == Swamp: Inflict 1 damage to Player and Opp gains 1 atk
    //if Environment == Colosseum: Player gains 1 atk and Opp loses 1 def point
    //if Environment == Arena: do nothing

    public void PenalizeEntity(Character Player, Opponent Enemy){
        if (Environment.equals("Swamp")){
            Player.SetHP(-1);
            Enemy.SetAttack(1);

            System.out.println("SWAMP EFFECT: PLAYER LOSES 1HP, ENEMY GETS 1PT ATTACK BUFF...");

        } else if (Environment.equals("Colosseum")) {
            Player.SetAttack( Player.GetAttack()+1);
            Enemy.SetDefense(-1);
            System.out.println("COLOSSEUM EFFECT: PLAYER GAINS 1 ATK, ENEMY LOSES 1 DEF");
        }else{
            System.out.println("ARENA EFFECT: NONE...");
        }
    }
	
	public String getName() {
		return this.Environment;
	}
	
	

}
