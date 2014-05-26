package org.ptodd;

/**
 * Tracks an individual Markov state
 * <p/>
 * A single Markov state has a token and a list of other states that it links to.
 * <p/>
 * Created by ptdecker on 5/25/14.
 */

class State {

    private Character token;
    private Links links = new Links();
    private long totalWeight = 0;

    State(char token) {
        this.token = token;
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

    public long getTotalWeight() {
        return this.totalWeight;
    }

    public String getLinksAsString() {
        return this.links.toString();
    }

    public void recordLink(char token) {
        links.recordLink(token);
        this.totalWeight++;
    }

    @Override
    public String toString() {
        return "State{" +
                "token='" + getName() + "\'," +
                "links={" + getLinksAsString() + "}, " +
                "total=" + getTotalWeight() + "}";
    }
}
