package cards;

/**
 * Only getValidity and isValid exposed - all other methods throw InvalidCardException.
 */
public class InvalidCard extends SimpleCard {
    final Validator.Validity failure;

    InvalidCard(Card card, Validator.Validity failure) {
        super(card.getPAN());
        this.failure = failure;
    }

    @Override
    public Validator.Validity getValidity() {
        return failure;
    }

    public boolean isValid() {
        return false;
    }
}
