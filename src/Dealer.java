import card.Card;
import card.Symbol;
import card.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dealer {
    private LinkedList<Card> cardDeck = new LinkedList<>();

    private List<Card> dealerCards;


    public Dealer() {


    }
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

    public void giveStartCards(List<Player> playerList){
        for (Player player : playerList) {
            giveCard(player);
            giveCard(player);
            // dealerCards.add
        }
    }

    private void giveCard(Player player) {
        Card currentCard = cardDeck.poll();
        player.getPlayerCards().add(currentCard);
    }


    public void shuffle(){
        List<Card> cards = new ArrayList<>(cardDeck);
        Collections.shuffle(cards);
        this.cardDeck = new LinkedList<>(cards);
    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }
}
