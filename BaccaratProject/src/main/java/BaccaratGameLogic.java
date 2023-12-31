
import java.util.ArrayList;

public class BaccaratGameLogic {
    static String ptString = "Player Total: ";
    static String dtString = "Dealer Total: ";
    static String pwString = "Player wins!";
    static String dwString = "Dealer wins!";
    static String drawString = "Its a Tie!";

    // will evaluate 2 hands and return a string based on the winner
    // hand1 = player, hand2 = banker
    // "player", "banker", or "tie"
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){
        // obtain the hand totals
        int playerTotal = this.handTotal(hand1);
        int bankerTotal = this.handTotal(hand2);

        // get the differences from the target number of 9
        playerTotal = Math.abs(playerTotal - 9);
        bankerTotal = Math.abs(bankerTotal - 9);

        // return the closest to 9, else it is a tie
        if(playerTotal < bankerTotal){ return "player"; }
        else if(bankerTotal < playerTotal){ return "dealer"; }
        else{ return "tie"; }
    }

    // return how many points in baccarat the hand is worth
    public int handTotal(ArrayList<Card> hand){
        int total = 0;

        // sum the values of the cards
        for(Card card : hand){
            total += card.gameValue;
        }

        // if the total is 10 or more, total is ones place
        if(total > 9){
            total = total % 10;
        }
        return total;
    }

    // return if the banker needs to draw given banker hand and playerCard
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){
        int bankerTotal = this.handTotal(hand);

        if(playerCard == null){ return bankerTotal < 6; } // if the player didn't draw
        else{ // if the player draws, decision based on banker and dealer cards
            if(bankerTotal < 3){ return true; } // draw if 0, 1, 2
            else if(bankerTotal == 3){ // draw when player card is not 8
                return playerCard.gameValue != 8;
            }
            else if(bankerTotal == 4){ // draw if player card is 2, 3, 4, 5, 6, 7
                return playerCard.gameValue < 8 && playerCard.gameValue > 1;
            }
            else if(bankerTotal == 5){ // draw if player card is 4, 5, 6, 7
                return playerCard.gameValue < 8 && playerCard.gameValue > 3;
            }
            else if(bankerTotal == 6){ // draw if player card is 6 or 7
                return playerCard.gameValue == 6 || playerCard.gameValue == 7;
            }
            else{ return false; } // don't draw when banker card is 7
        }
    }

    // return if the player needs to draw given player hand
    public boolean evaluatePlayerDraw(ArrayList<Card> hand){
        // if the total is less than 6, then we hit (true)
        // else, we stand (false)
        int playerTotal = this.handTotal(hand);
        return playerTotal < 6;
    }
}
