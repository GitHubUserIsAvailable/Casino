import card.Card;
import card.Symbol;
import card.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {
    private List<Card> cardDeck = new ArrayList<>();

    private List<Card> dealerCards;


    public Dealer() {

        List<Value> values = new ArrayList<>(List.of(Value.values()));
        List<Symbol> symbols = new ArrayList<>(List.of(Symbol.values()));

        for (int i = 0; i < symbols.size(); i++) {
            for (int j = 0; j < values.size(); j++) {
                cardDeck.add(new Card(symbols.get(i),values.get(j)));

            }
        }
    }

    private void giveStartCards(List<Player> playerList){
        for (int i = 0; i < playerList.size(); i++) {
            Card currentCard = cardDeck.get(i);
            Player currentPlayer = playerList.get(i);
            currentPlayer.getPlayerCards().add(currentCard);
            cardDeck.remove(currentCard);

           // dealerCards.add
        }
    }


    public void shuffle(){
        Collections.shuffle(cardDeck);
    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }
}
