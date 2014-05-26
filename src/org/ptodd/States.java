package org.ptodd;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Markov state machine
 * <p/>
 * Defines a Markov state machine which stores a set of node states, the links between them, and the weight of
 * each link.
 * <p/>
 * An initially created Markov state machine will have two states named ("[Initial]" and "[Final]") with one
 * connection (a weight of one) between the two.
 * <p/>
 * Created by ptdecker on 5/25/14.
 */

class States {

    ArrayList<State> states = new ArrayList<State>();

    States() {
        addState(Constants.INITIAL_STATE);
        addState(Constants.FINAL_STATE);
    }

    private void addState(State state) {
        states.add(state);
    }

    private void addState(char token) {
        addState(new State(token));
    }

    private State findStateByName(char token) {
        for (State oneState : states) {
            if (oneState.getToken() == token) {
                return oneState;
            }
        }
        return null;
    }

    private State getFirstState() {
        return findStateByName(Constants.INITIAL_STATE);
    }

    public boolean isTrained() {
        return (getFirstState().getTotalWeight() > 0);
    }

    private void recordLink(char fromState, char toState) {
        if (findStateByName(fromState) == null) {
            addState(fromState);
        }
        if (findStateByName(toState) == null) {
            addState(toState);
        }
        findStateByName(fromState).recordLink(toState);
    }

    public void trainFrom(String text) {
        if (text != null && !text.isEmpty()) {
            String upCase = text.toUpperCase(Locale.getDefault());
            recordLink(Constants.INITIAL_STATE, upCase.charAt(0));
            for (int i = 1; i < upCase.length(); i++) {
                recordLink(upCase.charAt(i - 1), upCase.charAt(i));
            }
            recordLink(upCase.charAt(upCase.length() - 1), Constants.FINAL_STATE);
        }
    }

    public void trainFrom(File fin) throws IOException {
        FileInputStream fis = new FileInputStream(fin);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            trainFrom(line);
        }
        br.close();
        fis.close();
    }

    public String getMarkovName() {
        if (isTrained()) {
            return "";
        } else {
            // TODO: Convert to throwing an appropriate custom exception
            return "To render a name, please train me first";
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
