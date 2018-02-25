package card;

import java.util.Arrays;

/**
 * An empty set of ranges for internal use.
 */
class NoRanges extends Ranges {
    NoRanges() {
        // One single unknown range.
        super(Arrays.asList(new Range(Scheme.Unknown, 0, Long.MAX_VALUE)));
    }
}
