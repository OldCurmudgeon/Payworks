package card;

import utils.IntervalTree;

import java.util.Collections;

/**
 * Created by Paul on 24/02/2018.
 */
public class NoRanges extends Ranges {
    public NoRanges() {
        super(new IntervalTree<>(Collections.emptyList()));
    }
}
