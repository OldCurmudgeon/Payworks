package cards;

/**
 * Validates and builds cards.
 *
 * If any of the details of the card are invalid then an InvalidCard will be built.
 */
public class CardFactory {
    private String pan;

    public void setPAN(String pan) {
        this.pan = pan;
    }

    public Card build() {
        // Start with a simple card.
        Card card = new SimpleCard(pan);
        // Is it valid?.
        Validator.Validity validity = Validator.validCard(card);
        if (validity == Validator.Validity.Valid) {
            // TODO - Lookup card in Ranges to build
        } else {
            // No - create an Invalid card.
            card = new InvalidCard(card, validity);
        }
        return card;
    }
}
