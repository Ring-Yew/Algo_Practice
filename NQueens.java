import java.util.*;
/*According to the rules of chess, a queen can attack pieces in the same row, 
column, or diagonal line. Given n queens and an n*n chessboard, find 
arrangements where no two queens can attack each other. */
public class NQueens {
    public static void main(String[] args) {
        List<List<List<Boolean>>> a = nQueens(4);
        for(int i=0;i<a.size();i++)
        {
            for(int j = 0; j<a.get(i).size(); j++)
            {
                for(int k = 0; k<a.get(i).get(j).size(); k++)
                {
                    System.out.print(a.get(i).get(j).get(k)+" ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }
    public static List<List<List<Boolean>>> nQueens(int n)
    {
        /*In the main diagonal, row - col is constant
        * In the secondary diagonal, row+col is constant
        */
        boolean[] mDia = new boolean[2*n-1];
        boolean[] sDia = new boolean[2*n-1];
        boolean[] col = new boolean[n];
        boolean[][] state = new boolean[n][n];
        List<List<List<Boolean>>> res = new ArrayList<>();
        backtrack(n, 0, state, col, mDia, sDia, res);
        return res;
    }
    public static void backtrack(int n, int row, 
            boolean[][] state, boolean[] col, 
            boolean[] mDia, boolean[] sDia, List<List<List<Boolean>>> res)
    {
        if(row >= n)
        {
            List<List<Boolean>> copyState = new ArrayList<>();
            for(int i = 0; i < n; i++)
            {
                copyState.add(new ArrayList<>());
                for(int j = 0; j < n; j++)
                {
                    copyState.get(i).add(state[i][j]);
                }
            }
            res.add(copyState);
            return;
        }
        for(int i = 0; i < n; i++)
        {
            if(!mDia[row-i+n-1]&&!sDia[row+i]&&!col[i])
            {
                state[row][i] = mDia[row-i+n-1] = sDia[row+i] = 
                        col[i] = true;
                backtrack(n, row+1, state, col, mDia, sDia, res);
                state[row][i] = mDia[row-i+n-1] = sDia[row+i] = 
                        col[i] = false;
            }
        }
    }
}
