/*
 * Implements a basic trainable discrete time Markov chain.
 *
 * c.f. https://en.wikipedia.org/wiki/Markov_chain
 *
 * Created by ptdecker on 2015-04-25.
 */

"use strict";

/**
 *  MarkovChain() - Constructor for the Discrete Time Markov Chain object
 *
 * @constructor
 */

function MarkovChain() {
    this.nodes = {};    // Start off with an empty state space of nodes since no training has occurred.
}

/**
 * addNode() - Add a new node to the Markov chain state space that is linked to the node prior state.
 *
 * @param from
 * @param to
 * @param nodes
 */

// addNode() builds the Markov chain state space as a set of nodes with weighted link connections between them.

function addNode(from, to, nodes) {

    // Case 1: The originating node doesn't exist at all in the state space. So, we will add the node and add the edge
    // to the destination node. Finally, we will set the strength of the connection to that node to 1.

    if (nodes[from] === undefined) {
        nodes[from] = {};
        nodes[from][to] = 1;

    // Case 2: The originating node exists, but this is the first time we have tried to get to the next node. Since
    // this is the first time, set the strength of the connection to 1.

    } else if (nodes[from][to] === undefined) {
        nodes[from][to] = 1;

    // Case 3: The originating node exists and we've been to the next node from this one before, so just increase
    // the weight of the path between the two.

    } else {
        nodes[from][to] += 1;
    }

}

/**
 * generateRawWord() - Generate a new word based upon a trained Markov chain
 *
 * The generated word will not be filtered for reasonableness.
 *
 * @returns {string}
 */

function generateRawWord(nodes) {

    var accumulatedKeys,        // An array of keys for the node with accumulative weights
        stateKey = "initial",   // Pointer to the state currently being processed--start with initial state
        linkKey,                // Pointer to the link from the current state to the next state
        selectedWeight,         // Randomly determined weight value for randomly selected link
        totalCount,             // Sum of all weights for a given state--determines maximum for random weight
        newWord = "";           // An accumulation of the letters selected for the new work--initially empty string

    // Walk the Markov chain states starting at the 'initial' state and working towards the 'final' state by randomly
    // following links from one state to the next by randomly picking which link to follow based upon the weighted
    // values of the links. The values of the links are determined by the frequency of the number of times the link
    // was taken from one state to the next established when the Markov chain is built through training.

    // Make sure the Markov chain has been trained. If not, return an error to the caller.

    if (nodes[stateKey] === undefined) {
        return new Error("The Markov chain has not yet been trained. Unable to generate words.");
    }

    do {

        // Checking to see if this state is a property of the nodes in the state space is really not needed. However,
        // it is included to keep Douglas Crockford and his linter happy.

        if (!nodes.hasOwnProperty(stateKey)) {
            throw new Error("Error in Markov chain graph--key '" + stateKey + "' was expected but not found");
        }

        // For the current state, walk through all the links to its other states doing two things: accumulate the
        // total weight values of all the states and build a list of keys to the other states that has a
        // cumulative weight value.
        //
        // Note: 'accumulatedKeys' is a sparse array whose index is the accumulated weight. It will have lots of
        // null values for indexes for which there is no accumulated link index. This probably isn't the best way
        // of doing this, but it works for now.
        //
        // TODO: Refactor approach to randomly selecting which link should be followed.

        totalCount = 0;
        accumulatedKeys = [];
        for (linkKey in nodes[stateKey]) {
            if (nodes[stateKey].hasOwnProperty(linkKey)) {
                accumulatedKeys[totalCount] = linkKey;
                totalCount += nodes[stateKey][linkKey];
            }
        }

        // Based upon the maximum total accumulated weight, pick a integer weight between 0 and totalCount
        // exclusive of total count (0 <= selectedWeight < totalCount).

        selectedWeight = Math.floor((Math.random() * totalCount));

        // Until we find an index in the 'accumulatedKeys' sparse array that is not null, walk it backwards until
        // either finding one or trying to walk past the start of the array (which should be impossible).

        while (!accumulatedKeys[selectedWeight]) {
            selectedWeight -= 1;
            if (selectedWeight < 0) {
                throw new Error("Reached beginning of accumulated keys sparse array prior to finding a valid link");
            }
        }

        // Follow the randomly selected link to the next node. If it is not the 'final' node, then append the node
        // value to the new name being created by the walk.

        stateKey = accumulatedKeys[selectedWeight];
        if (stateKey !== 'final') {
            newWord += stateKey;
        }

        // Keep going until we reach the 'final' state.

    } while (stateKey !== "final");

    return newWord;
}

// c.f. http://javascriptexample.net/extobjects34.php

function properCase(word) {
    return word.replace(/\b[a-z]/g, function (f) {
        return f.toUpperCase();
    });
}



MarkovChain.prototype.generateNiceName = function generateNiceName() {

    var rawName,
        maxAttempts = 10000,
        attempts;

    for (attempts = 0; attempts < maxAttempts; attempts += 1) {
        rawName = generateRawWord(this.nodes);
        if (rawName.length > 3 && rawName.length < 10) {
            return properCase(rawName);
        }
        attempts += 1;
    }

    return new Error("After " + maxAttempts + " attempts, was unable to find a suitable nice generated name");

};


/**
 * trainWords() - Takes a string of space separated words and uses them to train the Markov chain.
 *
 * @param wordList
 */

MarkovChain.prototype.trainWords = function trainWords(wordList) {

    var wordArray = wordList.toLowerCase().split(' '),  // An array of words--initialized from the passed word list
        charArray,                                      // An array of character symbols comprising an individual word
        priorChar,                                      // The 'priorChar' character symbol
        wordIndex,                                      // An index pointer into the word array
        charIndex;                                      // An index pointer into the character array

    // Split apart the passed 'wordList' at the spaces. Then walk through each word splitting them in turn into an
    // array of characters. From each character, build the nodes and links between nodes within the state space of
    // the Markov chain. The first time through for a given word, the prior node will be set to the special 'initial'
    // node. Once all the characters in a word are handled, a link between the between the last character and the
    // 'final' node will be created.

    for (wordIndex = 0; wordIndex < wordArray.length; wordIndex += 1) {
        charArray = wordArray[wordIndex].split('');
        priorChar = undefined;
        for (charIndex = 0; charIndex < charArray.length; charIndex += 1) {
            addNode(priorChar || "initial", charArray[charIndex], this.nodes);
            priorChar = charArray[charIndex];
        }
        addNode(priorChar, "final", this.nodes);
    }

};

// Make the Markov Chain object available to the world.

module.exports = MarkovChain;