package org.ptodd;

import java.util.ArrayList;

/**
 * Manages a set of links to other states
 *
 * Provides methods to manage a set of links. Each link records the name of the state and the accumulated weight
 * of the state. The 'recordLink' method will either add a new link if a link of the specified name does not already
 * exist. If it does, the the weight of the link is increased by one.
 *
 * Created by ptdecker on 5/25/14.
 */

public class Links {

    ArrayList<Link> links = new ArrayList<Link>();

    public Links() {
    }

    private Link findLink(String name) {
        for (Link link : links) {
            if (link.getName().equals(name)) {
                return link;
            }
        }
        return null;
    }

    public void recordLink(String name) {
        Link link = findLink(name);
        if (link == null) {
            links.add(new Link(name));
        } else {
            link.incCount();
        }
    }

    @Override
    public String toString() {
        StringBuilder linksStr = new StringBuilder("'");
        for (int i = 0; i < links.size(); i++) {
            linksStr.append((i > 0) ? "','" : "");
            linksStr.append(links.get(i).toString());
        }
        return linksStr.append("'").toString();
    }
}
