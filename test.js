/**
 * Created by ptdecker on 2015-04-25.
 */

/*
 * A basic test harness for the JavaScript port of Markov-based Name Generator
 */

"use strict";

var MarkovChain = require("./discrete-time-markov-chain.js");

var testDTMC1 = new MarkovChain(),
    testDTMC2 = new MarkovChain();

testDTMC1.teachWords("Akor Alaun Aly Ang Ardul Aun Bae Bal Belar Briz Bur Chal Char Chess Dhaun Dil Dirz Dris Eclav");
testDTMC1.teachWords("Elvan Elv Erel Ethe Faer Felyn Filf Gauss G'eld Ghuan Gin Grey Hael Hal Houn Iiv Iim Illiam");
testDTMC1.teachWords("Ilph Irae Irr Iym Jan Jhael Jhul Jys Lael Lar Lineer Lird Lua Mal May Micar Min Mol Myr Nath");
testDTMC1.teachWords("Ned Nhil Neer Null Olor Pellan Phaer Phyr Qualn Quar Quav Qil Rauv Ril Sbat Sab Shi'n Shri");
testDTMC1.teachWords("Shur Shynt Sin Ssap Susp Talab Tal Triel T'riss Ulvir Umrae Vas Vic Vier Vlon Waer Wuyon Xull");
testDTMC1.teachWords("Xun Yas Zar Zebey Zes Zilv");
console.log(JSON.stringify(testDTMC1));
testDTMC1.listNodes();


testDTMC2.teachWords("Ashley Todd Ruth Anne Brittany Norma Paul Toni Jerry Billy O'Riley Munro Jeff Austin Jane Ben");
console.log(JSON.stringify(testDTMC2));
testDTMC2.listNodes();

