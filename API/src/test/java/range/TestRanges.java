package range;

import card.Range;
import card.Ranges;
import card.Scheme;
import utils.IntervalTree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Paul on 24/02/2018.
 */
public class TestRanges extends Ranges {
    public TestRanges() {
        super(buildRanges());
    }

    enum TestRange {
        /*
         * NB: Although 16-19 are the normal pan-lengths for VISA see note 8 in the PDF referenced above:
         * "VisaScheme operating regulations state that all POS must be able to accept 11-19 digit account numbers that contain a valid six-digit BIN"
         */
        Visa(Scheme.Visa, new String[]{
                "4"
        }, IntStream.range(11, 19).toArray()),
        Mastercard(Scheme.MasterCard, new String[]{
                "222100-272099",
                "510000-559999",
        }, IntStream.range(11, 19).toArray()),
        Maestro(Scheme.Maestro, new String[]{
                "500000-509999",
                "560000-699999",
        }, IntStream.range(11, 19).toArray()),
        ChinaUnionPay(Scheme.ChinaUnionPay, new String[]{
                "620000-629999",
        }, IntStream.range(11, 19).toArray());
        private final List<Range> ranges;

        /*
         * Constructor in this form allows for copy/paste from current IIN ranges documentation.
         */
        TestRange(Scheme scheme, String[] ranges, int... lengths) {
            this.ranges = makeRanges(scheme, ranges, lengths);
        }

    }

    private static IntervalTree<Range> buildRanges() {
        return new IntervalTree<>(Arrays.stream(TestRange.values())
                .map(r -> r.ranges)
                .flatMap(List::stream)
                .collect(Collectors.toList()));
    }
}

