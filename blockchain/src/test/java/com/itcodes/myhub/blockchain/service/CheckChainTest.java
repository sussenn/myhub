package com.itcodes.myhub.blockchain.service;

import com.itcodes.myhub.blockchain.BlockchainApplication;
import com.itcodes.myhub.blockchain.pojo.Block;
import com.itcodes.myhub.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@SpringBootTest(classes = BlockchainApplication.class)
@RunWith(value = SpringRunner.class)
@Slf4j
public class CheckChainTest {

    private static ArrayList<Block> blockchain = new ArrayList<>();
    private static int difficulty = 5;

    @Test
    public void test00(){

        //将我们的块添加到区块链ArrayList中
        System.err.println("Trying to Mine block 1... ");
        addBlock(new Block("第一个区块:", "0"));

        System.err.println("Trying to Mine block 2... ");
        addBlock(new Block("第二个区块:",blockchain.get(blockchain.size()-1).hash));

        System.err.println("Trying to Mine block 3... ");
        addBlock(new Block("第三个区块:",blockchain.get(blockchain.size()-1).hash));

        System.err.println("\n有效区块链: " + isChainValid());

        String blockchainJson = StringUtil.getJson(blockchain);
        System.err.println("\n区块链: ");
        System.err.println(blockchainJson);

    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //遍历区块链以检查哈希
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //比较注册的哈希和计算的哈希
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.err.println("当前哈希不相等");
                return false;
            }
            //比较先前的哈希和注册的先前的哈希
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.err.println("先前的哈希不相等");
                return false;
            }
            //检查哈希是否 解开
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.err.println("该区块尚未被开采");
                return false;
            }
        }
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }

}