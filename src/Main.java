//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Dealer Dealer = new Dealer();
        Dealer.shuffle();
        for (int i = 0; i < Dealer.getCardDeck().size() ; i++) {
            System.out.println("-------------START------------");
            System.out.println(Dealer.getCardDeck().get(i).getSymbol());
            System.out.println(Dealer.getCardDeck().get(i).getValue().getCardValuePair().getValue());
            System.out.println("-------------END---------------");
        }
    }
}