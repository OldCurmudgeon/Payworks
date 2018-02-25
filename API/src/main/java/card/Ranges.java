package card;

import utils.IntervalTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Manages card range and PAN lookups.
 */
public abstract class Ranges {
    private final IntervalTree<Range> tree;
    private static final Comparator<? super Range> RANGE_LENGTH_ASCENDING =
            (Comparator<Range>) (r1, r2) -> Long.signum(r1.getLength() - r2.getLength());
    private static final Range UNCONFIGURED_RANGE = new Range(Scheme.Unknown, 0, 0);

    protected Ranges(IntervalTree<Range> tree) {
        this.tree = tree;
    }

    public Range lookup(String pan) {
        return tree.query(Long.parseLong(pan))
                .stream()
                // Sort it ascending.
                .sorted(RANGE_LENGTH_ASCENDING)
                // Grab the smallest range.
                .findFirst()
                .orElse(UNCONFIGURED_RANGE);
    }

    public List<Range> lookup(String[] pans) {
        return lookup(Arrays.stream(pans))
                .collect(Collectors.toList());
    }

    public List<Range> lookup(Collection<String> pans) {
        return pans.stream()
                .map(pan -> lookup(pan))
                .collect(Collectors.toList());
    }

    public Stream<Range> lookup(Stream<String> pans) {
        return pans.map(pan -> lookup(pan));
    }

    protected static List<Range> makeRanges(Scheme scheme, String[] ranges, int[] lengths) {
        return IntStream.of(lengths)
                .mapToObj(length -> makeRanges(scheme, ranges, length))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    protected static List<Range> makeRanges(Scheme scheme, String[] ranges, int digits) {
        return Arrays.stream(ranges)
                // Make all ranges - one per digit
                .map(r -> makeRange(scheme, r, digits))
                // Gather them up into a list.
                .collect(Collectors.toList());
    }

    // String of "0"s
    private static final String ZEROS = String.join("", Collections.nCopies(Validator.MAX_PAN_LENGTH, "0"));
    // String of "9"s
    private static final String NINES = String.join("", Collections.nCopies(Validator.MAX_PAN_LENGTH, "9"));

    private static Range makeRange(Scheme scheme, String range, int digits) {
        String[] parts = range.split("-");
        // "n" means "n-n"
        if (parts.length < 2) {
            parts = new String[]{parts[0], parts[0]};
        }
        long start = Long.parseLong((parts[0] + ZEROS).substring(0, digits));
        long end = Long.parseLong((parts[1] + NINES).substring(0, digits));
        return new Range(scheme, start, end);
    }
}
