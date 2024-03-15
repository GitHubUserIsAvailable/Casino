package horseRacing;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HorseRacing {

    public static void main(String[] args) {
        HorseRacingInitializer horseRacingInitializer = new HorseRacingInitializer();
        HorseRacingEvaluator horseRacingEvaluator = new HorseRacingEvaluator();
        HorseGame horseGame = new HorseGame(horseRacingEvaluator, horseRacingInitializer);
        horseGame.start();
    }
}