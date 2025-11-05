public class LinkedListPractice {

}
class ListNode
{
    int val;
    ListNode next;
    public ListNode(int num)
    {
        val = num;
    }
    /*Insert node after n0 */
    public void insert(ListNode n0, ListNode node)
    {
        node.next = n0.next;
        n0.next = node;
    }
    /*Delete the next node after n0 */
    public void delete(ListNode n0)
    {
        if(n0.next==null)
            return;
        ListNode temp = n0.next.next;
        n0.next.next = null;
        n0.next = temp;
    }
    public ListNode access(ListNode head, int index)
    {
        for(int i=0;i<index;i++)
        {
            if(head == null)
                return null;
            head = head.next;
        }
        return head;
    }
    public int find(ListNode head, int val)
    {   
        int index = 0;
        while(head!=null)
        {
            if(head.val == val)
                return index;
            index++;
            head = head.next;
        }
        return -1;
    }
}
