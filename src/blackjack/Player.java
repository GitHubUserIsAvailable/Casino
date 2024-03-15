package blackjack;

import blackjack.card.Card;
import blackjack.card.CardValuePair;

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

    public boolean checkCommand(List<Card> hand, List<Card> hand2) {
        String command = Blackjack.getCommand();
        switch (command) {

            case "take card" -> {
                return takeCard(hand);
            }

            case "stand" -> {
                return stand();
            }

            case "double" -> {
                return doubleUp(Blackjack.dealer, hand);
            }

            case "split" -> {
                return splitUp(Blackjack.dealer, hand, hand2);
            }
        }
        return false;
    }

    private boolean takeCard(List<Card> hand) {
        Blackjack.dealer.giveCardToPlayer(this, hand);
        return true;
    }

    private boolean stand() {
        int handValue = checkHandValue(getPlayerCardsHand1());
        int dealerHandValue = checkHandValue(Blackjack.dealer.getDealerCards());
        Blackjack.checkForWinner(handValue, dealerHandValue, this);
        System.out.println("Cards Dealer: " + List.of(Blackjack.dealer.getDealerCards().get(0).toString(false) + Blackjack.dealer.getDealerCards().get(1).toString(false)));
        return true;
    }

    private boolean doubleUp(Dealer dealer, List<Card> hand) {
        if (dealer.getCollectedAmount() > amount) {
            System.out.println("Zu wenig Geld zum Doppeln!");
            return false;
        }
        dealer.setCollectedAmount(dealer.getCollectedAmount() * 2);
        setAmount(amount - dealer.getCollectedAmount() / 2);
        Blackjack.dealer.giveCardToPlayer(this, hand);
        stand();
        return true;

    }

    private boolean splitUp(Dealer dealer, List<Card> hand, List<Card> hand2) {
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
                        return false;
                    } else {
                        hand2.add(cardToCompare);
                        hand.remove(cardToCompare);
                        Blackjack.dealer.giveCardToPlayer(this, hand);
                        Blackjack.dealer.giveCardToPlayer(this, hand2);
                    }
                } else {
                    System.out.println("Zum splitten müssen beide Karten gleich sein!");
                    return false;
                }
            }
        }
        return true;
    }

    public int checkHandValue(List<Card> hand) {
        int cardValue = 0;
        int cardSecondValue = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getValue().getCardValuePair().getSecondValue().isEmpty()) {
                cardValue = cardValue + hand.get(i).getValue().getCardValuePair().getValue();
            } else {
                cardValue = cardValue + hand.get(i).getValue().getCardValuePair().getValue();
                cardSecondValue = cardSecondValue + hand.get(i).getValue().getCardValuePair().getSecondValue().get();
            }
        }
        if (cardValue > 21) {
            if (!(cardSecondValue == 0)) {
                for (int i = 0; i < cardSecondValue; i++) {
                    cardValue = cardValue - 10;
                    cardSecondValue--;
                }
            } else {
                return cardValue;
            }


        }
        return cardValue;
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

