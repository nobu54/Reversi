/**
 * リバーシのルールにかかわる内容をまとめたクラス
*/
public class Rule{

    /**
     * 駒を置く手番が現在どちらであるかを保存する変数
     * 格納される値は1又は2のどちらか
     * 値が1の場合は1Pのターン。値が2の場合2Pのターン
     */
    private int turn;

    /**
     * コンストラクタ
     */
    Rule(int turn) {
        this.turn = turn;
    }

    /**
     * 盤面の座標ぞint[]型で受け取り、String型に変換して返すメソッド
     * @param place ボードの配置を配列の[0]と[1]に入れたもの
     * @return A1～A8を返す
     */
    private String toStringPlace(int[] place){
        // int[]型で受け取った座標をString型にして返す
        return "ABCDEFGH".charAt(place[0]-1) + String.valueOf(place[1]);
    }

    /**
     * 盤面の座標x,y(int型)を受け取り、String型に変換して返すメソッド
     * @param y boardのy座標
     * @param x boardのx座標
     * @return A1～H8を返す
     */
    private String toStringPlace(int y, int x){
        // int[]型で受け取った座標をString型にして返す
        return "ABCDEFGH".charAt(x-1) + String.valueOf(y);
    }

    /**
     * 盤面の座標をString型で受け取り、int[]型に変換して返すメソッド
     * @param place boardの座標をA1～H8で表した値
     * @return xy[0]はx座標。xy[1]はy座標
     */
    public int[] toIntPlace(String place){
        String x = String.valueOf("ABCDEFGH".indexOf(place.charAt(0)));
        String y = String.valueOf(place.charAt(1));
        int[] xy = {Integer.parseInt(x) + 1, Integer.parseInt(y) };
        return xy;
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
    public String turnPlace(int[][] board, int posy, int posx, int color){
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

                p = toStringPlace(y, x) + ",";
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
                    p += toStringPlace(y, x) + ",";
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
    public boolean put(Board board, int color, int y, int x){
        String[] placeList = turnPlace(board.getBoard(), y, x, color).split(",");
        if (placeList[0].length() < 2) {
            return false;
        }  
        // ボードに駒を置く
        board.changeBoard(y, x, color);
        
        // 置いたことにより他の駒をひっくり返す
        for (String pl : placeList) {
            int[] place = toIntPlace(pl);
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
    public String canPlace(int[][] board, int color) {
        String place = "";
        for (int i = 1; i < 9; i++) {
            for (int r = 1; r < 9; r++) {
                String[] placeList = turnPlace(board, r, i, color).split(",");
                if (placeList[0].length() > 1) {
                    place += toStringPlace(r, i) + ",";
                }
            }
        }
        return place;
    }

    /** turnのゲッター */
	public int getTurn() {
		return this.turn;
	}

    /** ターンのセッター */
    public void setTurn(int turn) {
        this.turn = turn;
    }

}