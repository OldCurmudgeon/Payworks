package card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Ranges as defined in the task.
 */
public class TestRanges extends Ranges {
    public TestRanges() {
        super(buildRanges());
    }

    enum TestRange {
        Visa(Scheme.Visa, new String[]{
                "4"
        }, IntStream.range(12, 19).toArray()),
        Mastercard(Scheme.MasterCard, new String[]{
                "222100-272099",
                "510000-559999",
        }, IntStream.range(12, 19).toArray()),
        Maestro(Scheme.MaestroUK, new String[]{
                "500000-509999",
                "560000-699999",
        }, IntStream.range(12, 19).toArray()),
        ChinaUnionPay(Scheme.ChinaUnionPay, new String[]{
                "620000-629999",
        }, IntStream.range(12, 19).toArray());
        private final List<Range> ranges;

        TestRange(Scheme scheme, String[] ranges, int... lengths) {
            this.ranges = makeRanges(scheme, ranges, lengths);
        }

    }

    private static List<Range> buildRanges() {
        return Arrays.stream(TestRange.values())
                .map(r -> r.ranges)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}

