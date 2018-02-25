package card;

/**
 * The simplest Card - just a PAN.
 *
 * Used internally to encapsulate a primordial card.
 */
class UncheckedCard extends AbstractCard {
    private final String pan;
    private final Scheme scheme;

    UncheckedCard(String pan) {
        this(pan, Scheme.Unknown);
    }

    protected UncheckedCard(String pan, Scheme scheme) {
        this.pan = pan;
        this.scheme = scheme;
    }

    public String getPAN() {
        return pan;
    }

    public Scheme getScheme() {
        return scheme;
    }
}
