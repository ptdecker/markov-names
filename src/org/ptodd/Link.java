package org.ptodd;

/**
 * A Link
 *
 * A single instance of a link between nodes. The instance stores the token of the target node and the weight of
 * the link.
 *
 * Created by ptdecker on 5/25/14.
 */

class Link {

    char token = '\u0000';  // Unicode null
    long count = 0;

    Link(char name) {
        this.token = name;
        this.count = 1;
    }

    public char getToken() {
        return this.token;
    }

    public void incCount() {
        this.count++;
    }

    public long getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return (token == Constants.INITIAL_STATE) ? "INITIAL" : ((token == Constants.FINAL_STATE) ? "FINAL" : token)
                + " (" + getCount() + ")";
    }
}
