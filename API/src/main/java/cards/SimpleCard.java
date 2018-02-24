package cards;

/**
 * The simplest Card - just a PAN.
 *
 * Used internally to encapsulate a primordial card.
 */
class SimpleCard extends AbstractCard {
    private final String pan;

    SimpleCard(String pan) {
        this.pan = pan;
    }

    public String getPAN() {
        return pan;
    }
}
