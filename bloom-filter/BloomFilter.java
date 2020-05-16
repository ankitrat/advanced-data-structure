package com.emc.nw;

import java.util.BitSet;

public class BloomFilter {

    private static final Integer MAX_HASH_FUNCTIONS = 15;
    private static final Integer MAX_BUFFER_SIZE = 14363613;    // ~1795451 bytes , ~1.79 MB
    private static final int[] multiplyFactors = {17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73}; //used prime numbers


    private int bufferSize;
    private BitSet bitSet;
    private int hashFunctions;

    /**
     * This constructor uses default values of bufferSize and number of hashFunctions.
     * Recommended to use this defined values if your targeted input count is ~ 4,00,000 string.
     * If you use this defined values for targeted string count ~ 4,00,000, Probability of false positives is calculated as 0.0000001 (1 in 10,00,0000).
     * Above stats are calculated using https://hur.st/bloomfilter/ .
     * You can calculate your own stats based on your input and use custom values as well.
     */
    public BloomFilter() {
        this(MAX_BUFFER_SIZE);
    }


    public BloomFilter(int bufferSize) {
        this(bufferSize, MAX_HASH_FUNCTIONS);
    }

    /**
     * This constructor you can use with you custom calculated values.
     * Highly recommended to use correct values to decrease the false positive count.
     * You can Calculate values/size from  https://hur.st/bloomfilter/  .
     */
    public BloomFilter(int bufferSize, Integer hashFunctions) {
        if (hashFunctions < 1 || hashFunctions > MAX_HASH_FUNCTIONS) {
            throw new IllegalArgumentException("Supported number of hash functions is in range of " + 1 + "to" + MAX_HASH_FUNCTIONS);
        }
        this.bufferSize = bufferSize;
        this.hashFunctions = hashFunctions;
        this.bitSet = new BitSet(bufferSize);
    }

    public void add(String str) {
        for (int i = 0; i < hashFunctions; i++) {
            long hashValue = hashCode(str, multiplyFactors[i]);
            hashValue = hashValue & 0X7FFFFFF;      //to avoid negative hashValue
            bitSet.set(getIndex(hashValue));
        }
    }

    public int getIndex(long hashValue) {
        return (int) (hashValue % this.bufferSize);
    }

    public boolean contains(String str) {
        for (int i = 0; i < hashFunctions; i++) {
            long hashValue = hashCode(str, multiplyFactors[i]);
            hashValue = hashValue & 0X7FFFFFF;          //to avoid negative hashValue
            if (!bitSet.get(getIndex(hashValue))) {
                return false;
            }
        }
        return true;
    }

    public long hashCode(String str, int multiplyFactor) {
        long hashVal = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            hashVal = multiplyFactor * hashVal + str.charAt(i);
        }
        return hashVal;
    }
}
