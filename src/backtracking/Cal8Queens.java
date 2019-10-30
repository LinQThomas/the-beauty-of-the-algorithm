package backtracking;

/**
 * 回溯算法——8皇后问题
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class Cal8Queens {

    /**
     * 全局变量或成员变量，下标表示行，值表示queen存储在哪一列
     */
    int[] result = new int[8];

    public void cal8queens(int row) {
        //8个棋子都放好了，打印结果
        if (row == 8) {
            printQueess(result);
            //8个棋子放好了，无法再递归，直接return
            return;
        }

        //每一行有8种放法
        for (int cloumn = 0; cloumn < 8; ++cloumn) {
            //有些放法不满足要求
            if (isOK(row, cloumn)) {
                //第row行的棋子放到了column列
                result[row] = cloumn;
                //考察下一行
                cal8queens(row + 1);
            }
        }

    }

    /**
     * 判断row行column列放置是否合适
     *
     * @param row
     * @param cloumn
     * @return
     */
    private boolean isOK(int row, int cloumn) {
        int leftup = cloumn - 1, rightup = cloumn + 1;
        //主行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            //第i行的column列有棋子
            if (result[i] == cloumn) {
                return false;
            }

            //考察左上对角线：第i行的leftup列有棋子吗
            if (leftup >= 0) {
                if (result[i] == leftup) {
                    return false;
                }
            }

            //考察右上对角线：第i行的rightup列有棋子吗
            if (rightup < 8) {
                if (result[i] == rightup) {
                    return false;
                }
            }

            --leftup;
            ++rightup;
        }
        return true;
    }

    /**
     * 打印一个二维矩阵
     *
     * @param result
     */
    private void printQueess(int[] result) {
        for (int row = 0; row < 8; ++row) {
            for (int cloumn = 0; cloumn < 8; ++cloumn) {
                if (result[row] == cloumn) {
                    System.out.println("Q ");
                } else {
                    System.out.println("* ");
                }
            }
        }
    }
}
