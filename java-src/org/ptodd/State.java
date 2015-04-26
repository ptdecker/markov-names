package org.ptodd;

/**
 * Tracks an individual Markov state
 * <p/>
 * A single Markov state has a token and a list of other states that it links to.
 * <p/>
 * Created by ptdecker on 5/25/14.
 */

class State implements Constants {

    private final Character token;
    private final Links links = new Links();

    State(char token) {
        this.token = token;
    }

    public Character getToken() {
        return this.token;
    }

    public String getName() {
        if (getToken() == INITIAL_STATE) {
            return "INITIAL";
        } else if (getToken() == FINAL_STATE) {
            return "FINAL";
        } else {
            return getToken().toString();
        }
    }

    public long getTotalWeight() {
        return this.links.getTotalWeight();
    }

    String getLinksAsString() {
        return this.links.toString();
    }

    public char followRandomLinkToken() {
        return this.links.getWeightedRandomLinkToken();
    }

    public void recordLink(char token) {
        links.recordLink(token);
    }

    @Override
    public String toString() {
        return "State{" +
                "token='" + getName() + "\'," +
                "links={" + getLinksAsString() + "}, " +
                "total=" + getTotalWeight() + "}";
    }
}
