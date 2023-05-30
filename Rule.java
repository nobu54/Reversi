import java.util.ArrayList;
import java.util.Arrays;

public class Rule{

	// 1-->1p, 2-->2p
	private int turn;

	Rule(int turn) {
		this.turn = turn;
  	}

	private String toStringPlace(int[] place){
		// int[]型で受け取った座標をString型にして返す
		return "ABCDEFGH".charAt(place[0]-1) + String.valueOf(place[1]);
	}

	private String toStringPlace(int y, int x){
		// int[]型で受け取った座標をString型にして返す
		return "ABCDEFGH".charAt(x-1) + String.valueOf(y);
	}

    private int[] toIntPlace(String place){
        System.out.println("place" + place);
        String x = String.valueOf("ABCDEFGH".indexOf(place.charAt(0)));
        String y = String.valueOf(place.charAt(1));
        int[] xy = {Integer.parseInt(x) + 1, Integer.parseInt(y) };
        return xy;
    }

	public String turnPlace(int[][] board, int posy, int posx, int color){
		String place = "";
		// posx++;
		// posy++;
        System.out.println("posx:" + posx + "  posy:" + posy + "color:" + board[posy][posx]);
		if (board[posy][posx] != -1) return place + "既に石が置いてある";
        
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

    public void put(Board board, int color, int y, int x){
        
        String[] placeList = turnPlace(board.getBoard(), y, x, color).split(",");

        // System.out.println("y: " + y + "--x: " + x);
        // System.out.println(turnPlace(board.getBoard(), y, x, color));
        // ボードに駒を置く
        board.changeBoard(y, x, color);
        Cui cui = new Cui();
        cui.printBoard(board.getBoard());
        
        // 置いたことにより他の駒をひっくり返す
        for (String pl : placeList) {
            // System.out.println(pl+ "loop");
            int[] place = toIntPlace(pl);
            board.changeBoard(place[1], place[0], color);
        }
        cui.printBoard(board.getBoard());
    }

	public int getTurn() {
		return this.turn;
	}

}
















