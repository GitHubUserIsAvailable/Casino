package horseRacing;

public class Horse {
    private int id;

    private double probability;

    private int stepCounter;

    private boolean stepWinner;

    public Horse(int id, double probability) {
        this.id = id;
        this.probability = probability;
        this.stepCounter = 0;
    }

    public int getId() {
        return id;
    }

    public double getProbability() {
        return probability;
    }

    public int getStepCounter() {
        return stepCounter;
    }

    public void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }

    public boolean isStepWinner() {
        return stepWinner;
    }

    public void setStepWinner(boolean stepWinner) {
        this.stepWinner = stepWinner;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "id=" + id +
                ", probability=" + probability +
                '}';
    }
}
