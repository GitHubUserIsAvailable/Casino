package horseRacing;

import horseRacing.Horse;
import horseRacing.HorseRacingEvaluator;
import horseRacing.HorseRacingInitializer;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HorseRacing {

    private static final Scanner scanner = new Scanner(System.in);

    private static int antwortWetten;




    static Player player1 = new Player("Saschalein");

    public static void wetten() {


        boolean validHorseInput = false;



        while (!validHorseInput) {
            System.out.println("Auf welches Pferd möchtest du wetten?");
            antwortWetten = Integer.parseInt(scanner.nextLine());
            if (antwortWetten < 9) {
                validHorseInput = true;
                System.out.println("Sie haben auf Pferd " +antwortWetten+ " gewettet");
            } else {
                System.out.println("Du kannst nur auf die Pferde 1 bis 8 wetten!");
            }
        }

        boolean validBetInput = false;

       while(!validBetInput){
           System.out.println("Balance:" + player1.getCash());
           System.out.println("Ihr Wetteinsatz:");
           int antwortWettbetrag = Integer.parseInt(scanner.nextLine());
           if (player1.getCash() >= antwortWettbetrag){
               System.out.println("Sie haben" + antwortWettbetrag + "auf" + antwortWetten + "gesetzt");
               validBetInput = true;
           } else {
               System.out.println("\u001B[31m" + "Du bist zu arm dafür!" + "\u001B[0m");


           }

       }


    }

    public static void main(String[] args) throws InterruptedException {

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
                Thread.sleep(1000);
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
        if (HorseRacingEvaluator.winningHorse.getId() == antwortWetten){
            System.out.println(green + "Ihr Pferd hat das Rennen gewonnen!" + green);
        }
        else {
            System.out.println("Das Pferd mit der Nummer " + HorseRacingEvaluator.winningHorse.getId() + " hat gewonnen.");
            System.out.println("\u001B[31m" + "Sie haben verloren!" + "\u001B[0m");
        }


        for (Horse horse : horses){
            System.out.println(horse.getId());
        }

    }
}