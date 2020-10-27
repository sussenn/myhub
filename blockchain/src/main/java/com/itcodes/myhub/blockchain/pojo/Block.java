package com.itcodes.myhub.blockchain.pojo;

import com.itcodes.myhub.util.StringUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Block
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/16
 */
public class Block implements Serializable {

    private static final long serialVersionUID = 1376906582885002760L;
    public String hash;          //当前哈希
    public String previousHash; //上一个节点哈希
    private String data;        //数据文本
    private long timeStamp;     //时间戳
    private int nonce;          //随机数

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash(); //设置其他值后 请确保执行此操作
    }

    //根据区块内容计算新哈希
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    //增加随机数值，直到达到哈希目标为止
    public void mineBlock(int difficulty) {
        String target = StringUtil.getDificultyString(difficulty); //创建一个难度为*“0”的字符串
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.err.println("区块开采: " + hash);
    }

}
