import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

class MyTest {

	@Test
	void cardConstructorTest() {
		Card myCard = new Card("heart", 13);
		assertNotNull(myCard, "error card constructor");
		assertEquals(10, myCard.gameValue);
	}

	@Test
	void baccaratDealerConstructorTest() {
		BaccaratDealer dealer = new BaccaratDealer();
		assertNotNull(dealer, "error dealer constructor");
	}

	@Test
	void baccaratDealerGenerateDeckTest1(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		assertEquals(52, dealer.deck.size(), "error on dealer deck size");

		ArrayList<String> suites = new ArrayList<>(List.of("heart", "diamond", "club", "spade"));
		ArrayList<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
		int v = 0;
		int s = 0;
		for(Card curCard : dealer.deck){
			assertEquals(suites.get(s), curCard.suite, "error on card suit in deck");
			assertEquals(values.get(v), curCard.value, "error on card value in deck");
			if(v < 12){
				v += 1;
			}
			else {
				v = 0;
				s += 1;
			}
		}
	}

	@Test
	void baccaratDealerDeckSizeTest(){
		BaccaratDealer dealer = new BaccaratDealer();
		assertEquals(52, dealer.deckSize(), "error on decksize()");
	}

	@Test
	void baccaratDealershuffleDeckTest(){
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.shuffleDeck();
		assertEquals(52, dealer.deckSize(), "error on shuffle deck size");
		ArrayList<Card> shuffleDeck1 = dealer.deck;

		dealer.shuffleDeck();
		assertEquals(52, dealer.deckSize(), "error on shuffle deck size");
		ArrayList<Card> shuffleDeck2 = dealer.deck;

		assertNotEquals(shuffleDeck1.get(0), shuffleDeck2.get(0), "error on shuffle deck order");
	}

	@Test
	void baccaratDealerDrawOneTest(){
		BaccaratDealer dealer = new BaccaratDealer();
		Card myCard = dealer.drawOne();
		Card myCard2 = dealer.drawOne();

		assertNotNull(myCard, "error on first draw");
		assertNotNull(myCard2, "error on second draw");

		assertNotEquals(myCard, myCard2);
	}

	@Test
	void baccaratDealerDealHandTest(){
		BaccaratDealer dealer = new BaccaratDealer();
		ArrayList<Card> hand1 = dealer.dealHand();

		assertEquals(2, hand1.size(), "first hand size error");
		assertEquals(50, dealer.deckSize(), "deck size error on dealHand1");

		ArrayList<Card> hand2 = dealer.dealHand();

		assertEquals(48, dealer.deckSize(), "deck size error on dealHand2");


		assertNotNull(hand2.get(0), "Card error on dealHand");
		assertNotNull(hand2.get(1), "Card error on dealHand");
	}
}
