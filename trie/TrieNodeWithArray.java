/*
Leetcode Problem Url - https://leetcode.com/problems/implement-trie-prefix-tree/ 
Stats from Leetcode (trying to optimize it further.)- 
	Runtime: 30 ms, faster than 88.28% of Java online submissions for Implement Trie (Prefix Tree).
Memory Usage: 50.4 MB, less than 98.5% of Java online submissions for Implement Trie (Prefix Tree).
*/

class TrieNode {
    TrieNode[] child;
    Boolean isEow;
    char ch;
    TrieNode() {
        child = new TrieNode[26];
        isEow = false;
    }
}

class Trie {
    TrieNode root = null;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode temp = root;
        char[] ch = word.toCharArray();
        for(char c: ch) {
            if(temp.child[c-'a'] == null) {
                TrieNode newNode = new TrieNode();
                newNode.ch = 'c';
                temp.child[c-'a'] = newNode;
            } 
            temp = temp.child[c-'a'];
        }
        temp.isEow = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode temp = root;
        char[] ch = word.toCharArray();
        for(char c: ch) {
            TrieNode curr = temp.child[c-'a'];
            if(curr == null) {
                return false;
            } 
            temp = curr;
        }
        if(temp.isEow) {
            return true;
        }
        return false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        char[] ch = prefix.toCharArray();
        for(char c: ch) {
            TrieNode curr = temp.child[c-'a'];
            if(curr == null) {
                return false;
            }
            temp = curr;
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
