package card;

import utils.IntervalTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Manages card range and PAN lookups.
 *
 * The lookup process queries the ree to find all ranges containing the supplied PAN.
 *
 * The narrowest range found is then chosen for the scheme of the card.
 *
 * TODO: Decide what to do if there are two matching ranges of the shortest length.
 * TODO: Add exclusion ranges.
 */
public abstract class Ranges {
    // The interval tree of all ranges.
    private final IntervalTree<Range> tree;
    // Hits are sorted smallest-first.
    private static final Comparator<? super Range> RANGE_LENGTH_ASCENDING =
            (Comparator<Range>) (r1, r2) -> Long.signum(r1.getLength() - r2.getLength());
    // This is returned if no ranges found that enclose the PAN.
    private static final Range UNCONFIGURED_RANGE = new Range(Scheme.Unknown, 0, 0);

    /**
     * Construct the Ranges object with a tree of ranges to use.
     *
     * @param ranges - The ranges to use.
     */
    protected Ranges(List<Range> ranges) {
        this.tree = new IntervalTree<>(enhanceRanges(ranges));
    }

    /**
     * Allow for enhancing te range list befor the tree is constructed.
     *
     * @param ranges - The ranges about to be added.
     * @return - The enhanced range list.
     */
    protected List<Range> enhanceRanges(List<Range> ranges) {
        // By default, just pass it through.
        return ranges;
    }

    /**
     * Lookup a PAN in the tree.
     *
     * @param pan - The PAN to look up.
     * @return The range it resides in or UNCONFIGURED_RANGE if not found.
     */
    public Range lookup(String pan) {
        return tree.query(Long.parseLong(pan))
                .stream()
                // Sort it ascending.
                .sorted(RANGE_LENGTH_ASCENDING)
                // Grab the smallest range.
                .findFirst()
                // Or it is not configured.
                .orElse(UNCONFIGURED_RANGE);
    }

    /**
     * Lookup a batch of PANs.
     *
     * @param pans - The pans to lookup.
     * @return A list of ranges, one for each PAN in the order supplied.
     */
    public List<Range> lookup(String[] pans) {
        return lookup(Arrays.stream(pans))
                .collect(Collectors.toList());
    }

    /**
     * Lookup a batch of PANs.
     *
     * @param pans - The pans to lookup.
     * @return A list of ranges, one for each PAN in the order supplied.
     */
    public List<Range> lookup(Collection<String> pans) {
        return pans.stream()
                .map(pan -> lookup(pan))
                .collect(Collectors.toList());
    }

    /**
     * Lookup a stream of PANs.
     *
     * @param pans - The pans to lookup.
     * @return A stream of ranges for the PANs.
     */
    public Stream<Range> lookup(Stream<String> pans) {
        return pans.map(pan -> lookup(pan));
    }

    /**
     * Utility method for building lists of ranges for installing in the interval tree.
     *
     * All supplied ranges for all supplied lengths are included in the result.
     *
     * See makeRange(card.Scheme, java.lang.String, int) for details of the format of the range parameter.
     *
     * @param scheme  - The scheme to supply to the ranges.
     * @param ranges  - The ranges to use.
     * @param lengths - The PAN length to use.
     * @return A list of build ranges.
     */
    protected static List<Range> makeRanges(Scheme scheme, String[] ranges, int[] lengths) {
        return IntStream.of(lengths)
                // Make the ranges lists.
                .mapToObj(length -> makeRanges(scheme, ranges, length))
                // Flatten them.
                .flatMap(List::stream)
                // We wan a list.
                .collect(Collectors.toList());
    }

    /**
     * As makeRanges(card.Scheme, java.lang.String[], int[]) but for just one length
     *
     * @param scheme - The scheme to supply to the ranges.
     * @param ranges - The ranges to use.
     * @param digits - The PAN length to use.
     * @return A list of build ranges.
     */
    protected static List<Range> makeRanges(Scheme scheme, String[] ranges, int digits) {
        return Arrays.stream(ranges)
                // Make all ranges - one per range string.
                .map(r -> makeRange(scheme, r, digits))
                // Gather them up into a list.
                .collect(Collectors.toList());
    }

    // String of "0"s
    private static final String ZEROS = String.join("", Collections.nCopies(Validator.MAX_PAN_LENGTH, "0"));
    // String of "9"s
    private static final String NINES = String.join("", Collections.nCopies(Validator.MAX_PAN_LENGTH, "9"));

    /**
     * Makes a range from details supplied in the range parameter.
     *
     * The range parameter takes the form generally used in IIN range documentation.
     *
     * 123456-9 = First six digits are between 123456 and 123459 inclusive.
     * 4 = Equivalent to 4-4.
     *
     * The start and end of the range are calculated by taking the base digits and padding them out to the right
     * to achieve the desired length. The start range is padded with "0", the end range with "9".
     *
     * @param scheme - The scheme to attach to the range.
     * @param range  - The range string to interpret.
     * @param digits - The number of digits in the pan.
     * @return A Range configured to match PANS of the specified number of digits in the specified range.
     */
    public static Range makeRange(Scheme scheme, String range, int digits) {
        String[] parts = range.split("-");
        // "n" means "n-n"
        if (parts.length < 2) {
            parts = new String[]{parts[0], parts[0]};
        }
        // "123456-9" means "123456-123459"
        if (parts[0].length() > parts[1].length()) {
            parts[1] = parts[0].substring(0, parts[0].length() - parts[1].length()) + parts[1];
        }
        // Start is padded with zeros.
        long start = Long.parseLong((parts[0] + ZEROS).substring(0, digits));
        // End is padded with nines.
        long end = Long.parseLong((parts[1] + NINES).substring(0, digits));
        // Make the range.
        return new Range(scheme, start, end);
    }
}
