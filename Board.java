public class Board {


  /*
  黒-->0
  白-->1
  board[y][x]
  y-->
  */
  private int[][] board;


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
  
  public void changeBoard(int y, int x, int color){
    this.board[y][x] = color;
  }

  public int[][] getBoard() {
    return this.board;
  }
}
