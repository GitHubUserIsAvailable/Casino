package card;

import java.util.Optional;

public enum Value {
    TWO(new CardValuePair(2, Optional.empty())),
    THREE(new CardValuePair(3, Optional.empty())),
    FOUR(new CardValuePair(4, Optional.empty())),
    FIVE(new CardValuePair(5, Optional.empty())),
    SIX(new CardValuePair(6, Optional.empty())),
    SEVEN(new CardValuePair(7, Optional.empty())),
    EIGHT(new CardValuePair(8, Optional.empty())),
    NINE(new CardValuePair(9, Optional.empty())),
    TEN(new CardValuePair(10, Optional.empty())),
    JACK(new CardValuePair(10, Optional.empty())),
    QUEEN(new CardValuePair(10, Optional.empty())),
    KING(new CardValuePair(10, Optional.empty())),
    ACE(new CardValuePair(11, Optional.of(1))),
    ;

    private final CardValuePair cardValuePair;

    Value(CardValuePair cardValuePair) {
        this.cardValuePair = cardValuePair;
    }

    public CardValuePair getCardValuePair() {
        return cardValuePair;
    }
}
