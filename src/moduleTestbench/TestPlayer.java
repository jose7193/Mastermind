/**
 * Testbench for Player
 * 05/05: first edited -- AC
 * 
 */

public class TestPlayer {
    /** main */
    public static void main(String[] args) {

        Player p1 = new Player("Lisa", 50, 1, Player.CODE_BREAKER);
        Player p2 = new Player("Computer", 60, 10, Player.CODE_MAKER);
        
        System.out.println(p1 + "\n" + p2);

    }
}
