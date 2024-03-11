package card;

import java.util.Optional;

public class CardValuePair {
    private int value;
    private Optional<Integer> secondValue;

    public CardValuePair(int value, Optional<Integer> secondValue) {
        this.value = value;
        this.secondValue = secondValue;
    }

    public int getValue() {
        return value;
    }

    public Optional<Integer> getSecondValue() {
        return secondValue;
    }
}
