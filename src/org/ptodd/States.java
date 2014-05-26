package org.ptodd;

import java.util.ArrayList;

/**
 * Created by ptdecker on 5/25/14.
 */
class States {

    ArrayList<State> states = new ArrayList<State>();

    States() {
        recordLink("[Initial]", "[Final]");
    }

    private State findStateByName(String S) {
        for (State oneState : states) {
            if (oneState.getName() == S) {
                return oneState;
            }
        }
        return null;
    }

    public void recordLink(String S1, String S2) {
        if (findStateByName(S2) == null) {
            states.add(new State(S2));
        }
        if (findStateByName(S1) == null) {
            State S = new State(S1);
            states.add(S);
            S.recordLink(S2);
        }
    }

    @Override
    public String toString() {
        StringBuilder stateList = new StringBuilder();
        for (State oneState : states) {
            stateList.append(oneState.toString()).append('\n');
        }
        return stateList.toString();
    }
}
