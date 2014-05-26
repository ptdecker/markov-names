package org.ptodd;

/**
 * A Link
 * <p/>
 * A single instance of a link between nodes. The instance stores the token of the target node and the weight of
 * the link.
 * <p/>
 * Created by ptdecker on 5/25/14.
 */

class Link {

    private Character token = Constants.NULL_STATE;  // Unicode null
    private long count = 0;

    Link(char name) {
        this.token = name;
        this.count = 0;
    }

    public Character getToken() {
        return this.token;
    }

    public String getName() {
        if (getToken() == Constants.INITIAL_STATE) {
            return "INITIAL";
        } else if (getToken() == Constants.FINAL_STATE) {
            return "FINAL";
        } else {
            return getToken().toString();
        }
    }

    public void incCount() {
        this.count++;
    }

    public long getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return getName() + " (" + getCount() + ")";
    }
}
