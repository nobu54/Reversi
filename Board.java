/**
* オセロの盤面に対応するクラス
* @parm board オセロの盤面
*/
public class Board {

    /**
    * board[y][x]
    * y 盤面でいう上から何行目かを指定します
    * x 盤面でいう左から何列目かを指定します
    * boardはxとyが1〜8までの範囲で実際の盤面を表します
    * board[0,9][*]、board[*][0,9]は盤面ではなく便宜上外枠として-1を配置します
    * boardに入る値は-1,0,1の3つ
    * -1 何も駒が置かれていない(又は外枠である)事を表します
    * 0 黒い駒が置かれている事を表します
    * 1 白い駒が置かれている事を表します
    */
    private int[][] board;

    /** コンストラクタ*/
    Board() {
        /* boardの初期化 */
        this.board = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
            this.board[i][j] = -1;
          }
        }
        // 初期配置のセッティング
        this.board[4][4] = 1; // 左上
        this.board[5][5] = 1; // 右下
        this.board[4][5] = 0; // 左下
        this.board[5][4] = 0; // 右上
    }

    /**
    * 盤面上の指定したマスの色を変更するメソッド
    * @parm y y軸 指定する範囲は1〜8
    * @parm x x軸 指定する範囲は1〜8
    * @parm color 変更後の色。指定する値は0か1
    */
    public void changeBoard(int y, int x, int color){
        this.board[y][x] = color;
    }

    /** 盤面のゲッター */
    public int[][] getBoard() {
        return this.board;
    }
}
