package card;

/**
 * Interface for all card.
 */
public interface Card {

    /**
     * Get the PAN of the card.
     *
     * @return The PAN for the card.
     */
    String getPAN();

    /**
     * Get the validity of the card.
     *
     * @return A {@link card.Validator.Validity#} for the card.
     */
    Validator.Validity getValidity();

    /**
     * Quick test for validity of the card.
     *
     * @return true if the card is valid.
     */
    boolean isValid();

    /**
     * Get the scheme associated with this card.
     *
     * @return The {@Link card.Scheme#} of the card.
     */
    Scheme getScheme();
}
