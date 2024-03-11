package card;

public class Card {

    private Symbol symbol;
    private Value value;

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
        return "Card{" +
                "symbol=" + symbol +
                ", value=" + value +
                '}';
    }
}
