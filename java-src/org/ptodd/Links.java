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

class Links {

    private final Random randomNumberGenerator;
    private final ArrayList<Link> links;
    private int totalWeight;

    public Links() {
        this.links = new ArrayList<Link>();
        this.randomNumberGenerator = new Random();
        this.totalWeight = 0;
    }

    private Link findLink(char token) {
        for (Link link : this.links) {
            if (link.getToken() == token) {
                return link;
            }
        }
        return null;
    }

    public int getTotalWeight() {
        return this.totalWeight;
    }

    public char getWeightedRandomLinkToken() {
        int index = randomNumberGenerator.nextInt(getTotalWeight());
        int cumulativeWeight = 0;
        int i = 0;
        do {
            cumulativeWeight += this.links.get(i).getCount();
            i++;
        } while ((cumulativeWeight - 1) < index);
        return links.get(i - 1).getToken();
    }

    public void recordLink(char token) {
        if (findLink(token) == null) {
            this.links.add(new Link(token));
        }
        findLink(token).incCount();
        this.totalWeight++;
    }

    @Override
    public String toString() {
        StringBuilder linksStr = new StringBuilder("'");
        for (int i = 0; i < this.links.size(); i++) {
            linksStr.append((i > 0) ? "','" : "");
            linksStr.append(this.links.get(i).toString());
        }
        return linksStr.append("'").toString();
    }
}
