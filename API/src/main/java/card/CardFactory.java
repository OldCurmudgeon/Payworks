package card;

/**
 * Validates and builds card.
 *
 * If any of the details of the card are invalid then an InvalidCard will be built.
 */
public class CardFactory {
    // Default to no ranges. Use setRanges to install the ranges to check.
    private static Ranges currentRanges = new NoRanges();
    // Collect the configured ranges on construction.
    private final Ranges ranges = currentRanges;
    // Parameters of the Card to be used at build time.
    private String pan;

    /**
     * Set's teh current ranges to be used for card building.
     *
     * @param ranges - The ranges to use.
     */
    public static void setRanges(Ranges ranges) {
        currentRanges = ranges;
    }

    /**
     * Set the PAN to use when the card is built.
     *
     * @param pan - The PAN to use.
     * @return this for fluent construction.
     */
    public CardFactory setPAN(String pan) {
        this.pan = pan;
        return this;
    }

    /**
     * Validates and builds a Card from the accumulated parameters.
     *
     * If any of the card details are invalid then an InvalidCard is built.
     *
     * @return A Card object.
     */
    public Card build() {
        // Start with a simple card.
        Card card = new UncheckedCard(pan);
        // Is it valid?.
        Validity validity = Validator.validCard(card);
        if (validity == Validity.Valid) {
            // Look up it's scheme.
            Range range = ranges.lookup(card.getPAN());
            if (range.getScheme() != Scheme.Unknown) {
                // Good card.
                card = new ValidCard(pan, range.getScheme());
            } else {
                // Range not found.
                card = new InvalidCard(pan, Validity.RangeNotConfigured);
            }
        } else {
            // Invalid PAN. Create an Invalid card.
            card = new InvalidCard(pan, validity);
        }
        return card;
    }
}
