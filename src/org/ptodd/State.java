package org.ptodd;

/**
 * Tracks an individual Markov state
 *
 * A single Markov state has a token and a list of other states that it links to.
 *
 * Created by ptdecker on 5/25/14.
 */

class State {

    char token;
    Links links = new Links();
    long totalWeight = 0;

    State(char token) {
        this.token = token;
    }

    public char getName() {
        return this.token;
    }

    public void recordLink(char name) {
        links.recordLink(name);
        this.totalWeight++;
    }

    @Override
    public String toString() {
        return "State{" +
                "token='" + this.token + "\'," +
                "links={" + this.links.toString() + " + " +
                "total=" + this.totalWeight + "}";
    }
}
