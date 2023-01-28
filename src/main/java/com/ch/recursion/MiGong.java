package com.ch.recursion;

public class MiGong {
    private static int[][] map = new int[8][7];

    public static void main(String[] args) {
        //左右列设置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //上下设置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        showMap();
        setWay(map, 1, 1);
        showMap();
    }

    public static boolean setWay(int[][] map, int x, int y) {
        //出口位置
        if (map[6][1] == 2) {
            return true;
        } else if (map[x][y] == 0) {
            map[x][y] = 2;
            if (setWay(map, x + 1, y)) {   //下走
                return true;
            } else if (setWay(map, x, y + 1)) {  //右走
                return true;
            } else if (setWay(map, x, y - 1)) {  //左走
                return true;
            } else if (setWay(map, x - 1, y)) {  //上走
                return true;
            } else {  //走不通
                map[x][y] = 3;
                return false;
            }
        } else {
            return false;
        }
    }

    public static void showMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
