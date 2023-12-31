
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class BaccaratDealer {
    ArrayList<Card> deck; // deck of 52 Card objects

    // constructor that generates a fresh shuffled deck
    public BaccaratDealer(){
        shuffleDeck();
    }

    // creates new standard deck of 52 Card objects
    public void generateDeck(){
        ArrayList<Card> newDeck = new ArrayList<>();

        // lists of all the possible card options
        ArrayList<String> suites = new ArrayList<>(List.of("heart", "diamond", "club", "spade"));
        ArrayList<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));

        // iterates through 13 cards of each suit and stores into the list
        for(String suite : suites) {
            for (Integer value : values) {
                newDeck.add(new Card(suite, value));
            }
        }
        this.deck = newDeck;
    }

    // deal 2 cards in the form of an ArrayList
    public ArrayList<Card> dealHand(){
        // create new arraylist and draw 2 cards
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(drawOne());
        hand.add(drawOne());
        return hand;
    }

    // returns a single card from the deck
    public Card drawOne(){
        int topCard = deckSize() - 1;
        return this.deck.remove(topCard);
    }

    // create a new deck and randomize the order of the 52 card objects
    public void shuffleDeck(){
        generateDeck();
        Collections.shuffle(this.deck);
    }

    // return the amount of cards in the deck
    public int deckSize(){ return deck.size(); }

}
