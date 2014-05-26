package org.ptodd;

/**
 * Created by ptdecker on 5/25/14.
 */
class State {

    String name;
    Links links = new Links();

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void recordLink(String name) {
        links.recordLink(name);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("State{")
                .append("name='" + this.name + "\',links={")
                .append(this.links.toString())
                .append("}}")
                .toString();
    }
}
