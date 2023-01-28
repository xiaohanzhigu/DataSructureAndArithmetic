package com.ch.arithmetic.recursion;

/**
 * 八皇后问题
 * 用一维数组解决八皇后问题
 * 1) 第一个皇后先放第一行第一列
 * 2) 第二个皇后放在第二行第一列、然后判断是否OK[即判断是冲突]， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 3) 继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4) 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 5) 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
 */
public class EightQueen {
    private static int max = 32;
    private static int[] arr = new int[max];
    private static int count = 0;

    public static void main(String[] args) {
        check(0);
        System.out.println(count);
    }

    /**
     * 放置皇后
     * @param n 放置第n + 1 行的皇后
     */
    public static void check(int n) {
        //如果条件成立表示已经放置完了第八行
        if (n == max) {
            count++;
            showQueen();
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    public static boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //arr[n] == arr[i]检查列有没有冲突
            //arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])//检查斜线上有没有冲突的
            //原理: 将两个皇后的位置想象成一个矩形，Math.abs(n - i)求出列宽, Math.abs(arr[n] == arr[i])求出行宽, 若是相等表示有冲突
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    public static void showQueen() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println(":" + count);
    }
}
