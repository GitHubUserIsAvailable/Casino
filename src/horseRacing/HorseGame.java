package horseRacing;

import java.util.List;
import java.util.Scanner;

public class HorseGame {

    private static final String GREEN_COLOR = "\u001B[32m";
    private static final String RESET_COLOR = "\u001B[0m";

    private final HorseRacingEvaluator horseRacingEvaluator;

    private final HorseRacingInitializer horseRacingInitializer;

    private static final Scanner scanner = new Scanner(System.in);

    private final GameData gameData = new GameData();

    public HorseGame(HorseRacingEvaluator horseRacingEvaluator, HorseRacingInitializer horseRacingInitializer) {
        this.horseRacingEvaluator = horseRacingEvaluator;
        this.horseRacingInitializer = horseRacingInitializer;
    }

    public void start() {
        try {
            runGameRound();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }
    }

    public void wetten() {

        HorseGameUtils.takeInput(
                gameData::setAntwortWetten,
                gameData::getAntwortWetten,
                "Auf welches Pferd möchtest du wetten?",
                "Du kannst nur auf die Pferde 1 bis 8 wetten!",
                "Sie haben auf Pferd %s gewettet",
                9
        );

        System.out.println("Balance:" + gameData.getPlayer1().getCash());

        HorseGameUtils.takeInput(
                gameData::setAntwortWettbetrag,
                gameData::getAntwortWettbetrag,
                "Ihr Wetteinsatz:",
                "\u001B[31m" + "Du bist zu arm dafür!" + "\u001B[0m",
                "Sie haben %s auf Pferd " + gameData.getAntwortWetten() + " gesetzt",
                gameData.getPlayer1().getCash() + 1
                );
    }

    private void runGameRound() throws InterruptedException {
        List<Horse> horses = horseRacingInitializer.initialize();

        for (int i = 0; i < 5 ;i++) {
            System.out.println();
        }
        System.out.println("Chances of winning for every horse");
        for (Horse horse : horses){
            System.out.println("Horse " + horse.getId() + " : " + horse.getProbability() + "%" );
        }

        wetten();

        boolean evaluate;
        while (HorseRacingEvaluator.winningHorse == null) {

            evaluate = horseRacingEvaluator.evaluate(horses);

            if (evaluate) {
                Thread.sleep(750);
                System.out.println("--------------------------------------------------");
                for (Horse horse : horses) {
                    horseDoStep(horse);
                }
                System.out.println("--------------------------------------------------");
            }

        }
        if (HorseRacingEvaluator.winningHorse.getId() == gameData.getAntwortWetten()){
            countWinnerCash();
        }
        else {
            countLoserCash();
        }
        System.out.println("Ihr neuer Kontostand lautet: " + gameData.getPlayer1().getCash());
        Thread.sleep(2000);
        playAgain();
    }

    private void countLoserCash() {
        System.out.println("Das Pferd mit der Nummer " + HorseRacingEvaluator.winningHorse.getId() + " hat gewonnen.");
        System.out.println("\u001B[31m" + "Sie haben verloren!" + "\u001B[0m");
        gameData.getPlayer1().setCash(gameData.getPlayer1().getCash() - gameData.getAntwortWettbetrag());
    }

    private void countWinnerCash() {
        System.out.println(GREEN_COLOR + "Ihr Pferd hat das Rennen gewonnen!" + GREEN_COLOR);
        Integer winMultiplicator = HorseProperties.multiplicators.get(HorseRacingEvaluator.winningHorse.getProbability());
        int profitMultiplier = gameData.getAntwortWettbetrag() * winMultiplicator;
        gameData.getPlayer1().setCash(gameData.getPlayer1().getCash() + profitMultiplier);
        System.out.println("Ihr neuer Kontostand lautet: " + gameData.getPlayer1().getCash());
    }

    private void horseDoStep(Horse horse) {
        if (horse.isStepWinner()){
            System.out.println(horseRacingEvaluator.displaySteps(horse) + GREEN_COLOR +"-(" + horse.getId() + "," + horse.getProbability() + ")-" + RESET_COLOR);
            horse.setStepWinner(false);
        } else {
            System.out.println(horseRacingEvaluator.displaySteps(horse) + "-(" + horse.getId() + "," + horse.getProbability() + ")-");
        }
    }

    private void playAgain() throws InterruptedException {
        boolean isInputValid = false;
        while(!isInputValid){
            System.out.println("Wollen Sie nochmal spielen?");
            String playAgainAnswer = scanner.nextLine();
            if (playAgainAnswer.equalsIgnoreCase("ja")) {
                isInputValid = true;
                HorseRacingEvaluator.winningHorse = null;
                runGameRound();
            } else if (playAgainAnswer.equalsIgnoreCase("nein")) {
                isInputValid = true;
                System.out.println("Auf Wiedersehen");
            } else {
                System.out.println("Antworte bitte mit ja oder nein");
            }
        }
    }

}
