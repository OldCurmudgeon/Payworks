package card;

/**
 * Only getValidity and isValid exposed - all other methods throw InvalidCardException.
 */
public class InvalidCard extends UncheckedCard {
    private final Validator.Validity failure;

    InvalidCard(String pan, Validator.Validity failure) {
        super(pan);
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
