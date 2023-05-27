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

	private String toStringPlace(int x, int y){
		// int[]型で受け取った座標をString型にして返す
		return "ABCDEFGH".charAt(x-1) + String.valueOf(y);
	}

	public String turnPlace(int[][] board, int posx, int posy){
		String place = "";
		posx++;
		posy++;
		if (board[posx][posy] != -1) return place + "既に石が置いてある";

		// 上下左右斜めでおける場所があるか
		for (int i = -1; i < 2; i++) {
			for (int r = -1; r < 2; r++) {
				if (i == 0 && r == 0) continue;

				int x = posx + i;
				int y = posy + r;

				// 置いた場所の隣が置いた石と同じもしくは何も置いていない場合
				if (board[x][y] == this.turn || board[x][y] == -1) continue;

				String p = toStringPlace(x,y) + ",";
				while (true) {
					x +=i;
					y +=r;

					// 石が置いてない所に当たった場合
					if (board[x][y] == -1) break;
					// 置いた石と同じ色に当たった場合
					if (board[x][y] == this.turn) {
						place += p;
						break;
					}
					// 置いた石と違う色の場合
					p += toStringPlace(x,y) + ",";
				}
			}
		}
		return place;
	}

    public void put(int[][] board, int color, int x, int y){
        board[x][y] = color;
    }

	public int getTurn() {
		return this.turn;
	}

}
















