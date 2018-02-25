package card;

/**
 * Represents an invalid card.
 *
 * Only getValidity and isValid exposed - all other methods should throw InvalidCardException.
 */
public class InvalidCard extends UncheckedCard {
    private final Validity failure;

    InvalidCard(String pan, Validity failure) {
        super(pan);
        this.failure = failure;
    }

    @Override
    public Validity getValidity() {
        return failure;
    }

    public boolean isValid() {
        return false;
    }
}
