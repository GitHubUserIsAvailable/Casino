package horseRacing;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class HorseGameUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static void takeInput(Consumer<Integer> setter, Supplier<Integer> getter, String prompt, String validationError, String validationSuccess, Integer minimumNumber) {
        boolean validHorseInput = false;

        while (!validHorseInput) {
            System.out.println(prompt);
            try {
                Integer userInput = Integer.parseInt(scanner.nextLine());
                setter.accept(
                        userInput
                );
                if (getter.get() < minimumNumber) {
                    validHorseInput = true;
                    System.out.printf((validationSuccess) + "%n", userInput);
                } else {
                    System.out.println(validationError);
                }
            } catch (NumberFormatException e) {
                System.out.println("Not valid input");
            }
        }
    }

}
