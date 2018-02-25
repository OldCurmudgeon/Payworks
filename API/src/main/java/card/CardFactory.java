package card;

/**
 * Validates and builds card.
 *
 * If any of the details of the card are invalid then an InvalidCard will be built.
 */
public class CardFactory {
    // Default to no ranges. Use setRanges to to pick Ranges to check.
    private static Ranges currentRanges = new NoRanges();
    // Collect the configured ranges on construction.
    private final Ranges ranges = currentRanges;
    // Parameters of the Card to be use on build.
    private String pan;

    public static void setRanges(Ranges ranges) {
        currentRanges = ranges;
    }

    public CardFactory setPAN(String pan) {
        this.pan = pan;
        return this;
    }

    public Card build() {
        // Start with a simple card.
        Card card = new UncheckedCard(pan);
        // Is it valid?.
        Validator.Validity validity = Validator.validCard(card);
        if (validity == Validator.Validity.Valid) {
            Range range = ranges.lookup(card.getPAN());
            if (range.getScheme() != Scheme.Unknown) {
                // Good card.
                card = new ValidCard(pan, range.getScheme());
            } else {
                // Range not found.
                card = new InvalidCard(pan, Validator.Validity.RangeNotConfigured);
            }
        } else {
            // No! Create an Invalid card.
            card = new InvalidCard(pan, validity);
        }
        return card;
    }
}