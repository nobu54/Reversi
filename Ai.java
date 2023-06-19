import java.util.Random;

/**
* 対戦相手としてのCPUを実装するためのクラス
* @parm level CPUの強さを表す値。0が最弱
* @parm color CPUの色を表す値。0:黒、1:白
*/
public class Ai {

    int level;
    int color;

    /**
    * コンストラクタ
    */
    public Ai(int lv, int cl) {
        this.level = lv;
        this.color = cl;
    }

    /**
    * 置く場所を決定し駒を置くメソッド
    * @parm board
    * @parm rule
    */
    public void put(Board board) {
        
        // 置ける場所を取得
        String[] canPutList = Rule.canPlace(board.getBoard(), this.color).split(",");

        // 置く場所を決定(今回はランダム)
        Random r = new Random();
        String s = canPutList[r.nextInt(canPutList.length)];
        int[] put = Common.toIntPlace(s);
        
        // 置く処理
        Rule.put(board, color, put[1], put[0]);


    }
}
