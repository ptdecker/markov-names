package org.ptodd;

/**
 * A Link
 *
 * A single instance of a link between nodes. The instance stores the name of the target node and the weight of
 * the link.
 *
 * Created by ptdecker on 5/25/14.
 */

class Link {

    String name = null;
    long count = 0;

    Link(String name) {
        this.name = name;
        this.count = 1;
    }

    public String getName() {
        return this.name;
    }

    public void incCount() {
        this.count++;
    }

    public long getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return name + " (" + getCount() + ")";
    }
}
