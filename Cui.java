public class Cui {
    public void printBoard(int[][] board){

        System.out.println("　 ｜ Ａ   Ｂ   Ｃ   Ｄ   Ｅ   Ｆ   Ｇ   Ｈ");
        System.out.println("　ーーーーーーーーーーーーーーーーーーーーー");
        for (int i = 1; i < 9; i++) {  // 縦移動
            System.out.print("　");
            for (int j = 0; j < 9; j++) {  // 横移動
                if (j == 0) {
                    System.out.print(i+"");
                    continue;
                }
                System.out.print("｜");
                if (board[i][j] == -1) System.out.print("   ");
                else if (board[i][j] == 0) System.out.print(" ● ");
                else if (board[i][j] == 1) System.out.print(" ○ ");
                if (j == 8) System.out.print("｜");
            }
            System.out.println("\n　ーーーーーーーーーーーーーーーーーーーーー");
        }
    }
}
