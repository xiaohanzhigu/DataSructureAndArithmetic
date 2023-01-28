package com.ch.sparsearray;

/**
 * 当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 * <p>
 * 稀疏数组的处理方法是:
 * 记录数组一共有几行几列，有多少个不同的值
 * 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 */
public class SparseArray {
    public static void main(String[] args) {
        //原始数组
        //0:没有棋子,1:白子,2:黑子
        int[][] chessArr = new int[11][11];
        //给原始数组赋值
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[3][4] = 1;

        //数组原始数组
        for (int[] row : chessArr) {
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%d\t", row[i]);
            }
            System.out.println();
        }

        //遍历二维数字，得到非 0 值的个数
        int sum = 0;//记录非零值的个数
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if(chessArr[i][j] != 0){
                    sum++;
                }
            }
        }

        //构建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        //遍历原始数组，将非零值记录到稀疏数组
        int count = 0;//记录当前是第几个非零值
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if(chessArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println("稀疏数组");
        for (int[] row : sparseArr) {
                System.out.printf("%d\t%d\t%d\n",row[0], row[1], row[2]);
        }

        //将稀疏数组还原为原始数组
        //遍历稀疏数组后几行的数据并赋值给原始数组
        int[][] newChessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            newChessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("还原后的原始数组");
        for(int[] row : newChessArr) {
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%d\t", row[i]);
            }
            System.out.println();
        }
    }
}
