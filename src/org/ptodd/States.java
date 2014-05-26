package org.ptodd;

import java.util.ArrayList;

/**
 * Created by ptdecker on 5/25/14.
 */
class States {

    ArrayList<State> states = new ArrayList<State>();

    States() {
        addState(new State("[initial]"));
        addState(new State("[final]"));
        addLinkByName("[initial]", "[final]");
    }

    private void addState(State S) {
        states.add(S);
    }

    private State findStateByName(String S) {
        for (State oneState : states) {
            if (oneState.getName() == S) {
                return oneState;
            }
        }
        return null;
    }

    private void addLinkByName(String L1, String L2) {
        State S = new State();
        if (S == null) {
            System.out.println("State not found\n");
        } else {
            System.out.println("State found\n");
        }

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
