package card

import spock.lang.Shared
import spock.lang.Specification

/**
 * Test the CardFactory.
 */
class CardFactoryTest extends Specification {
    @Shared
    Ranges testRanges = new TestRanges();

    def "Build works correctly"() {
        setup:
        CardFactory.setRanges(testRanges)

        expect:
        def card = new CardFactory().setPAN(pan).build();
        card.getValidity() == validity
        card.getScheme() == scheme

        where:
        pan                | scheme               | validity
        "4929804463622139" | Scheme.Visa          | Validity.Valid
        "4929804463622138" | Scheme.Unknown       | Validity.InvalidLuhn
        "6762765696545485" | Scheme.MaestroUK     | Validity.Valid
        "5212132012291762" | Scheme.Unknown       | Validity.InvalidLuhn
        "6210948000000029" | Scheme.ChinaUnionPay | Validity.Valid
        "5212132012291765" | Scheme.MasterCard    | Validity.Valid
        "1234"             | Scheme.Unknown       | Validity.InvalidLength
        "1234_56012334444" | Scheme.Unknown       | Validity.NonDigits
        "0234156012334444" | Scheme.Unknown       | Validity.LeadingZeros
        "34343434343434"   | Scheme.Unknown       | Validity.RangeNotConfigured
    }
}
