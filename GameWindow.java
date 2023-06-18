import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

/**
 * ゲーム画面を生成するクラス
 * @parm board ボード情報
 * @parm rule ルール
 * @parm p1 Player1
 * @parm p2 Player2
 * @parm highlight 置ける場所のハイライトの有無
 * @parm rw リザルトウィンドウ
 * @parm ai コンピューター
 */
public class GameWindow extends JFrame  {

    Board board;
    Rule rule;
    Player p1;
    Player p2;
    boolean highlight;
    ResultWindow rw;
    Ai ai;

    /**
     * ゲームウィンドウを生成するメソッド
     * @param p1 Player1
     * @param p2 Player2
     * @param highlight ハイライトの有無
     */
    public GameWindow(Player p1, Player p2, boolean highlight) {
        // Playerの初期化
        this.p1 = p1;
        this.p2 = p2;
        
        // ハイライトのon/off
        this.highlight = highlight;

        // 初手が1pか2pか
        int turn = 1;
        if (p2.getColor() == 0) turn = 2;

        // 盤とルールの初期化
        this.rule = new Rule(turn);
        this.board = new Board();

        // 相手がプレイヤー化コンピュータか
        ai = new Ai(0, p2.getColor());
        
        // windowの作成
        setTitle("オセロ");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.addMouseListener(new MyMouseListener());
        add(panel);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drowBoard(g);
    }

    /**
     * ボードを描画し、board情報に基づき駒を描画するメソッド
     * @param g Graphics
     */
    private void drowBoard(Graphics g) {
        // ボードを描画
        g.drawLine(80, 80, 80, 720);
        g.drawLine(160, 80, 160, 720);
        g.drawLine(240, 80, 240, 720);
        g.drawLine(320, 80, 320, 720);
        g.drawLine(400, 80, 400, 720);
        g.drawLine(480, 80, 480, 720);
        g.drawLine(560, 80, 560, 720);
        g.drawLine(640, 80, 640, 720);
        g.drawLine(720, 80, 720, 720);
        g.drawLine(80, 80, 720, 80);
        g.drawLine(80, 160, 720, 160);
        g.drawLine(80, 240, 720, 240);
        g.drawLine(80, 320, 720, 320);
        g.drawLine(80, 400, 720, 400);
        g.drawLine(80, 480, 720, 480);
        g.drawLine(80, 560, 720, 560);
        g.drawLine(80, 640, 720, 640);
        g.drawLine(80, 720, 720, 720);
       
        // 駒を描画
        int b[][] = board.getBoard();
        for (int i = 1; i < 9; i++) {
            for (int r = 0; r < 9; r++) {
                if (b[r][i] != -1) {
                    paintPiece(g, getWidth(r), getHeight(i), b[r][i]);
                }
            }
        }

        // ハイライトを描画
        if (highlight) {
            String[] hlplace;
            if (rule.getTurn() == 1) {
                hlplace = rule.canPlace(board.getBoard(), p1.getColor()).split(",");
            } else {
                hlplace = rule.canPlace(board.getBoard(), p2.getColor()).split(",");
            }

            for (String hlp: hlplace) {
                int[] h = Common.toIntPlace(hlp);
                paintHighLight(g, getHeight(h[1]), getWidth(h[0]));
            }

        }
    }

    /**
     * ハイライトを描画するメソッド
     * @param g Graphcs
     * @param y y軸
     * @param x x軸
     */
    private void paintHighLight(Graphics g, int y, int x) {
       int width = 20;
       int height = 20;
       x += 20;
       y += 20;
       g.setColor(Color.RED);
       g.fillOval(x, y, width, height); 
    }

    /**
     * y軸の座標を受け取り、その座標がウィンドウ中ではどの位置になるかを計算して返すメソッド
     * @param position y軸(縦)。1～8
     * @return ウィンドウ中での位置(y軸のみ)
     */
    int getHeight(int position) {
        return 10 + 80 * position;
    }

    // getHorizoneの方がいいかも？
    /**
     * アルファベットでx軸を受け取り、その座標がウィンドウ中ではどの位置になるかを計算して返すメソッド
     * @param positionStr x軸。A～H
     * @return ウィンドウ中での位置(x軸のみ)
     */
    int getWidth(String positionStr) {
        String str = "ABCDEFGH";
        int position = str.indexOf(positionStr) + 1;
        return 10 + 80 * position;
    }

    /**
     * 数値でx軸を受け取り、その座標がウィンドウ中ではどの位置になるかを計算して返すメソッド
     * @param position x軸。1～8
     * @return ウィンドウ中での位置(x軸のみ)
     */
    int getWidth(int position) {
        return 10 + 80 * position;
    }
   
    /**
     * 駒を盤面に描画するメソッド
     * @param g Graphics
     * @param y y軸。ウィンドウの中でのposition。1～8の座標で指定ではない
     * @param x x軸。ウィンドウの中でのposition。A～Hの座標で指定ではない
     * @param color 色
     */
    private void paintPiece(Graphics g, int y, int x, int color) {
        int width = 60;
        int height = 60;
        if (color == 0) g.setColor(Color.BLACK);
        else if (color == 1) g.setColor(Color.WHITE);
        else g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }

    /**
     * マウスを右クリックした時に呼ばれる処理
     * 駒を置きたいときにマウスクリックを行う
     */
    class MyMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
        // 位置を取得
        int x = e.getX();
        int y = e.getY();
        if (x > 72 && x < 712 && y > 50 && y < 690) {
            int posx = (x-72) / 80;
            int posy = (y-50) / 80;
            posx++;
            posy++;
            pvp(posy, posx);
        }
    }

    /**
     * 駒を置き、置いた後にターンを相手に移すメソッド
     * @param posy y軸。1～8
     * @param posx x軸。1～8
     */
    private void pvp(int posy, int posx) {
        // boardへ置く処理
        if (rule.getTurn() == 1) {
            // 指定した場所がおける場所かどうか、置けるなら置く
            if (rule.put(board, p1.getColor(), posy, posx)) { 
                // 相手が置けるかどうか。置ける場合ターンを相手に移す
                if (rule.canPlace(board.getBoard(), p2.getColor()).length() > 1) {
                    rule.setTurn(2);
                    // 相手がコンピュータの場合
                    if (p2.getIsComputer()) {
                        ai.put(board, rule);
                        rule.setTurn(1);
                    }
                } else {
                    // 自分も置けない場合試合終了
                    if (rule.canPlace(board.getBoard(), p1.getColor()).length() < 2) {
                        rw = new ResultWindow(p1, p2, board);
                    }
                }
            }
        } else if (rule.getTurn() == 2) {
            if (rule.put(board, p2.getColor(), posy, posx)) {
                if (rule.canPlace(board.getBoard(), p1.getColor()).length() > 1) {
                    rule.setTurn(1);
                } else {
                    if (rule.canPlace(board.getBoard(), p2.getColor()).length() < 2) {
                        rw = new ResultWindow(p1, p2, board);
                    }
                }
            }
        }

        repaint();
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    }

}
