package card;

import utils.IntervalTree;

/**
 * Represents a card range associated with a scheme.
 *
 * These are used in the Interval tree for lookups.
 */
public class Range implements IntervalTree.Interval {
    private final Scheme scheme;
    private final long start;
    private final long end;

    public Range(Scheme scheme, long start, long end) {
        this.scheme = scheme;
        this.start = start;
        this.end = end;
    }

    public long getLength() {
        return end + 1L - start;
    }

    public Scheme getScheme() {
        return scheme;
    }

    @Override
    public long getStart() {
        return start;
    }

    @Override
    public long getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Range range = (Range) o;

        if (start != range.start) return false;
        if (end != range.end) return false;
        return scheme.equals(range.scheme);

    }

    @Override
    public int hashCode() {
        int result = scheme.hashCode();
        result = 31 * result + (int) (start ^ (start >>> 32));
        result = 31 * result + (int) (end ^ (end >>> 32));
        return result;
    }
}
