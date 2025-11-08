public class SortingPractice {
    public static void main(String[] args) {

    }
    public static void merge(int[] nums, int left, int mid, int right)
    {
        //left interval is [left,mid], and right interval is [mid+1,right]
        int[] temp = new int[right-left+1];
        int i = left, j = mid+1, k = 0;
        while(i <= mid && j <= right)
        {
            if(nums[i]<nums[j])
            {
                temp[k] = nums[i];
                i++; k++;
            }
            else
            {
                temp[k] = nums[j];
                k++; j++;
            }
        }
        for(;j <= right; j++, k++)
            temp[k] = nums[j];
        for(; i <= mid; i++, k++)
            temp[k] = nums[i];
        for(k = 0; k<temp.length; k++)
            nums[left+k] = temp[k];

    }
    public static void mergeSort(int[] nums, int left, int right)
    {
        if(left>=right) return;
        int mid = left + (right-left)/2;
        mergeSort(nums,left,mid);
        mergeSort(nums,mid+1,right);
        merge(nums,left,mid,right);
    }
    public static int partition(int[] nums, int left, int right)
    {
        /*Use nums[left] as pivot */
        int i = left, j = right;
        while(i<j)
        {
            while(i<j && nums[j]>=nums[left])
                j--;
            while(i<j && nums[i]<=nums[left])
                i++;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        int temp = nums[i];
        nums[i] = nums[left];
        nums[left] = temp;
        return i; //return index of pivot
    }
    public static void quickSort(int[] nums, int left, int right)
    {
        if(left >= right)
            return;
        int pivot = partition(nums, left,right);
        quickSort(nums, left, pivot-1);
        quickSort(nums, pivot+1, right);

    }
    public static void insertionSort(int[] nums)
    {
        int n = nums.length;
        for(int i = 1; i < n; i++)
        {
            int temp = nums[i];
            int j = i-1;
            for(; j >= 0; j--)
            {
                if(nums[j]>temp)
                    nums[j+1] = nums[j];
                else break;
            }
            nums[j+1] = temp;
        }
    }
    public static void bubbleSort(int[] nums)
    {
        int n = nums.length;
        for(int j = n-1; j>=0; j--)
        {
            for(int i=0; i<j; i++)
            {
                if(nums[i]>nums[i+1])
                {
                    int temp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = temp;
                }
            }
        }
    }
    public static void selectionSort(int[] nums)
    {
        int n = nums.length;
        for(int i = 0; i < n; i++)
        {
            int minIndex = -1;
            int min = Integer.MAX_VALUE;
            for(int j = i; j < n; j++)
            {
                if(nums[j]<=min){
                    minIndex = j;
                    min = nums[j];
                }
            }
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }
    public static void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
