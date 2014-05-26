package org.ptodd;

import java.util.ArrayList;

/**
 * Markov state machine
 *
 * Defines a Markov state machine which stores a set of node states, the links between them, and the weight of
 * each link.
 *
 * An initially created Markov state machine will have two states named ("[Initial]" and "[Final]") with one
 * connection (a weight of one) between the two.
 *
 * Created by ptdecker on 5/25/14.
 */

class States {

    ArrayList<State> states = new ArrayList<State>();

    States() {
        recordLink(Constants.INITIAL_STATE, Constants.FINAL_STATE);
    }

    private State findStateByName(char token) {
        for (State oneState : states) {
            if (oneState.getName() == token) {
                return oneState;
            }
        }
        return null;
    }

    public void recordLink(char fromState, char toState) {
        if (findStateByName(toState) == null) {
            states.add(new State(toState));
        }
        if (findStateByName(fromState) == null) {
            State S = new State(fromState);
            states.add(S);
            S.recordLink(toState);
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
