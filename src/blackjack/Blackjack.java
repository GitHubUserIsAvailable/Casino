package blackjack;
import blackjack.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Blackjack {

    private static final Scanner scanner = new Scanner(System.in);
    public static Dealer dealer = new Dealer();
    private static String command;
    private static int handValue;
    private static int dealerHandValue;

    public static void main(String[] args) {
        System.out.println("----------------------------------------------");
        System.out.println("Willkommen zum Blackjack!");
        System.out.println("Bitte gebe deinen Namen ein: ");
        Player player = new Player(scanner.nextLine());
        System.out.println("INFO: '" + player.getName() + "' wurde als Name gesetzt!");
        System.out.println("----------------------------------------------");
        playRound(player);
    }

    private static void playRound(Player player) {
        if (player.getAmount() == 0){
            System.out.println("Du hast kein Geld mehr!");
            System.exit(0);
        }
        System.out.println("Möchtest du eine weiter runde Spielen? (yes/no)");
        String playRound = scanner.nextLine();
        dealer.initialize();
        player.getPlayerCardsHand1().clear();
        player.getPlayerCardsHand2().clear();
        player.getPlayerCardsHand3().clear();
        player.getPlayerCardsHand4().clear();
        dealer.getDealerCards().clear();
        dealer.setCollectedAmount(0);
        if (playRound.equals("yes") || playRound.equals("y")) {
            System.out.println("Verfügbares Geld: " + player.getAmount());
            System.out.println("Wie viel Geld setzt du?");
            int input = Integer.parseInt(scanner.nextLine());
            boolean checkedAmount = false;
            if (!checkedAmount) {
                checkedAmount = player.checkAmount(input);
                dealer.setCollectedAmount(input);
                player.setAmount(player.getAmount() - input);
                System.out.println("----------------------------------------------");
                System.out.println("Gesetzt: " + dealer.getCollectedAmount());
                dealer.initialize();
                dealer.giveStartCards(Collections.singletonList(player));
                System.out.println("Verfügbares Geld: " + player.getAmount());
                System.out.println("----------------------------------------------");
                System.out.println("Cards " + player.getName() + " " + player.getPlayerCardsHand1().stream().map(card -> card.toString(false)).toList());
                System.out.println("Cards Dealer: " + List.of(dealer.getDealerCards().get(0).toString(false) + dealer.getDealerCards().get(1).toString(true)));
                System.out.println("----------------------------------------------");
                System.out.println("Folgende Befehle sind verfügbar: take card, stand, double, split");
                System.out.println("Gebe deinen Befehl ein: ");
                command = scanner.nextLine();
            }
            boolean checkedCommand = player.checkCommand(player.getPlayerCardsHand1(), player.getPlayerCardsHand2());
            if (checkedCommand && command.equals("split")) {
                splitGame(player, player.getPlayerCardsHand1(), player.getPlayerCardsHand2());
            } else {
                gameLoop(player);
            }

        }else {
            System.exit(0);
        }

    }

    public static void checkForWinner(int playerHandValue, int dealerHandValue, Player player) {
        handValue = playerHandValue;

        while (dealerHandValue < 17) {
            dealer.giveCardToDealer(dealer);
            dealerHandValue = player.checkHandValue(dealer.getDealerCards());
        }
        int playerPointsAway = 21 - playerHandValue;
        int dealerPointsAway = 21 - dealerHandValue;

        if (playerHandValue == 21) {
            System.out.println("Cards " + player.getName() + " " + player.getPlayerCardsHand1().stream().map(card -> card.toString(false)).toList());
            System.out.println("Cards Dealer: " + dealer.getDealerCards().stream().map(card -> card.toString(false)).toList());
            System.out.println("Du hast 21, BLACKJACK! Du gewinnst: " + dealer.getCollectedAmount() * 1.4);
            player.setAmount((int) ((int) player.getAmount() + (dealer.getCollectedAmount() * 1.4)));
            System.out.println("");
            playRound(player);
        } else if (playerHandValue < 21) {
            if (dealerHandValue > 21) {
                System.out.println("Cards Dealer: " + dealer.getDealerCards().stream().map(card -> card.toString(false)).toList());
                System.out.println("Der Dealer hat überkauft! Du gewinnst: " + dealer.getCollectedAmount() * 1.6);
                player.setAmount((int) ((int) player.getAmount() + (dealer.getCollectedAmount() * 1.6)));
                playRound(player);
            } else if (dealerHandValue == 21) {
                System.out.println("Cards Dealer: " + dealer.getDealerCards().stream().map(card -> card.toString(false)).toList());
                System.out.println("Der Dealer hat 21! Du verlierst: " + dealer.getCollectedAmount());
                playRound(player);
            } else {
                if (playerPointsAway < dealerPointsAway) {
                    System.out.println("Cards " + player.getName() + " " + player.getPlayerCardsHand1().stream().map(card -> card.toString(false)).toList());
                    System.out.println("Cards Dealer: " + dealer.getDealerCards().stream().map(card -> card.toString(false)).toList());
                    System.out.println("Du bist " + playerPointsAway + " Punkte entfernt von 21. Du gewinnst: " + dealer.getCollectedAmount() * 1.6);
                    player.setAmount((int) ((int) player.getAmount() + (dealer.getCollectedAmount() * 1.6)));
                    playRound(player);
                } else if (playerPointsAway > dealerPointsAway) {
                    System.out.println("Cards Dealer: " + dealer.getDealerCards().stream().map(card -> card.toString(false)).toList());
                    System.out.println("Cards " + player.getName() + " " + player.getPlayerCardsHand1().stream().map(card -> card.toString(false)).toList());
                    System.out.println("Der Dealer ist " + dealerPointsAway + " Punkte entfernt von 21. Du verlierst: " + dealer.getCollectedAmount());
                    playRound(player);
                } else {
                    System.out.println("Cards Dealer: " + dealer.getDealerCards().stream().map(card -> card.toString(false)).toList());
                    System.out.println("Cards " + player.getName() + " " + player.getPlayerCardsHand1().stream().map(card -> card.toString(false)).toList());
                    System.out.println("Es ist ein Unentschieden! Die Runde endet in einem Push.");
                    player.setAmount(player.getAmount() + dealer.getCollectedAmount());
                    playRound(player);
                }
            }
        } else {
            System.out.println("Cards " + player.getName() + " " + player.getPlayerCardsHand1().stream().map(card -> card.toString(false)).toList());
            System.out.println("Deine Karten haben einen Wert in höhe von über 21! (" + handValue + ") du verlierst!");
            playRound(player);
        }
    }


    private static void gameLoop(Player player) {
        for (int i = 0; i < 20; i++) {
            handValue = player.checkHandValue(player.getPlayerCardsHand1());
            dealerHandValue = player.checkHandValue((dealer.getDealerCards()));
            if (handValue > 21) {
                checkForWinner(handValue, dealerHandValue, player);
            }
            System.out.println("----------------------------------------------");
            System.out.println("Cards " + player.getName() + " " + player.getPlayerCardsHand1().stream().map(card -> card.toString(false)).toList());
            System.out.println("Cards Dealer: " + dealer.getDealerCards().stream().map(card -> card.toString(false)).toList());
            System.out.println("Gesetzt: " + dealer.getCollectedAmount());
            System.out.println("Verfügbares Geld: " + player.getAmount());
            System.out.println("Deine Karten haben einen Wert in höhe von: " + handValue);
            System.out.println("Gebe deinen Befehl ein: ");
            System.out.println("Folgende Befehle sind verfügbar: take card, stand");
            command = scanner.nextLine();
            player.checkCommand(player.getPlayerCardsHand1(), player.getPlayerCardsHand2());
        }


    }

    private static void splitGame(Player player, List<Card> hand, List<Card> hand2) {
        List<List<Card>> hands = new ArrayList<>();
        hands.add(player.getPlayerCardsHand1());
        hands.add(player.getPlayerCardsHand2());
        hands.add(player.getPlayerCardsHand3());
        hands.add(player.getPlayerCardsHand4());
        int displayHandNumber;

        for (int i = 0; i < 10; i++) {
            if (i >= 4) {
                i = 0;
            }
            displayHandNumber = i;
            List<Card> currentHand = hands.get(i);
            System.out.println("SPLIT METHODE AUSGERUFEN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            if (!hands.get(i).isEmpty()) {
                System.out.println("Cards " + player.getName() + " " + (displayHandNumber + 1) + " " + currentHand.stream().map(card -> card.toString(false)).toList());
                System.out.println("(take card, stand, double, split)");
                System.out.println("Gebe deinen Befehl ein: ");
                command = scanner.nextLine();
                player.checkCommand(currentHand, hand2);
            } else {
                i = -1;
            }
        }
    }

    public static String getCommand() {
        return command;
    }


    public static void setCommand(String command) {
        Blackjack.command = command;
    }
}




