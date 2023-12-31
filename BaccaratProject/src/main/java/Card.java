import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Card extends Parent{
    String suite; // heart, diamond, club, spades
    int value; // Ace = 1, 1-12
    int gameValue; // baccarat value (Jack, Queen, King = 10) the rest match value
    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 140;

    public Card(String theSuite, int theValue){
        suite = theSuite;
        value = theValue;
        if(value <= 13 && value >= 10){
            gameValue = 10;
        }
        else{
            gameValue = value;
        }
        Rectangle bg = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        bg.setArcWidth(20);
        bg.setArcHeight(20);
        bg.setFill(Color.WHITE);

        Text text1 = new Text(suite);
        text1.setFont(Font.font(18));
        text1.setX(CARD_WIDTH - text1.getLayoutBounds().getWidth() - 10);
        text1.setY(text1.getLayoutBounds().getHeight());

        Text text2 = new Text(text1.getText());
        text2.setFont(Font.font(18));
        text2.setX(10);
        text2.setY(CARD_HEIGHT - 10);

        getChildren().addAll(bg, text1, text2);
    }
}
