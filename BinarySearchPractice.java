public class BinarySearchPractice {

    public static void main(String[] args) {
        int[] a = {1,2,7,8,11,71,88};
        System.out.println(binarySearchInsertionSimple(a, 5));
    }
    public static int binarySearchLeftEdge(int[] nums, int target)
    {
        int i = binarySearchInsertion(nums, target);
        if(i == nums.length || nums[i]!=target)
            return -1;
        return i;
    }
    public static int binarySearchRightEdge(int[] nums, int target)
    {
        /*Search the left edge of target+1, and then the index minus one is the 
        index of right edge of target*/
        int j = binarySearchInsertion(nums, target+1)-1;
        if(j == -1 || nums[j]!=target)
            return -1;
        return j;
    }
    public static int binarySearchInsertion(int[] nums, int target)
    {
        int i = 0, j = nums.length;
        while(!(i>j))
        {
            int m = i + (j-i)/2;
            if(nums[m]>target)
                j = m-1;
            if(nums[m]<target)
                i = m+1;
            else{
                j = m-1;
            }
        }
        return i;
    }
    public static int binarySearchInsertionSimple(int[] nums, int target)
    {
        int i = 0, j = nums.length-1;
        while(!(i>j))
        {
            int m = j+(i-j)/2;
            if(nums[m]==target)
                return m;
            else if(nums[m]>target)
                j = m-1;
            else
                i = m+1;
        }
        return i;
    }
    public static int binarySearch(int[] array, int target)
    {
        int i = 0, j = array.length-1;
        while(!(i>j))
        {
            int m = j+(i-j)/2;
            if(array[m]==target)
                return m;
            else if(array[m]>target)
                j = m-1;
            else
                i = m+1;
        }
        return -1;
    }
}

