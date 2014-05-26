package org.ptodd;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manages a set of links to other states
 *
 * Provides methods to manage a set of links. Each link records the token of the state and the accumulated weight
 * of the state. The 'recordLink' method will either add a new link if a link of the specified token does not already
 * exist. If it does, the the weight of the link is increased by one.
 *
 * Created by ptdecker on 5/25/14.
 */

public class Links {

    private Random randomNumberGenerator;
    private ArrayList<Link> links;

    public Links() {
        links = new ArrayList<Link>();
        randomNumberGenerator = new Random();
    }

    private Link findLink(char token) {
        for (Link link : links) {
            if (link.getToken() == token) {
                return link;
            }
        }
        return null;
    }

    public char getRandomLinkToken() {
        int index = randomNumberGenerator.nextInt(links.size());
        return links.get(index).getToken();
    }

    public void recordLink(char token) {
        if (findLink(token) == null) {
            links.add(new Link(token));
        }
        findLink(token).incCount();
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
