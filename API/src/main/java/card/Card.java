package card;

/**
 * Interface for all card.
 */
public interface Card {
    String getPAN();
    Validator.Validity getValidity();
    boolean isValid();
}
