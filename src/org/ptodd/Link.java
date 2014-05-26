package org.ptodd;

/**
 * Created by ptdecker on 5/25/14.
 */
class Link {

    State toState = null;
    long count = 0;

    Link(State S) {
        this.toState = S;
    }

    public State getToState() {
        return toState;
    }

    public void incCount() {
        this.count++;
    }

    public long getCount() {
        return this.count;
    }
}
