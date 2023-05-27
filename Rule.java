import java.util.ArrayList;
import java.util.Arrays;

public class Rule{

	// 白黒どっちの手番かを記憶するための変数
	private int turn;

	Rule() {
		this.turn = 0;
  	}

  	/**
  	 * @parm	board	盤面の情報
  	 * @parm	posx	石を置く場所のx軸(範囲は1～8)
  	 * @parm	posy	石を置く場所のy軸(範囲は1～8)
  	 * @return	boolean	石が置けるかどうか(trueを返す時は置ける)
  	 */
  	/*
	public List<String> canPlacing(int[][] board, int posx, int posy) {
		List<String> placeList = new ArrayList<String>();
		posx++;
		posy++;
		// int p = 0;
		// int[][][] placeList = new int[8][18][2];
		// 指定されたポジションが空いているか
		// if (board[posx][posy] != -1) return false;
		if (board[posx][posy] != -1) return placeList;


		// 上下左右斜めでおける場所があるか
		for (int i = -1; i < 2; i++) {
			for (int r = -1; r < 2; r++) {
				if (i == 0 && r == 0) continue;
				// if (findStone(board, posx, posy, i, r)) return true;
				//placeList[p] = returnPlace(board, posx, posy, i, r);
				// p++;
				// place.add(new ArrayList<Integer>(Arrays.asList(1,2,3)));
				// placeList
			}
		}
		// return false;
		return placeList;
	}
	*/

	/**
	 * @parm	board	盤面の情報
  	 * @parm	x		石を置く場所のx軸(範囲は1～8)
  	 * @parm	y		石を置く場所のy軸(範囲は1～8)
  	 * @parm	movex	x軸に移動する量(範囲は-1～1)
  	 * @parm	movey	y軸に移動する量(範囲は-1～1)
  	 * @return	boolean	石が置けるかどうか(trueを返す時はおける)
  	 */
  	/*
	private boolean findStone(int[][] board, int x, int y, int movex, int movey) {

		x +=movex;
		y +=movey;
		if (board[x][y] == this.turn || board[x][y] == -1) return false;
		while (true) {
			x +=movex;
			y +=movey;
			if (board[x][y] == -1) return false;
			if (board[x][y] == this.turn) return true;
		}
	}
*/
	/**
	 * @parm	board	盤面の情報
  	 * @parm	x		石を置く場所のx軸(範囲は1～8)
  	 * @parm	y		石を置く場所のy軸(範囲は1～8)
  	 * @parm	movex	x軸に移動する量(範囲は-1～1)
  	 * @parm	movey	y軸に移動する量(範囲は-1～1)
  	 * @return	place	ひっくり返す場所を返す。ひっくり返せない場合は[0][0]に-1を入れて返す。
  	 */ 
  	/*
	public int[][] returnPlace(int[][] board, int x, int y, int movex, int movey) {
		// board[posx][posy]に置いたとき、ひっくり返すことの出来るマスを返す

		int[][] place = new int[18][2];
		int i = 0;
		x +=movex;
		y +=movey;

		// 置いた場所の隣が置いた石と同じもしくは何も置いていない場合
		if (board[x][y] == this.turn || board[x][y] == -1) {
			place[0][0] = -1;
			return place;
		}

		while (true) {
			x +=movex;
			y +=movey;

			// 石が置いてない所に当たった場合
			if (board[x][y] == -1) {
				place[0][0] = -1;
				return place;	
			}

			// 置いた石と同じ色に当たった場合
			if (board[x][y] == this.turn) return place;

			// 置いた違う色の場合
			place[i][0] = x;
			place[i][1] = y;
			i++;
		}
		

	}
	*/
/*
	private int[] toIntPlace(String place) {
		// String型で受け取った座標をint[]型にして返す
		return {"ABCDEFGH".indexOf(place.charAt(0)), Integer.parseInt(place.charAt(1))};
	}
*/
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

				// int[][] place = new int[18][2];
				// int i = 0;
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

	public int getTurn() {
		return this.turn;
	}

}
















