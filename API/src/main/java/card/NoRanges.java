package card;

import utils.IntervalTree;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Paul on 24/02/2018.
 */
class NoRanges extends Ranges {
    NoRanges() {
        super(new IntervalTree<>(Arrays.asList(new Range(Scheme.Unknown,0,Long.MAX_VALUE))));
    }
}
