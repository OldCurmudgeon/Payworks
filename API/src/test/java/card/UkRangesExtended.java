package card;

import java.util.List;

/**
 * The UK ranges extended with the addition of a new range.
 */
public class UkRangesExtended extends UKRanges {
    enum CustomRange {
        // Special loyalty card range.
        Loyalty(Scheme.Custom, new String[]{"8"}, new int[]{16});
        private final List<Range> ranges;

        CustomRange(Scheme scheme, String[] ranges, int... lengths) {
            this.ranges = makeRanges(scheme, ranges, lengths);
        }
    }

    @Override
    protected List<Range> enhanceRanges(List<Range> ranges) {
        ranges.addAll(CustomRange.Loyalty.ranges);
        return ranges;
    }
}
