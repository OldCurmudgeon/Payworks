package card;

/**
 * Base functionality for all card.
 */
abstract class AbstractCard implements Card {
    public Validator.Validity getValidity() {
        return Validator.Validity.Valid;
    }

    public boolean isValid() {
        return true;
    }
}
