package org.ptodd;

import java.util.ArrayList;

/**
 * Created by ptdecker on 5/25/14.
 */
class State {

    String name;
    ArrayList<Link> links;

    State() {

    }

    State(String name) {
        this.name = name;
        this.links = new ArrayList<Link>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addLink(String name) {

    }

    public String getLinksAsString() {
        StringBuilder linksAsString = new StringBuilder("No Links");
        if (links.size() > 0) {
            linksAsString.delete(0, linksAsString.length());
            for (Link oneLink : links) {
                linksAsString.append(oneLink.toString());
            }
        }
        return linksAsString.toString();
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + "\'," +
                "Links={" + getLinksAsString() + "}" +
                '}';
    }

}
