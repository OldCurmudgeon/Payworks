package card

import spock.lang.Specification

/**
 * Test the UK ranges
 */
class UKRangesTest extends Specification {

    def "UK Ranges are good"() {
        setup:
        Ranges ukRanges = new UKRanges();
        CardFactory.setRanges(ukRanges)

        expect:
        def card = new CardFactory().setPAN(pan).build();
        card.getScheme() == scheme

        where:
        pan                | scheme
        "4929804463622139" | Scheme.Visa
        "4929804463622138" | Scheme.Unknown
        "6762765696545485" | Scheme.MaestroIntl
        "5212132012291762" | Scheme.Unknown
        "6759000000000000" | Scheme.MaestroUK
        "4129210000000003" | Scheme.VisaElectron
        "4129219999999992" | Scheme.VisaElectron
        "4129219999999992" | Scheme.VisaElectron
        "412921000003"     | Scheme.VisaElectron
    }

    def "Custom ranges are good"() {
        setup:
        Ranges ranges = new UkRangesExtended();
        CardFactory.setRanges(ranges)

        expect:
        def card = new CardFactory().setPAN(pan).build();
        card.getScheme() == scheme

        where:
        pan                | scheme
        "8000000000000003" | Scheme.Custom
    }

}
