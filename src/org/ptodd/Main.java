package org.ptodd;

import java.util.ArrayList;

class Link {

    State toState = null;
    long count = 0;

}

class State {

    String name;
    ArrayList<Link> links;

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

    public String getLinksAsString () {
        StringBuilder linksAsString = new StringBuilder("No Links");
        if (links.size() > 0) {
            linksAsString.delete(0,linksAsString.length());
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

class States {

    ArrayList<State> states = new ArrayList<State>();

    States() {
        addState(new State("[initial]"));
        addState(new State("[final]"));
    }

    private void addState(State S) {
        states.add(S);
    }

    private State findStateByName(String S) {
        for (State oneState : states) {
            if (oneState.getName() = S) {
                return oneState;
            }
        }
        return null;
    }

    private void addLinkByName(String L1, String L2) {
        
    }

    @Override
    public String toString() {
        StringBuilder stateList = new StringBuilder();
        for (State oneState : states) {
            stateList.append(oneState.toString() + "\n");
        }
        return stateList.toString();
    }
}

public class Main {

    public static void main(String[] args) {

        States mtm = new States();
        System.out.println(mtm.toString());

   }
}
