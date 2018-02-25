package card;

/**
 * Details the possible validities of a card.
 */
public enum Validity {
    Valid(null),
    InvalidLength("Invalid PAN length - should be between " + Validator.MIN_PAN_LENGTH + " and " + Validator.MAX_PAN_LENGTH),
    NonDigits("A PAN must contain only digits"),
    LeadingZeros("A PAN cannot start with a 0"),
    InvalidLuhn("A PAN must have a valid Luhn check-digit"),
    RangeNotConfigured("Card range not configured");
    private final String message;

    Validity(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return name() + (message == null ? "" : ":" + message);
    }
}
