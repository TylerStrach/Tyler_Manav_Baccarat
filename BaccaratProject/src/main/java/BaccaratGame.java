import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.Objects;


public class BaccaratGame extends Application {
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	String playerBet;
	BaccaratGameLogic gameLogic;
	double currentBet;
	double totalWinnings;

	public HBox showCards(Pane card1, Pane card2){
		HBox cardBox = new HBox(10, card1, card2);
		return cardBox;
	}
	public HBox showCards(Pane card1, Pane card2, Pane card3){
		HBox cardBox = new HBox(10,card1, card2, card3);
		return cardBox;
	}
	public Pane generateCard (){
		Pane cardPane = new Pane();
		final int CARD_WIDTH = 100;
		final int CARD_HEIGHT = 140;
		Rectangle bg = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
		bg.setArcWidth(20);
		bg.setArcHeight(20);
		bg.setFill(Color.WHITE);

		cardPane.getChildren().add(bg);
		return cardPane;
	}
	public Pane generateText(String suite, int value){
		final int CARD_WIDTH = 100;
		final int CARD_HEIGHT = 140;
		String text = Integer.toString(value);
		if (text.equals("11")){
			text = "J";
		}
		if (text.equals("12")){
			text = "Q";
		}
		if (text.equals("13")){
			text = "K";
		}

		Pane textPane = new Pane();
		Text text1 = new Text(suite);
		text1.setFont(Font.font(18));
		text1.setX(CARD_WIDTH - text1.getLayoutBounds().getWidth() - 10);
		text1.setY(text1.getLayoutBounds().getHeight());

		Text text2 = new Text(text1.getText());
		text2.setFont(Font.font(18));
		text2.setX(10);
		text2.setY(CARD_HEIGHT - 10);

		Text text3 = new Text (text);
		text3.setFont(Font.font(18));
		text3.setX(50);
		text3.setY(70);

		textPane.getChildren().addAll(text1,text2,text3);
		return textPane;
	}

	public double evaluateWinnings(){
		String outcome = gameLogic.whoWon(playerHand,bankerHand);
		if(Objects.equals(outcome, "player") && Objects.equals(playerBet, "player")){
			totalWinnings += (currentBet*2);
		}
		else if (Objects.equals(outcome, "banker") && Objects.equals(playerBet, "banker")){
			totalWinnings += currentBet+(currentBet*0.95);
		}
		else if (Objects.equals(outcome, "tie") && Objects.equals(playerBet, "tie")){
			totalWinnings += (currentBet*9);
		}
		else{
			if(totalWinnings >0) {
				totalWinnings -= currentBet;
			}
		}
		return totalWinnings;
	}

	private Parent initializeGame(Stage primaryStage){
		Pane root = new Pane();
		root.setPrefSize(800, 600);

		Region background = new Region();
		background.setPrefSize(800, 600);
		background.setStyle("-fx-background-color: rgba(0, 0, 0, 1)");

		HBox rootLayout = new HBox(5);
		rootLayout.setPadding(new Insets(5, 5, 5, 5));
		Rectangle leftBG = new Rectangle(550, 560);
		leftBG.setArcWidth(50);
		leftBG.setArcHeight(50);
		leftBG.setFill(Color.GREEN);
		Rectangle rightBG = new Rectangle(230, 560);
		rightBG.setArcWidth(50);
		rightBG.setArcHeight(50);
		rightBG.setFill(Color.ORANGE);

		VBox leftVBox = new VBox(50);
		leftVBox.setAlignment(Pos.TOP_CENTER);

		VBox rightVBox = new VBox(20);
		rightVBox.setAlignment(Pos.CENTER);

		TextField bet = new TextField("BET");
		Text message = new Text("Welcome to Baccarat!");
		bet.setDisable(false);
		bet.setMaxWidth(50);
		message.setDisable(true);
		Text money = new Text("BET AMOUNT: 0");
		Text total = new Text("TOTAL WINNINGS: " + totalWinnings);
		Text recipient = new Text("BET ON: ");
		Button btnPlay = new Button("PLAY");
		Button btnPlayer = new Button("Player");
		Button btnTie = new Button("Tie");
		Button btnBanker = new Button("Banker");
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll("Exit", "Fresh Start");
		TextArea result = new TextArea();
		result.setEditable(false);
		result.setWrapText(true);
		result.setPrefSize(230,120);

		HBox hbox = new HBox();
		hbox.getChildren().add(comboBox);

		HBox buttonsHBox = new HBox(15, btnPlayer, btnTie, btnBanker);
		buttonsHBox.setAlignment(Pos.CENTER);

		rootLayout.getChildren().addAll(new StackPane(leftBG, leftVBox), new StackPane(rightBG, rightVBox));
		root.getChildren().addAll(background, rootLayout);
		rightVBox.getChildren().addAll(result,message, bet, btnPlay, money, total, recipient, buttonsHBox, comboBox);
		comboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String selectedOption = comboBox.getValue();
				if (selectedOption.equals("Exit")) {
					primaryStage.close(); // Exit the application
				} else if (selectedOption.equals("Fresh Start")) {
					totalWinnings = 0;
					money.setText("BET AMOUNT: " + 0);
					total.setText("TOTAL WINNINGS: " + 0);
					recipient.setText("BET ON: ");
					btnPlayer.setDisable(false);
					btnBanker.setDisable(false);
					btnTie.setDisable(false);
				}
			}
		});
		btnPlayer.setOnAction(event ->{
			playerBet = "player";
			String playerInput = bet.getText();
			currentBet = Integer.parseInt(playerInput);
			money.setText("BET AMOUNT: " + playerInput);
			recipient.setText("BET ON: " + playerBet);
			btnPlayer.setDisable(true);
			btnBanker.setDisable(true);
			btnTie.setDisable(true);
		});
		btnTie.setOnAction(event ->{
			playerBet = "tie";
			String playerInput = bet.getText();
			currentBet = Integer.parseInt(playerInput);
			money.setText("BET AMOUNT: " + playerInput);
			recipient.setText("BET ON: " + playerBet);
			btnPlayer.setDisable(true);
			btnBanker.setDisable(true);
			btnTie.setDisable(true);
		});
		btnBanker.setOnAction(event ->{
			playerBet = "banker";
			String playerInput = bet.getText();
			currentBet = Integer.parseInt(playerInput);
			money.setText("BET AMOUNT: " + playerInput);
			recipient.setText("BET ON: " + playerBet);
			btnPlayer.setDisable(true);
			btnBanker.setDisable(true);
			btnTie.setDisable(true);
		});
		btnPlay.setOnAction(event->{
			theDealer = new BaccaratDealer();
			gameLogic = new BaccaratGameLogic();
			bankerHand = theDealer.dealHand();
			playerHand = theDealer.dealHand();
			Card bankerCard1 = bankerHand.get(0);
			Card bankerCard2 = bankerHand.get(1);
			Card playerCard1 = playerHand.get(0);
			Card playerCard2 = playerHand.get(1);
			Pane card1Text = generateText(bankerCard1.suite,bankerCard1.value);
			Pane card2Text = generateText(bankerCard2.suite,bankerCard2.value);
			Pane card4Text = generateText(playerCard1.suite,playerCard1.value);
			Pane card5Text = generateText(playerCard2.suite,playerCard2.value);
			Pane card1 = generateCard();
			Pane card2 = generateCard();
			Pane card3 = generateCard();
			Pane card4 = generateCard();
			Pane card5 = generateCard();
			Pane card6 = generateCard();
			card1.getChildren().add(card1Text);
			card2.getChildren().add(card2Text);
			card4.getChildren().add(card4Text);
			card5.getChildren().add(card5Text);
			HBox bankerCardBox = showCards(card1, card2, card3);
			HBox playerCardBox = showCards(card4, card5, card6);


			PauseTransition pause = new PauseTransition(Duration.seconds(3));
			int dealerTotal = gameLogic.handTotal(bankerHand);
			int playerTotal = gameLogic.handTotal(playerHand);
			Text dealerScore = new Text("Banker: " + dealerTotal);
			Text playerScore = new Text("Player: " + playerTotal);

			leftVBox.getChildren().setAll(dealerScore,bankerCardBox, playerScore, playerCardBox);

			if(gameLogic.evaluatePlayerDraw(playerHand)){
				Card newCard = theDealer.drawOne();
				Pane newCardText = generateText(newCard.suite, newCard.value);
				playerHand.add(newCard);
				playerTotal = gameLogic.handTotal(playerHand);
				playerScore.setText("Player: " + playerTotal);

				pause.play();
				card6.getChildren().add(newCardText);

				if(gameLogic.evaluateBankerDraw(bankerHand,playerHand.get(playerHand.size()-1))){
					newCard = theDealer.drawOne();
					newCardText = generateText(newCard.suite, newCard.value);
					bankerHand.add(newCard);
					dealerTotal = gameLogic.handTotal(bankerHand);
					dealerScore.setText("Banker: " + dealerTotal);
					pause.play();
					card3.getChildren().add(newCardText);
				}

			}
			else if(gameLogic.evaluateBankerDraw(bankerHand,null)){
				Card newCard = theDealer.drawOne();
				Pane newCardText = generateText(newCard.suite, newCard.value);
				bankerHand.add(newCard);
				dealerTotal = gameLogic.handTotal(bankerHand);
				dealerScore.setText("Banker: " + dealerTotal);
				pause.play();
				card3.getChildren().add(newCardText);

			}
			evaluateWinnings();
			String outcome = gameLogic.whoWon(playerHand,bankerHand);
			if ((Objects.equals(outcome, "player")) && (Objects.equals(playerBet, "player"))){
				result.setText("Player Total: " + playerTotal + "  Banker Total: " + dealerTotal +
								"\n Player wins! \n" +
								"Congrats,you bet Player! You win!");
			}
			else if ((Objects.equals(outcome, "banker")) && Objects.equals(playerBet, "banker")){
				result.setText("Player Total: " + playerTotal + "  Banker Total: " + dealerTotal +
						"\n Banker wins! \n" +
						"Congrats,you bet Banker! You win!");
			}
			else if ((Objects.equals(outcome, "tie")) && Objects.equals(playerBet, "tie")){
				result.setText("Player Total: " + playerTotal + "  Banker Total: " + dealerTotal +
						"\n Tie! \n" +
						"Congrats,you bet Tie! You win!");
			}
			else if ((Objects.equals(outcome, "tie")) && !(Objects.equals(playerBet, "tie"))){
				result.setText("Player Total: " + playerTotal + "  Banker Total: " + dealerTotal +
						"\n Tie! \n" +
						"Sorry, you bet on " + playerBet +"! You lost your bet!");

			}
			else if ((Objects.equals(outcome, "player")) && !(Objects.equals(playerBet, "player"))){
				result.setText("Player Total: " + playerTotal + "  Banker Total: " + dealerTotal +
						"\n Player wins! \n" +
						"Sorry, you bet on " + playerBet +"! You lost your bet!");

			}
			else if ((Objects.equals(outcome, "banker")) && !(Objects.equals(playerBet, "banker"))){
				result.setText("Player Total: " + playerTotal + "  Banker Total: " + dealerTotal +
						"\n Banker wins! \n" +
						"Sorry, you bet on " + playerBet +"! You lost your bet!");

			}


			total.setText("TOTAL WINNINGS: " + totalWinnings);
			btnPlayer.setDisable(false);
			btnBanker.setDisable(false);
			btnTie.setDisable(false);
		});
		return root;
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// display start screen
		primaryStage.setScene(new Scene(initializeGame(primaryStage)));
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Baccarat");
		primaryStage.show();


	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}