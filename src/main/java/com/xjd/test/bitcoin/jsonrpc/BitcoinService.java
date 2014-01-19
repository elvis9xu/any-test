package com.xjd.test.bitcoin.jsonrpc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public interface BitcoinService {

    BigDecimal getbalance();

    Map<String, BigDecimal> listaccounts();

    String getnewaddress(String label);

    Block getblock(String hash);

    public static class Block {
	private String hash;
	private int confirmations;
	private int size;
	private int height;
	private int version;
	private String merkleroot;
	private String[] tx;
	private Date time;
	private long nonce;
	private String bits;
	private double difficulty;
	private String previousblockhash;
	private String nextblockhash;

	public String getHash() {
	    return hash;
	}

	public void setHash(String hash) {
	    this.hash = hash;
	}

	public int getSize() {
	    return size;
	}

	public void setSize(int size) {
	    this.size = size;
	}

	public int getHeight() {
	    return height;
	}

	public void setHeight(int height) {
	    this.height = height;
	}

	public int getConfirmations() {
	    return confirmations;
	}

	public void setConfirmations(int confirmations) {
	    this.confirmations = confirmations;
	}

	public int getVersion() {
	    return version;
	}

	public void setVersion(int version) {
	    this.version = version;
	}

	public String getMerkleroot() {
	    return merkleroot;
	}

	public void setMerkleroot(String merkleroot) {
	    this.merkleroot = merkleroot;
	}

	public String[] getTx() {
	    return tx;
	}

	public void setTx(String[] tx) {
	    this.tx = tx;
	}

	public Date getTime() {
	    return time;
	}

	public void setTime(Date time) {
	    this.time = time;
	}

	public long getNonce() {
	    return nonce;
	}

	public void setNonce(long nonce) {
	    this.nonce = nonce;
	}

	public double getDifficulty() {
	    return difficulty;
	}

	public void setDifficulty(double difficulty) {
	    this.difficulty = difficulty;
	}

	public String getPreviousblockhash() {
	    return previousblockhash;
	}

	public void setPreviousblockhash(String previousblockhash) {
	    this.previousblockhash = previousblockhash;
	}

	public String getNextblockhash() {
	    return nextblockhash;
	}

	public void setNextblockhash(String nextblockhash) {
	    this.nextblockhash = nextblockhash;
	}

	public String getBits() {
	    return bits;
	}

	public void setBits(String bits) {
	    this.bits = bits;
	}

	@Override
	public String toString() {
	    return "Block [hash=" + hash + ", confirmations=" + confirmations + ", size=" + size + ", height=" + height + ", version=" + version
		    + ", merkleroot=" + merkleroot + ", tx=" + Arrays.toString(tx) + ", time=" + time + ", nonce=" + nonce + ", bits=" + bits
		    + ", difficulty=" + difficulty + ", previousblockhash=" + previousblockhash + ", nextblockhash=" + nextblockhash + "]";
	}

    }
}
