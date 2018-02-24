package cards;

/**
 * Interface for all cards.
 */
public interface Card {
    String getPAN();
    Validator.Validity getValidity();
    boolean isValid();
}
