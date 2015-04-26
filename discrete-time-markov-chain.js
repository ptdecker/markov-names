/**
 * Created by ptdecker on 2015-04-25.
 */

/*
 * Implements a basic trainable discrete time Markov chain.
 *
 * c.f. https://en.wikipedia.org/wiki/Markov_chain
 */

"use strict";

function MarkovChain() {
    this.nodes = {};
}

function addNode(value, next, nodes) {
    if (nodes[value] === undefined) {
        nodes[value] = {};
        nodes[value][next] = 1;
    } else if (nodes[value][next] === undefined) {
        nodes[value][next] = 1;
    } else {
        nodes[value][next] += 1;
    }
}

MarkovChain.prototype.listNodes = function listNodes() {
    var accumulatedKeys,
        nodeKey = "initial",
        linkKey,
        selectedKeyIndex,
        totalCount,
        newWord = "";

    do {
        if(this.nodes.hasOwnProperty(nodeKey)) {

            totalCount = 0;
            accumulatedKeys = [];
            for (linkKey in this.nodes[nodeKey]) {
                if (this.nodes[nodeKey].hasOwnProperty(linkKey)) {
                    accumulatedKeys[totalCount] = linkKey;
                    totalCount += this.nodes[nodeKey][linkKey];
                }
            }

            selectedKeyIndex = Math.floor((Math.random() * totalCount));

            while(!accumulatedKeys[selectedKeyIndex]) {
                selectedKeyIndex -= 1;
            }

            nodeKey = accumulatedKeys[selectedKeyIndex];
            if (nodeKey !== 'final') {
                newWord += nodeKey;
            }

        } else {
            throw new Error("Error in Markov chain graph--key '" + nodeKey + "' was expected but not found");
        }

    } while (nodeKey !== "final");

    console.log(newWord);
};

MarkovChain.prototype.teachWords = function teachWords(word) {
    var wordArray = word.toLowerCase().split(' '),
        charArray,
        prior,
        wordIndex,
        charIndex;
    for (wordIndex = 0; wordIndex < wordArray.length; wordIndex += 1) {
        charArray = wordArray[wordIndex].split('');
        prior = undefined;
        for (charIndex = 0; charIndex < charArray.length; charIndex += 1) {
            addNode(prior || "initial", charArray[charIndex], this.nodes);
            prior = charArray[charIndex];
        }
        addNode(prior, "final", this.nodes);
    }
};

module.exports = MarkovChain;