/**
* プレイヤーに関するクラス
* @parm isComputer コンピュータであるかどうか
* @parm color playerの色
*/
public class Player {

    /** プレイヤーがコンピュータであるかどうかを保持する変数
    * コンピュータである場合はTrue,そうで無い場合はfalse
    */
    private boolean isComputer;

    /** プレイヤーの色を保持する変数
    * プロイヤーが黒である場合は0
    * プレイヤーが白である場合は1
    */
    private int color;
    
    /** コンストラクタ*/
    Player(boolean ic, int c) {
        if (c == 0 || c == 1){
            this.color = c;
        } else {
            // 想定外の値が入力された際の処理
        }
        this.isComputer = ic;
    }

    /** isComputerのゲッター
    * @return isComputer
    */
    public boolean getIsComputer() {
        return this.isComputer;
    }

    /** colorのゲッター
    * @return color
    */
    public int getColor(){
        return this.color;
    }
}
