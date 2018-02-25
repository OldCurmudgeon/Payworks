package card;

/**
 * Base functionality for all cards.
 */
abstract class AbstractCard implements Card {
    /**
     * Default all cards to Valid.
     *
     * @return A {@link Validity#Valid}.
     */
    public Validity getValidity() {
        return Validity.Valid;
    }

    /**
     * Default all cards to Valid.
     *
     * @return true.
     */
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return "Card{PAN=" + getPAN() + " Scheme=" + getScheme() + " Validity=" + getValidity();
    }
}
