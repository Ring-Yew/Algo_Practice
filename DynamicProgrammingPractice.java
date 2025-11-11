import java.util.*;

public class DynamicProgrammingPractice {
    public static void main(String[] args) {
        // int[][] a = new int[][]{{1,3,1,5},{2,2,4,2},{5,3,2,1},{4,3,5,2}};
        // System.out.println(MinimumPathSum.findMinumumPath(a));
        // int[] wgt = new int[] {2,3,4};
        // int[] val = new int[] {4,5,6};
        // int[] coins = new int[]{1,2,5};
        // String s = "bag", t = "pack";
        // System.out.println(EditDistance.minEditDP(s, t));
        // System.out.println(KnapsackProblem.coinChangeProblemDP(11, coins));
        // System.out.println(KnapsackProblem.coinChangeProblemIIDP(5, coins));
        // System.out.println(KnapsackProblem.findMaxValUnbounded(3, 8, wgt, val));
    }
}
class EditDistance{
    /* Given two strings s and t, return the minimum number of edits required to 
    transform s into t.
    You can perform three types of edits on a string: insert a character, delete 
    a character, or replace a character with any other character.*/
    public static int minEditDP(String s, String t)
    {
        /*Let i be the index of s, j be the index of t
        Let dp[i][j] be the minimum edit to make first i characters of s and j 
        characters of t the same.
        If choose to insert dp[i][j] = dp[i][j-1]+1
        If choose to delete dp[i][j] = dp[i-1][j]+1
        If choose to replace dp[i][j] = dp[i-1][j-1]*/
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i = 0; i < dp.length; i++)
        {
            dp[i][0] = i;
        }
        for(int j = 1; j < dp[0].length; j++)
        {
            dp[0][j] = j;
        }
        for(int i = 1; i < dp.length; i++)
        {
            for(int j = 1; j < dp[i].length; j++)
            {
                if(s.charAt(i-1)==t.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), 
                            dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
class KnapsackProblem
{
    /*Given n types of coins, where the denomination of the i-th type of coin is 
    coins[i-1], and the target amount is amt. Each type of coin can be selected 
    multiple times, ask how many combinations of coins can make up the target 
    amount.*/
    public static int coinChangeProblemIIDP(int amt, int[] coins)
    {   /*Let dp[i][a] be the number of combinations make up a with the first i 
        coins.
        If do not choose i-th coin, dp[i][a] = dp[i-1][a].
        If choose i-th coin, dp[i][a] = dp [i-1][a-coins[i-1]]+1*/
        int n = coins.length;
        int[][] dp = new int[n+1][amt+1];
        for (int i = 0; i <= n; i++) 
        {
            dp[i][0] = 1;
        }
        for(int i = 1; i < n+1; i++)
        {
            for(int a = 1; a < amt+1; a++)
            {
                if(coins[i-1]>a)
                    dp[i][a] = dp[i-1][a];
                else
                    dp[i][a] = dp[i-1][a] + dp[i][a-coins[i-1]];
            }
        }
        return dp[n][amt];
    }
    /*Given n types of coins, the denomination of the i-th type of coin is 
    coins[i-1], and the target amount is amt. Each type of coin can be selected 
    multiple times. What is the minimum number of coins needed to make up the 
    target amount? If it is impossible to make up the target amount, return -1.*/
    public static int coinChangeProblemDP(int amt, int[] coins)
    {
        /*
        Let dp[i][a] be the minimum number of coins to sum up a using first i 
        coins.
        If do not choose i-th coin, dp[i][a] = dp[i-1][a];
        If choose i-th coin, dp[i][a] = dp[i][a-coins[i-1]]+1; 
         */
        int n = coins.length;
        int[][] dp = new int[n+1][amt+1];
        for(int a = 1; a < amt+1; a++)
        {
            dp[0][a] = amt+1;
        }
        for(int i = 1; i < n+1; i++)
        {
            for(int a = 1; a < amt+1; a++)
            {
                if(coins[i-1]>a)
                    dp[i][a] = dp[i-1][a];
                else
                    dp[i][a] = Math.min(dp[i-1][a],dp[i][a-coins[i-1]]+1);
            }
        }
        return dp[n][amt]==amt+1?-1:dp[n][amt];
    }
    /*Given n items, the weight of the i-th item is wgt[i-1] and its value is 
    val[i-1], and a knapsack with a capacity of cap. Each item can be chosen 
    only once. What is the maximum value of items that can be placed in the 
    knapsack under the capacity limit? */
    public static int findMaxVal01DP(int n, int cap, int[] wgt, int[] val)
    {
        /*Let j be current capacity of bag
        If do notchoose the item, dp[i][j] = dp[i-1][j]
        If choose the item, dp[i][j] = dp[i-1][j-wgt[i-1]]+val[i-1] */
        int[][] dp = new int[n+1][cap+1];
        for(int i = 1; i < n+1; i++)
        {
            for(int j = 1; j < cap+1; j++)
            {
                if(wgt[i-1]>j)
                    dp[i][j] = dp[i-1][j];
                else
                {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wgt[i-1]]+val[i-1]);
                }
            }
        }
        return dp[n][cap];
    }
    /*Given n items, where the weight of the i-th item is wgt[i-1]and its value 
    is val[i-1], and a backpack with a capacity of cap. Each item can be 
    selected multiple times. What is the maximum value of the n items that can 
    be put into the backpack without exceeding its capacity? See the example 
    below. */
    public static int findMaxValUnbounded(int i, int cap, int[] wgt, int[] val)
    {
        if(i == 0 || cap <= 0)
            return 0;
        if(wgt[i-1]>cap)
            return findMaxValUnbounded(i-1, cap, wgt, val);
        int choose = Math.max(findMaxValUnbounded(i, cap-wgt[i-1], wgt, val)+val[i-1],
                i>0?(findMaxValUnbounded(i-1, cap-wgt[i-1], wgt, val))+val[i-1]:0);
        int notChoose = findMaxValUnbounded(i-1, cap, wgt, val);
        return Math.max(choose, notChoose);
    }
}
class MinimumPathSum
{
/*Given an n*m two-dimensional grid, each cell in the grid contains a 
 non-negative integer representing the cost of that cell. The robot starts 
 from the top-left cell and can only move down or right at each step until it 
 reaches the bottom-right cell. Return the minimum path sum from the top-left to
 the bottom-right. */
    public static int findMinumumPath(int[][] grid)
    {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                if(j+1 < grid[i].length && (dp[i][j+1]==0 || 
                        (dp[i][j]+grid[i][j+1])<dp[i][j+1]))
                {
                    dp[i][j+1] = dp[i][j]+grid[i][j+1];
                }
                if(i+1 < grid.length && (dp[i+1][j]==0 || 
                        (dp[i][j]+grid[i+1][j]) < dp[i+1][j]))
                {
                    dp[i+1][j] = dp[i][j] + grid[i+1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}