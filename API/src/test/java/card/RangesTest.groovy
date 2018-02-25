package card

import spock.lang.Shared
import spock.lang.Specification

/**
 * Test the Ranges.
 */
class RangesTest extends Specification {
    @Shared Ranges testRanges = new TestRanges();

    def "Lookup works correctly"() {
        expect:
        testRanges.lookup(card).getScheme() == scheme

        where:
        card | scheme
        "4929804463622139"|Scheme.Visa
        "4929804463622138"|Scheme.Visa
        "6762765696545485"|Scheme.Maestro
        "5212132012291762"|Scheme.MasterCard
        "6210948000000029"|Scheme.ChinaUnionPay
    }
}
