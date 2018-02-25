package card;

/**
 * Base functionality for all cards.
 */
abstract class AbstractCard implements Card {
    /**
     * Default all cards to Valid.
     *
     * @return A {@link card.Validator.Validity#Valid}.
     */
    public Validator.Validity getValidity() {
        return Validator.Validity.Valid;
    }

    /**
     * Default all cards to Valid.
     *
     * @return true.
     */
    public boolean isValid() {
        return true;
    }
}
