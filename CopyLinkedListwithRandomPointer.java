/*

Appoach - 
create duplicate nodes.

for ex - give linked list is - 1->2->3->4->5->null
Approach  is to first convert above LL as - 1->1->2->2->3->3->4->5->5->null
and then cut out the duplicate nodes from original list

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) {
            return head;
        }
        Node temp = head;  
        while(temp!=null){
            Node node = new Node(temp.val);
            node.next = temp.next;
            temp.next=node;
            temp = temp.next.next;
        }
        
        temp = head;
        while(temp!=null && temp.next!=null) {
            if(temp.random==null) {
                temp.next.random = null;
            } else {
                temp.next.random = temp.random.next;
            }
            temp=temp.next.next;
        }

        Node copyHead = head.next;
        temp = head;
        while(temp!=null) {
            Node copy = temp.next;
            if(copy.next == null) {
                copy.next = null;
                temp.next = temp.next.next;
                temp = temp.next;
            } else {
                temp.next = temp.next.next;
                temp = temp.next;
                copy.next = copy.next.next;
            }
        }

        return copyHead;
        
    }
}