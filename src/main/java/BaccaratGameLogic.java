
import java.util.ArrayList;

public class BaccaratGameLogic {
    String ptString = "Player Total: ";
    String dtString = "Dealer Total: ";
    String pwString = "Player wins!";
    String dwString = "Dealer wins!";
    String drawString = "Its a Draw!";

    // will evaluate 2 hands and return a string based on the winner
    // "player", "banker", or "draw"
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){
        return "me";
    }

    // return how many points in baccarat the hand is worth
    public int handTotal(ArrayList<Card> hand){
        return 0;
    }

    // return if the banker needs to draw given banker hand and playerCard
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){
        return true;
    }

    // return if the player needs to draw given player hand
    public boolean evaluatePlayerDraw(ArrayList<Card> hand){
        return true;
    }
}
