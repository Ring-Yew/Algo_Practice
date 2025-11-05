import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
public class BinaryTreePractice {
    public static void main(String[] args) {
    }
}
class BinarySearchTree
{
    TreeNode root;
    public BinarySearchTree(int x)
    {
        root = new TreeNode(x);
    }
    public TreeNode searchPre(int val)
    {
        TreeNode cur = root, pre = null;
        while(cur!=null)
        {
            if(cur.val<val) {pre = cur; cur = cur.right;}
            else if(cur.val>val) {pre = cur; cur = cur.left;}
            else if(cur.val == val) return pre;
        }
        return null;
    }
    public TreeNode search(int val)
    {
        TreeNode cur = root;
        while(cur!=null)
        {
            if(cur.val<val) cur = cur.right;
            else if(cur.val>val) cur = cur.left;
            else if(cur.val == val) return cur;
        }
        return null;
    }
    public void insert(int x)
    {
        if(root == null)
        { root = new TreeNode(x); return; }
        TreeNode pre = null;
        TreeNode cur = root;
        while(cur!=null)
        {
            if(cur.val<x)
            { pre = cur; cur = cur.right;}
            else if(cur.val>x)
            { pre = cur; cur = cur.left;}
            else if(cur.val == x)
                return;
        }
        if (pre.val < x) {
            pre.right = new TreeNode(x);
        } else {
            pre.left = new TreeNode(x);
        }
        
    }
    public void delete(int x)
    {
        if(root == null) return;
        TreeNode pre = searchPre(x), target = search(x);
        if(target == null)
            return;

        if(target.left==null||target.right==null)
        {
            TreeNode child = target.left != null ? target.left : target.right;
            if(pre == null)
            { root = child; }
            else
            {
                if(target == pre.left)
                    pre.left = child;
                else
                    pre.right = child;
            }
        }
        else
        {
            TreeNode temp = target.right;
            while(temp.right!=null)
            {
                temp = temp.right;
            }
            delete(temp.val);
            target.val = temp.val;
        }
    }
}
class TreeNode
{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x)
    {
        val = x;
    }
}
class BinaryTree{
    TreeNode root;
    public BinaryTree(TreeNode x){
        root = x;
    }
    public List<Integer> levelOrder(TreeNode root)
    {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(root);
        while(!queue.isEmpty())
        {
            TreeNode temp = queue.poll();
            if(temp!=null)
            {
                list.add(temp.val);
                queue.offer(temp.left);
                queue.offer(temp.right);
            }
        }
        return list;
    }
    public List<Integer> traversal(TreeNode root, int order)
    {
        List<Integer> list = new ArrayList<>();
        /*preorder */
        if(order == 0)
        {
            preOrder(root, list);
        }
        /*inorder */
        if(order == 1)
        {
            inOrder(root, list);
        }
        /*postorder */
        if(order == 2)
        {
            postOrder(root, list);
        }
        return list;
    }
    public void preOrder(TreeNode root, List<Integer> list)
    {
        if(root==null)
            return;
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }
    public void inOrder(TreeNode root, List<Integer> list)
    {
        if(root==null)
            return;
        preOrder(root.left, list);
        list.add(root.val);
        preOrder(root.right, list);
    }
    public void postOrder(TreeNode root, List<Integer> list)
    {
        if(root==null)
            return;
        preOrder(root.left, list);
        preOrder(root.right, list);
        list.add(root.val);
    }
}
