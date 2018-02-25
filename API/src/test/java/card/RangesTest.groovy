package card

import spock.lang.Specification

/**
 * Test the Ranges.
 */
class RangesTest extends Specification {

    def "Range string interpretation ok"() {
        expect:
        Range r = Ranges.makeRange(Scheme.Unknown, range, 6)
        r.getLength() == length

        where:
        range     | length
        "4"       | 100000
        "444-6"   | 3000
        "444-446" | 3000
    }

    def "Bad range rejected"() {
        when:
        Ranges.makeRange(Scheme.Unknown, "444-333", 6)

        then:
        thrown(RuntimeException)
    }

    def "Lookup works correctly"() {
        setup:
        Ranges testRanges = new TestRanges();

        expect:
        testRanges.lookup(card).getScheme() == scheme

        where:
        card               | scheme
        "4929804463622139" | Scheme.Visa
        "4929804463622138" | Scheme.Visa
        "6762765696545485" | Scheme.MaestroUK
        "5212132012291762" | Scheme.MasterCard
        "6210948000000029" | Scheme.ChinaUnionPay
    }

}
