public class Player {

    private boolean isComputer;
    private int color;
    
    Player(boolean ic, int c) {
        if (c == 0 || c == 1){
            this.color = c;
        } else {
            // 想定外の値が入力された際の処理
        }
        this.isComputer = ic;
    }


    public boolean getIsCompter(){
        return this.isComputer;
    }

    public int getColor(){
        return this.color;
    }
}
