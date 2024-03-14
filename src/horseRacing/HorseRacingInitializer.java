package horseRacing;

import java.util.*;

public class HorseRacingInitializer {


    public List<Horse> initialize() {
        List<Horse> horses = new ArrayList<>();
        double[] probabilities = {5,7,9,11,14,16,18,20};
        double[] shuffleArray = shuffleArray(probabilities);
        for (int i = 1; i < 9; i++) {
            Horse horse = new Horse(i, shuffleArray[i-1]);
            horses.add(horse);
        }
        return horses;
    }


    public static double[] shuffleArray(double[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            double temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
