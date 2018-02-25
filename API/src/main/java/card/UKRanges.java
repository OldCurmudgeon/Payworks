package card;

import utils.IntervalTree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Current set of UK IIN Ranges.
 *
 * Derived from https://www.barclaycard.co.uk/business/files/BIN-Rules-UK.pdf
 *
 * NB: In a mature environment this enum should be replaced by reading the details from properties.
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
        }, IntStream.range(11, 19).toArray()),
        MasterCard(Scheme.MasterCard, new String[]{
                "510000-559999",
                "222100-272099",
        }, new int[]{16}),
        JCB(Scheme.JCB, new String[]{
                "352800-358999",
        }, IntStream.range(16, 19).toArray()),
        MaestroUK(Scheme.MaestroUK, new String[]{
                "675900-675999",
                "676770-676774",
        }, IntStream.range(12, 19).toArray()),
        MaestroIntl(Scheme.MaestroIntl, new String[]{
                "50",
                "56-69",
        }, IntStream.range(12, 19).toArray()),;
        private final List<Range> ranges;

        UKRange(Scheme scheme, String[] ranges, int... lengths) {
            this.ranges = makeRanges(scheme, ranges, lengths);
        }

    }

    private static IntervalTree<Range> buildRanges() {
        List<Range> allRanges = Arrays.stream(UKRange.values())
                // The ranges field of each enum.
                .map(e -> e.ranges)
                // Flatten them all into one list.
                .flatMap(List::stream)
                // Gather them up into a list.
                .collect(Collectors.toList());
        return new IntervalTree<>(allRanges);
    }
}
