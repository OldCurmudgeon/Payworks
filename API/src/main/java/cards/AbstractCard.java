package cards;

/**
 * Base functionality for all cards.
 */
abstract class AbstractCard implements Card {
    public Validator.Validity getValidity() {
        return Validator.Validity.Valid;
    }

    public boolean isValid() {
        return true;
    }
}
