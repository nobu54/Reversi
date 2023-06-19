/**
 * リバーシのルールにかかわる内容をまとめたクラス
*/
public class Rule{

    /**
     * 駒を置く手番が現在どちらであるかを保存する変数
     * 格納される値は1又は2のどちらか
     * 値が1の場合は1Pのターン。値が2の場合2Pのターン
     */
    static int turn;

    /**
     * 初期化
     */
    static void initialize(int t){
        turn = t;
    }

    /**
     * posy,posxにcolor色の駒をとした時、ひっくり返す対象となる座標を返す。
     * 座標を調べるだけで実際にひっくり返す処理は行わない
     * @param board boardの状態
     * @param posy 置く場所のy座標
     * @param posx 置く場所のx座標
     * @param color 置く色0～1
     * @return ひっくり返る対象となる座標。実際には"A3,E1,D4,"と返す。ひっくり返せる場所が無い場合は""を返す
     */
    public static String turnPlace(int[][] board, int posy, int posx, int color){
        String place = "";
        if (board[posy][posx] != -1) return place;
        
        String p = "";
        // 上下左右斜めでおける場所があるか
        for (int i = -1; i < 2; i++) {
            for (int r = -1; r < 2; r++) {
                if (i == 0 && r == 0) continue;

                int x = posx + i;
                int y = posy + r;

                // 置いた場所の隣が置いた石と同じもしくは何も置いていない場合
                if (board[y][x] == color || board[y][x] == -1) continue;

                p = Common.toStringPlace(y, x) + ",";
                while (true) {
                    x +=i;
                    y +=r;

                    // 石が置いてない所に当たった場合
                    if (board[y][x] == -1) break;
                    // 置いた石と同じ色に当たった場合
                    if (board[y][x] == color) {
                        place += p;
                        break;
                    }
                    // 置いた石と違う色の場合
                    p += Common.toStringPlace(y, x) + ",";
                }
            }
        }
        return place;
    }

    /**
     * ボード内の指定した位置に駒を置き、ルールに基づいて周りの駒をひっくり返すメソッド 
     * @param board boardのインスタンス
     * @param color 置く色0,1
     * @param y y軸
     * @param x x軸
     * @return 置くことが出来たらtrue、置くことが出来なかった場合はfalseを返す
     */
    public static boolean put(Board board, int color, int y, int x){
        String[] placeList = turnPlace(board.getBoard(), y, x, color).split(",");
        if (placeList[0].length() < 2) {
            return false;
        }  
        // ボードに駒を置く
        board.changeBoard(y, x, color);
        
        // 置いたことにより他の駒をひっくり返す
        for (String pl : placeList) {
            int[] place = Common.toIntPlace(pl);
            board.changeBoard(place[1], place[0], color);
        }
        return true;
    }

    /**
     * 色を指定し、その色の駒を置くことが出来る座標をまとめて返す 
     * @param board boardの情報
     * @param color 色を指定
     * @return 置ける場所をString型で返す。例"E2,H4,A5,"置ける場所が無い場合は""を返す
     */
    public static String canPlace(int[][] board, int color) {
        String place = "";
        for (int i = 1; i < 9; i++) {
            for (int r = 1; r < 9; r++) {
                String[] placeList = turnPlace(board, r, i, color).split(",");
                if (placeList[0].length() > 1) {
                    place += Common.toStringPlace(r, i) + ",";
                }
            }
        }
        return place;
    }
}