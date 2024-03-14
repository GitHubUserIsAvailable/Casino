package blackjack;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Marvin");
        Player player2 = new Player("Leonard");
        Player player3 = new Player("ABC");
        Dealer dealer = new Dealer();
        List<Player> players = List.of(player1, player2, player3);
        dealer.initialize();
        dealer.giveStartCards(players);
        for (int i = 0; i < dealer.getCardDeck().size(); i++) {
            System.out.println(dealer.getCardDeck().get(i).getSymbol());
            System.out.println(dealer.getCardDeck().get(i).getValue().getCardValuePair().getValue());
            System.out.println("-------------------------");
        }
        System.out.println("Cards blackjack.Player 1: " + player1.getPlayerCardsHand1());
        System.out.println("Cards blackjack.Player 2: " + player2.getPlayerCardsHand1());
        System.out.println("Cards blackjack.Player 3: " + player3.getPlayerCardsHand1());
        System.out.println("Cards blackjack.Dealer: " + dealer.getDealerCards());
    }
}