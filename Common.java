/**
 * どこのクラスにも所属しない処理をまとめたクラス
 */
public class Common {

    /**
     * 盤面の座標x,y(int型)を受け取り、String型に変換して返すメソッド
     * @param y boardのy座標
     * @param x boardのx座標
     * @return A1～H8を返す
     */
    static String toStringPlace(int y, int x){
        // int[]型で受け取った座標をString型にして返す
        return "ABCDEFGH".charAt(x-1) + String.valueOf(y);
    }

    /**
     * 盤面の座標をString型で受け取り、int[]型に変換して返すメソッド
     * @param place boardの座標をA1～H8で表した値
     * @return xy[0]はx座標。xy[1]はy座標
     */
    static int[] toIntPlace(String place){
        String x = String.valueOf("ABCDEFGH".indexOf(place.charAt(0)));
        String y = String.valueOf(place.charAt(1));
        int[] xy = {Integer.parseInt(x) + 1, Integer.parseInt(y) };
        return xy;
    }

    /**
     * colorをString型で返すメソッド
     * @param color 0又は1
     * @return "黒"、又は"白"を返す
     */
    static String getStringColor(int color) {
        if (color == 0) return "黒";
        return "白"; 
    }

    /**
    * 盤面に置かれたコマの数を数えるメソッド
    * @parm board 盤面の状態
    * @parm color カウントする色
    * @return 駒の数
    */
    static int countPiece(int[][] board, int color) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int r = 0; r < 9; r++) {
                if (board[r][i] == color) count++;
            }
        }
        return count;
    }

}
