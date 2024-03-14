package blackjack;

import blackjack.card.Card;
import blackjack.card.Symbol;
import blackjack.card.Value;

import java.util.*;

public class Dealer {

    private int collectedAmount;
    private LinkedList<Card> cardDeck = new LinkedList<>();
    private final List<Card> dealerCards = new ArrayList<>();


    public void initialize() {
        List<Value> values = new ArrayList<>(List.of(Value.values()));
        List<Symbol> symbols = new ArrayList<>(List.of(Symbol.values()));

        for (Symbol symbol : symbols) {
            for (Value value : values) {
                cardDeck.add(new Card(symbol, value));
            }
        }
        this.shuffle();
    }

    public void giveStartCards(List<Player> playerList) {
        for (Player player : playerList) {
            giveCardToPlayer(player, player.getPlayerCardsHand1());
            giveCardToPlayer(player, player.getPlayerCardsHand1());
        }
        giveCardToDealer(this);
        giveCardToDealer(this);
    }


    public void giveCardToPlayer(Player player, List<Card> Cards) {
        //Card currentCard = cardDeck.poll();
        //Cards.add(currentCard);
        Card fixCard = cardDeck.get(Value.KING.ordinal());
        Cards.add(fixCard);

    }

    private void giveCardToDealer(Dealer dealer) {
        Card currentCard = cardDeck.poll();
        dealer.getDealerCards().add(currentCard);
    }


    public void shuffle() {
        List<Card> cards = new ArrayList<>(cardDeck);
        Collections.shuffle(cards);
        this.cardDeck = new LinkedList<>(cards);
    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public int getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(int collectedAmount) {
        this.collectedAmount = collectedAmount;
    }
}
