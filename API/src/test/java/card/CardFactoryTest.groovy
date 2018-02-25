package card

import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Paul on 24/02/2018.
 */
class CardFactoryTest extends Specification {
    @Shared Ranges testRanges = new TestRanges();

    def setup() {
        CardFactory.setRanges(testRanges)
    }

    def "Build works correctly"() {
        expect:
        new CardFactory().setPAN(pan).build().getValidity() == validity

        where:
        pan | validity
        "4929804463622139"|Validator.Validity.Valid
        "4929804463622138"|Validator.Validity.InvalidLuhn
        "6762765696545485"|Validator.Validity.Valid
        "5212132012291762"|Validator.Validity.InvalidLuhn
        "6210948000000029"|Validator.Validity.Valid
        "1234"|Validator.Validity.InvalidLength
        "1234_56012334444"|Validator.Validity.AllDigits
        "0234156012334444"|Validator.Validity.NoLeadingZeros
        "34343434343434"|Validator.Validity.RangeNotConfigured

    }
}
