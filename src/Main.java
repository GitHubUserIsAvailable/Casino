import horseRacing.Horse;
import horseRacing.HorseRacingEvaluator;
import horseRacing.HorseRacingInitializer;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void wetten() {


        boolean validInput = false;

        while (!validInput) {
            System.out.println("Auf welches Pferd möchtest du wetten?");
            int antwortWetten = Integer.parseInt(scanner.nextLine());
            if (antwortWetten < 8) {
                validInput = true;
                System.out.println("Sie haben auf Pferd " +antwortWetten+ " gewettet");
            } else {
                System.out.println("Du kannst nur auf die Pferde 1 bis 8 wetten!");
            }
        }


    }

    public static void main(String[] args) {

        String green = "\u001B[32m";
        String reset = "\u001B[0m";

        HorseRacingInitializer horseRacingInitializer = new HorseRacingInitializer();
        HorseRacingEvaluator horseRacingEvaluator = new HorseRacingEvaluator();
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
                scanner.nextLine();
                System.out.println("--------------------------------------------------");
                for (Horse horse : horses) {
                    if (horse.isStepWinner()){
                        System.out.println(horseRacingEvaluator.displaySteps(horse) + green+"-(" + horse.getId() + "," + horse.getProbability() + ")-"+reset);
                        horse.setStepWinner(false);
                    }else {
                        System.out.println(horseRacingEvaluator.displaySteps(horse) + "-(" + horse.getId() + "," + horse.getProbability() + ")-");

                    }

                }
                System.out.println("--------------------------------------------------");
            }

        }
        System.out.println("Das Pferd mit der Nummer " + HorseRacingEvaluator.winningHorse.getId() + " hat gewonnen.");

    }
}