package moduleFolder;

/*
 * Description: This sets/gets the Player info
 * 05/05: first edited -- AC
 * 
 */

public class Player {
    private String name;
    private int score;
    private static int attempts;
    private int playerType;
    private String player;
    
    public static final int CODE_MAKER = 1;
    public static final int CODE_BREAKER = 2;
    
    public Player() {
    	this.attempts = 0;
    }
    
    public Player(String name, int playerType) {
        this.name = name;
        this.playerType = playerType;
    }
    
    public Player(String name, int score, int attempts, int playerType) {
        this.name = name;
        this.score = score;
        this.attempts = attempts;
        this.playerType = playerType;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPlayerType() {
		return playerType;
	}

	public void setPlayerType(int playerType) {
		this.playerType = playerType;
		
	}
	
	public String findPlayerType(int playerType) {
		switch(playerType) {
			case CODE_MAKER:
				player = "Code Maker"; break;
			case CODE_BREAKER:
				player = "Code Breaker"; break;
			default:
				player = "no player"; break;
		}
		return player;
	}

	public int getAttempts() {
		return attempts;
	}

	public static void incAttempts() {
		attempts++;
	}
	
	public String toString() {
		return "Name: " + name + 
				"\n Player Type: " + findPlayerType(playerType) + 
			   "\n Score: " + score +
			   "\n Attempts: " + attempts;  
	}
    
}

