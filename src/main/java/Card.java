public class Card {
    String suite; // heart, diamond, club, spades
    int value; // Ace = 1, 1-12
    int gameValue; // baccarat value (Jack, Queen, King = 10) the rest match value

    public Card(String theSuite, int theValue){
        suite = theSuite;
        value = theValue;
        if(value < 13 && value > 9){
            gameValue = 10;
        }
        else{
            gameValue = value;
        }
    }
}
