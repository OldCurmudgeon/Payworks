package card;

import utils.Luhn;

/**
 * Validates card details.
 */
public class Validator {
    public static final int MIN_PAN_LENGTH = 12;
    public static final int MAX_PAN_LENGTH = 19;

    public enum Validity {
        Valid("Valid card"),
        InvalidLength("Invalid PAN length - should be between " + MIN_PAN_LENGTH + " and " + MAX_PAN_LENGTH),
        AllDigits("A PAN must contain only digits"),
        NoLeadingZeros("A PAN cannot start with a 0"),
        InvalidLuhn("A PAN must have a valid Luhn check-digit"),
        RangeNotConfigured("Card range not configured");
        private final String message;

        Validity(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

    private enum PANValidator {
        ValidLength(Validity.InvalidLength) {
            boolean check(String pan) {
                return pan.length() >= MIN_PAN_LENGTH
                        && pan.length() <= MAX_PAN_LENGTH;
            }
        },
        AllDigits(Validity.AllDigits) {
            boolean check(String pan) {
                boolean valid = true;
                for (int i = 0; i < pan.length() && valid; i++) {
                    if (!Character.isDigit(pan.charAt(i))) {
                        valid = false;
                    }
                }
                return valid;
            }
        },
        NoLeadingZeros(Validity.NoLeadingZeros) {
            boolean check(String pan) {
                return pan.charAt(0) != '0';
            }
        },
        ValidLuhn(Validity.InvalidLuhn) {
            boolean check(String pan) {
                return Luhn.validate(pan);
            }
        };
        final Validity failure;

        PANValidator(Validity failure) {
            this.failure = failure;
        }

        abstract boolean check(String pan);
    }

    static Validity validCard(Card card) {
        Validity validity = Validity.Valid;
        for (PANValidator v : PANValidator.values()) {
            if(!v.check(card.getPAN())){
                validity = v.failure;
            }
        }
        return validity;
    }
}
