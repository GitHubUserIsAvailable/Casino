package blackjack;

import blackjack.card.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {

    private int amount;
    private final String name;
    private final List<Card> playerCardsHand1;
    private final List<Card> playerCardsHand2;
    private final List<Card> playerCardsHand3;
    private final List<Card> playerCardsHand4;

    public Player(String name) {
        this.name = name;
        this.playerCardsHand1 = new ArrayList<>();
        this.playerCardsHand2 = new ArrayList<>();
        this.playerCardsHand3 = new ArrayList<>();
        this.playerCardsHand4 = new ArrayList<>();
        this.amount = 1000;
    }

    public boolean checkAmount(int input) {
        return input <= amount;
    }

    public void checkCommand(List<Card> hand) {
        String command = Blackjack.getCommand();
        switch (command) {

            case "take card" -> takeCard(hand);

            case "stand" -> stand();

            case "double" -> doubleUp(Blackjack.dealer);

            case "split" -> splitUp(Blackjack.dealer, hand);
        }
    }

    private void takeCard(List<Card> hand) {
        Blackjack.dealer.giveCardToPlayer(this, hand);
    }

    private void stand() {
    }

    private void doubleUp(Dealer dealer) {
        if (dealer.getCollectedAmount() > amount) {
            System.out.println("Zu wenig Geld zum Doppeln!");
            return;
        }
        dealer.setCollectedAmount(dealer.getCollectedAmount() * 2);
        setAmount(amount - dealer.getCollectedAmount() / 2);
        //Blackjack.dealer.giveCardToPlayer(this);
        stand();
    }

    private void splitUp(Dealer dealer, List<Card> hand) {
        Card cardToCompare = null;
        Iterator<Card> iterator1 = hand.iterator();
        while (iterator1.hasNext()) {
            Card card = iterator1.next();
            if (cardToCompare == null) {
                cardToCompare = card;
            } else {
                if (cardToCompare.getValue() == card.getValue()) {
                    if (dealer.getCollectedAmount() > amount) {
                        System.out.println("zu wenig Geld zum Splitten!");
                    } else {
                        playerCardsHand2.add(cardToCompare);
                        hand.remove(cardToCompare);
                        Blackjack.dealer.giveCardToPlayer(this, hand);
                        Blackjack.dealer.giveCardToPlayer(this, playerCardsHand2);
                        System.out.println("TESTWEISE:------------- SPLIT ERFOLGREICH -------------"); // TEST; REMOVE LINE -----------
                    }
                } else {
                    System.out.println("Zum splitten müssen beide Karten gleich sein!");
                }
            }
        }
    }

    public List<Card> getPlayerCardsHand1() {
        return playerCardsHand1;
    }

    public List<Card> getPlayerCardsHand2() {
        return playerCardsHand2;
    }

    public List<Card> getPlayerCardsHand3() {
        return playerCardsHand3;
    }

    public List<Card> getPlayerCardsHand4() {
        return playerCardsHand4;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

