package cards;

/**
 * Validates card details.
 */
class Validator {
    private static final int MIN_LENGTH = 12;
    private static final int MAX_LENGTH = 19;

    public enum Validity {
        Valid("Valid card"),
        InvalidLength("Invalid PAN length - should be between " + MIN_LENGTH + " and " + MAX_LENGTH),
        AllDigits("A PAN must contain only digits"),
        NoLeadingZeros("A PAN cannot start with a 0"),
        InvalidLuhn("A PAN must have a valid Luhn check-digit");
        private final String message;

        Validity(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

    enum PANValidator {
        ValidLength(Validity.InvalidLength) {
            boolean check(String pan) {
                return pan.length() >= MIN_LENGTH
                        && pan.length() <= MAX_LENGTH;
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
        final Validity validity;

        PANValidator(Validity invalidity) {
            this.validity = invalidity;
        }

        abstract boolean check(String pan);
    }

    static Validity validCard(Card card) {
        Validity validity = Validity.Valid;
        for (PANValidator v : PANValidator.values()) {
            if(!v.check(card.getPAN())){
                validity = v.validity;
            }
        }
        return validity;
    }
}
