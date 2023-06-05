import java.util.Random;

public class Ai {

    int level;
    int color;

    public Ai(int lv, int cl) {
        this.level = lv;
        this.color = cl;
    }

    public void put(Board board, Rule rule) {
        
        // 置ける場所を取得
        String[] canPutList = rule.canPlace(board.getBoard(), this.color).split(",");

        // 置く場所を決定(今回はランダム)
        Random r = new Random();
        String s = canPutList[r.nextInt(canPutList.length)];
        int[] put = rule.toIntPlace(s);
        
        // 置く処理
        rule.put(board, color, put[1], put[0]);


    }
}
