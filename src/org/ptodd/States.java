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

    private State findStateByToken(char token) {
        for (State oneState : states) {
            if (oneState.getToken() == token) {
                return oneState;
            }
        }
        return null;
    }

    private State getInitialState() {
        return findStateByToken(Constants.INITIAL_STATE);
    }

    private State getFinalState() {
        return findStateByToken(Constants.FINAL_STATE);
    }

    private State getNextRandomState(State state) {
        return findStateByToken(state.followRandomLinkToken());
    }

    public boolean isTrained() {
        return (getInitialState().getTotalWeight() > 0);
    }

    private void recordLink(char fromState, char toState) {
        if (findStateByToken(fromState) == null) {
            addState(fromState);
        }
        if (findStateByToken(toState) == null) {
            addState(toState);
        }
        findStateByToken(fromState).recordLink(toState);
    }

    public void trainFrom(String text) {
        if (text != null && !text.isEmpty()) {
            String cleanedText = text
                    .toUpperCase(Locale.getDefault())   // convert to upper case
                    .replaceAll("\\W", "");             // and strip non-word characters (including spaces)
            recordLink(Constants.INITIAL_STATE, cleanedText.charAt(0));
            for (int i = 1; i < cleanedText.length(); i++) {
                recordLink(cleanedText.charAt(i - 1), cleanedText.charAt(i));
            }
            recordLink(cleanedText.charAt(cleanedText.length() - 1), Constants.FINAL_STATE);
        }
    }

    public void trainFrom(File fin) throws IOException {
        FileInputStream fis = new FileInputStream(fin);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine()) != null) {
            trainFrom(line);
        }
        br.close();
        fis.close();
    }

    private String getRawName() {
        StringBuilder name = new StringBuilder();
        State state = getInitialState();
        while (state != getFinalState()) {
            state = getNextRandomState(state);
            if (state != getFinalState()) {
                name.append(state.getName());
            }
        }
        return name.toString();
    }

    public String getMarkovName() {
        if (isTrained()) {
            do {
                String rawName = getRawName();
                if (rawName.length() < 3) {
                    continue;
                }
                if (rawName.length() > 8) {
                    continue;
                }
                return rawName;
            } while (true);
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
