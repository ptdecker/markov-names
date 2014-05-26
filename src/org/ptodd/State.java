package org.ptodd;

/**
 * Tracks an individual Markov state
 *
 * A single Markov state has a name and a list of other states that it links to.
 *
 * Created by ptdecker on 5/25/14.
 */

class State {

    String name;
    Links links = new Links();
    long totalWeight = 0;

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void recordLink(String name) {
        links.recordLink(name);
        this.totalWeight++;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + this.name + "\'," +
                "links={" + this.links.toString() + " + " +
                "total=" + this.totalWeight + "}";
    }
}
