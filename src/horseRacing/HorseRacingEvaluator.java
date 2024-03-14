package horseRacing;

import java.util.*;

public class HorseRacingEvaluator {

    private final Random random = new Random();

    public static Horse winningHorse = null;

    public boolean evaluate(List<Horse> horses) {

        for (Horse horse : horses) {
            boolean isAllowedToDoNextStep = generate(horse.getProbability());
            if (isAllowedToDoNextStep) {
                horse.setStepCounter(horse.getStepCounter() + 1);
                horse.setStepWinner(true);
                if (horse.getStepCounter() == 10) {
                    winningHorse = horse;
                }
                return true;
            }

        }
        return false;
    }

    public boolean generate(double probability) {
        Boolean[] stepOpportunities = new Boolean[100];
        Arrays.fill(stepOpportunities, Boolean.FALSE);
        for (int i = 0; i < probability; i++) {
            stepOpportunities[i] = true;
        }
        List<Boolean> shuffledOpportunities = new ArrayList<>(Arrays.asList(stepOpportunities));
        Collections.shuffle(shuffledOpportunities);
        int randomIndex = random.nextInt(10);
        return shuffledOpportunities.get(randomIndex);
    }

    public String displaySteps(Horse horse) {
        StringBuilder stepString = new StringBuilder();
        for (int i = 0; i < horse.getStepCounter(); i++) {
            stepString.append(":::|");
        }
        return stepString.toString();
    }
}
