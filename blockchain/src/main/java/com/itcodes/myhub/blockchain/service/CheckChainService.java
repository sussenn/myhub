package com.itcodes.myhub.blockchain.service;

import com.itcodes.myhub.blockchain.pojo.Block;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @ClassName CheckChainService
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/16
 */
//@Service
public class CheckChainService {

    //private static ArrayList<Block> blockchain = new ArrayList<>();
    private static int difficulty = 5;

    public static Boolean isChainValid(ArrayList<Block> blockchain) {

        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //遍历区块链以检查哈希
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //比较注册的哈希和计算的哈希
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.err.println("当前哈希不相等");
                return false;
            }
            //比较先前的哈希和注册的先前的哈希
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.err.println("先前的哈希不相等");
                return false;
            }
            //检查哈希是否 解开
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.err.println("该区块尚未被开采");
                return false;
            }

        }
        return true;
    }

    public static void addBlock(Block newBlock,ArrayList<Block> blockchain) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}
