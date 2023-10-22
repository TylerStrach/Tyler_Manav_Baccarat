import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class BaccaratGame extends Application {
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic;

	double currentBet;
	double totalWinnings;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// display start screen

		// if the user presses options, then display options dropdown
			// if user presses end game, end the program
			// if the user presses reset, then reset to bet screen

		// display bet screen (includes blank cards and total winnings)
			// wait for user bet
			// save user bet and proceed
			// update the text box

		// transition the cards being dealt, first player then dealer 2 times
			// update the text box as the cards are dealt

		// pause at screen with 2 cards in the player and dealer hands
			// check for a natural win
			// if yes
				// display game result message and user winnings/losings

		// check if the player needs to hit
			// if yes then deal the user card and transition to 3 player hand screen
			// update the text box

		// check if the dealer needs to hit
			// transition to a 3 card hand to both screen
			// update the text box

		// compare hands and determine the winner of the round
			// display game result message and user winnings/losings


		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to JavaFX");
		
		 Rectangle rect = new Rectangle (100, 40, 100, 100);
	     rect.setArcHeight(50);
	     rect.setArcWidth(50);
	     rect.setFill(Color.VIOLET);

	     RotateTransition rt = new RotateTransition(Duration.millis(5000), rect);
	     rt.setByAngle(270);
	     rt.setCycleCount(4);
	     rt.setAutoReverse(true);
	     SequentialTransition seqTransition = new SequentialTransition (
	         new PauseTransition(Duration.millis(500)),
	         rt
	     );
	     seqTransition.play();
	     
	     FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
	     ft.setFromValue(1.0);
	     ft.setToValue(0.3);
	     ft.setCycleCount(4);
	     ft.setAutoReverse(true);

	     ft.play();
	     BorderPane root = new BorderPane();
	     root.setCenter(rect);
	     
	     Scene scene = new Scene(root, 700,700);
			primaryStage.setScene(scene);
			primaryStage.show();
	}

	// determine if the user won or lost and return their winnings/what they lost
	public double evaluateWinnings(){
		return 0;
	}
}
