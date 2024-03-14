package blackjack.card;

public class Card {

    private final Symbol symbol;
    private final Value value;

    public Card(Symbol symbol, Value value) {
        this.symbol = symbol;
        this.value = value;
    }


    public Symbol getSymbol() {
        return symbol;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return symbol + " " + value;
    }

    public String toString(boolean isBlurred) {
        if (isBlurred) {
            return ", ###########";
        } else {
            return toString();
        }
    }
}
