import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Marvin");
        Player player2 = new Player("Leonard");
        List<Player> players = List.of(player1, player2);
        Dealer dealer = new Dealer();
        dealer.initialize();
        dealer.giveStartCards(players);
        for (int i = 0; i < dealer.getCardDeck().size() ; i++) {
            System.out.println("-------------START------------");
            System.out.println(dealer.getCardDeck().get(i).getSymbol());
            System.out.println(dealer.getCardDeck().get(i).getValue().getCardValuePair().getValue());
            System.out.println("-------------END---------------");
        }
        System.out.println(player1.getPlayerCards());
        System.out.println(player2.getPlayerCards());
    }
}