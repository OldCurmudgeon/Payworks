package card;

import utils.Luhn;

/**
 * Validates card details.
 */
public class Validator {
    public static final int MIN_PAN_LENGTH = 12;
    public static final int MAX_PAN_LENGTH = 19;

    private enum PANValidator {
        ValidLength(Validity.InvalidLength) {
            boolean check(String pan) {
                return pan.length() >= MIN_PAN_LENGTH
                        && pan.length() <= MAX_PAN_LENGTH;
            }
        },
        AllDigits(Validity.NonDigits) {
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
        NoLeadingZeros(Validity.LeadingZeros) {
            boolean check(String pan) {
                return pan.charAt(0) != '0';
            }
        },
        ValidLuhn(Validity.InvalidLuhn) {
            boolean check(String pan) {
                return Luhn.check(pan);
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
            if (!v.check(card.getPAN())) {
                validity = v.failure;
                // Detect only the first issue.
                break;
            }
        }
        return validity;
    }
}
