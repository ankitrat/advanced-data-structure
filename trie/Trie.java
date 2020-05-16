/*
Leetcode Problem Url - https://leetcode.com/problems/implement-trie-prefix-tree/ 
Stats from Leetcode (trying to optimize it further.)- 
	Runtime: 36 ms, faster than 45.42% of Java online submissions for Implement Trie (Prefix Tree).
	Memory Usage: 51.4 MB, less than 98.08% of Java online submissions for Implement Trie (Prefix Tree).
*/

class Node {
    HashMap<Character,Node> child;
    boolean isEow;
    
    Node() {
        this.child = new HashMap<>();
        this.isEow = false;
    }
}
class Trie {
    Node root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node temp = root;
        char[] chArr = word.toCharArray();
        for(char ch: chArr) {
            Node t = temp.child.get(ch);
            if(t == null) {
                t = new Node();
                temp.child.put(ch,t);
            }
            temp = t;
        }
        temp.isEow=true;
        
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node temp = root;
        char[] chArr = word.toCharArray();
        for(char ch: chArr) {
            Node t = temp.child.get(ch);
            if(t == null) {
                return false;
            }
            temp = t;
        }
        if(temp.isEow) {
            return true;
        } 
        return false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node temp = root;
        char[] chArr = prefix.toCharArray();
        for(char ch: chArr) {
            Node t = temp.child.get(ch);
            if(t == null) {
                return false;
            }
            temp = t;
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
