/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stratkon.myblockchain;

import java.util.ArrayList;

/**
 *
 * @author Swami Ranganathan
 */
public class MyBlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 0;

    public static void main(String[] args) {

        //add our blocks to the blockchain ArrayList:
        blockchain.add(new Block("First blockchain transaction", "0"));
        System.out.println("Mining block 1... ");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second blockchain transaction", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Mining block 2... ");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third blockchain transaction", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Mining block 3... ");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());
        //add our blocks to the blockchain ArrayList:
        blockchain.add(new Block("Adding first block", "0"));
        blockchain.add(new Block("Adding second block", blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block("Adding third block", blockchain.get(blockchain.size() - 1).hash));

        //   String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        //   System.out.println(blockchain);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }

}
