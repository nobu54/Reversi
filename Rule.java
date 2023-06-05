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

    public int[] toIntPlace(String place){
        String x = String.valueOf("ABCDEFGH".indexOf(place.charAt(0)));
        String y = String.valueOf(place.charAt(1));
        int[] xy = {Integer.parseInt(x) + 1, Integer.parseInt(y) };
        return xy;
    }

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

	public int getTurn() {
		return this.turn;
	}

    public void setTurn(int turn) {
        this.turn = turn;
    }

}
















