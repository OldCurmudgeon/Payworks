package card;

/**
 * The simplest Card - just a PAN.
 *
 * Used internally to encapsulate a primordial card.
 */
class UncheckedCard extends AbstractCard {
    private final String pan;

    UncheckedCard(String pan) {
        this.pan = pan;
    }

    public String getPAN() {
        return pan;
    }
}
