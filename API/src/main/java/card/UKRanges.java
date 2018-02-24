package card;

import utils.IntervalTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Current set of UK IIN Ranges.
 *
 * Derived from https://www.barclaycard.co.uk/business/files/BIN-Rules-UK.pdf
 *
 * TODO: Complete the configuration for all UK card ranges.
 */
public class UKRanges extends Ranges {
    public UKRanges() {
        super(buildRanges());
    }

    enum UKRange {
        /*
         * NB: Although 16-19 are the normal pan-lengths for VISA see note 8 in the PDF referenced above:
         * "VisaScheme operating regulations state that all POS must be able to accept 11-19 digit account numbers that contain a valid six-digit BIN"
         */
        Visa(Scheme.Visa, new String[]{"4"}, IntStream.range(11, 19).toArray()),
        VisaElectron(Scheme.VisaElectron, new String[]{
                "400115",
                "400837-9",
                "412921-3",
                "419740-1",
                "419773-75",
                "424519",
                "424962-3",
                "437860",
                "444000",
                "459472",
                "481887-90",
                "484406-11",
                "484413-14",
                "484418-26",
                "484428-55",
                "491730-59",
        }, IntStream.range(11, 19).toArray()),
        VisaPurchase(Scheme.VisaPurchase, new String[]{
                "448400-448699",
                "471500-99",
        }, IntStream.range(11, 19).toArray());
        private final List<Range> ranges;

        /*
         * Constructor in this form allows for copy/paste from current IIN range documentation.
         */
        UKRange(Scheme scheme, String[] range, int... lengths) {
            ranges = new ArrayList<>();
            for (int length : lengths) {
                ranges.addAll(makeRanges(scheme, range, length));
            }
        }

    }

    private static IntervalTree<Range> buildRanges() {
        List<Range> allRanges = new ArrayList<>();
        for (UKRange range : UKRange.values()) {
            allRanges.addAll(range.ranges);
        }
        return new IntervalTree<>(allRanges);
    }
}
