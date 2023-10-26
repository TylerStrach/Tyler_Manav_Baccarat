import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

class MyTest {
	static BaccaratGameLogic logic = new BaccaratGameLogic();
	static BaccaratDealer dealer = new BaccaratDealer();

	static ArrayList<Card> hand1 = new ArrayList<>(List.of(new Card("heart", 3),
			new Card("spade", 12), new Card("heart", 6)));
	static ArrayList<Card> hand2 = new ArrayList<>(List.of(new Card("diamond", 10),
			new Card("club", 1)));

	@Test
	void cardConstructorTest() {
		Card myCard = new Card("heart", 13);
		assertNotNull(myCard, "error card constructor");
		assertEquals(10, myCard.gameValue);
	}

	@Test
	void baccaratDealerConstructorTest() {
		assertNotNull(dealer, "error dealer constructor");
	}

	@Test
	void baccaratDealerGenerateDeckTest1(){
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
		assertEquals(52, dealer.deckSize(), "error on decksize()");
	}

	@Test
	void baccaratDealerShuffleDeckTest(){
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
		Card myCard = dealer.drawOne();
		Card myCard2 = dealer.drawOne();

		assertNotNull(myCard, "error on first draw");
		assertNotNull(myCard2, "error on second draw");

		assertNotEquals(myCard, myCard2);
	}

	@Test
	void baccaratDealerDealHandTest(){
		ArrayList<Card> hand1 = dealer.dealHand();

		assertEquals(2, hand1.size(), "first hand size error");
		assertEquals(50, dealer.deckSize(), "deck size error on dealHand1");

		ArrayList<Card> hand2 = dealer.dealHand();

		assertEquals(48, dealer.deckSize(), "deck size error on dealHand2");

		assertNotNull(hand2.get(0), "Card error on dealHand");
		assertNotNull(hand2.get(1), "Card error on dealHand");
	}

	@Test
	void baccaratGameLogicWhoWonTest(){
		ArrayList<Card> emptyHand = new ArrayList<>();

		assertEquals("draw", logic.whoWon(emptyHand, emptyHand), "error on draw btwn empty hands");
		assertEquals("player", logic.whoWon(hand1, emptyHand), "error on player win against empty");
		assertEquals("dealer", logic.whoWon(emptyHand, hand2), "error on dealer win against empty");
		assertEquals("player", logic.whoWon(hand1, hand2), "error on player win against dealer");
	}

	@Test
	void baccaratGameLogicHandTotalTest(){
		ArrayList<Card> emptyHand = new ArrayList<>();
		assertEquals(0, logic.handTotal(emptyHand), "error on empty hand total");

		assertEquals(9, logic.handTotal(hand1), "error on 2 card hand total");
		assertEquals(1, logic.handTotal(hand2), "error on 3 card hand total");
	}

	@Test
	void baccaratGameLogicEvaluatePlayerDrawTest(){
		assertFalse(logic.evaluatePlayerDraw(hand1));
		assertTrue(logic.evaluatePlayerDraw(hand2));
	}

	@Test
	void baccaratGameLogicEvaluateBankerDrawTest(){
		//TODO tmrw
	}
}
