import card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public Player(String name) {
        this.name = name;
        this.playerCards = new ArrayList<>();
    }

    private String name;
    private List<Card> playerCards;

    public List<Card> getPlayerCards() {
        return playerCards;
    }
}
