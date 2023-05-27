import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameWindow extends JFrame  {

  Board board;
  Rule rule;

  public GameWindow() {

    // 盤とルールの生成
    this.board = new Board();
    this.rule = new Rule();

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

  public void start() {
    System.out.println("start!!!");
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    paintLine(g);
  }

  void paintLine(Graphics g) {
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
    paintWhite(g, getHeigth(4), getWidth("D"));
    paintWhite(g, getHeigth(5), getWidth("E"));
    paintBlack(g, getHeigth(5), getWidth("D"));
    paintBlack(g, getHeigth(4), getWidth("E"));

  }

  int getHeigth(int position) {
    return 10 + 80 * position;
  }

  int getWidth(String positionStr) {
    String str = "ABCDEFGH";
    int position = str.indexOf(positionStr) + 1;
    return 10 + 80 * position;
  }

  void paintWhite(Graphics g, int x, int y) {
    int width = 60;
    int height = 60;
    g.setColor(Color.WHITE);
    g.fillOval(x, y, width, height);
  }

  void paintBlack(Graphics g, int x, int y) {
    int width = 60;
    int height = 60;
    g.setColor(Color.BLACK);
    g.fillOval(x, y, width, height);
  }

  class MyMouseListener implements MouseListener {
    public void mouseClicked(MouseEvent e) {
      // 位置を取得
      int x = e.getX();
      int y = e.getY();
      if (x > 72 && x < 712 && y > 50 && y < 690) {
        int posx = (x-72) / 80;
        int posy = (y-50) / 80 ;
        // ルールのチェック
        // int[][][] place = rule.canPlacing(board.getBoard(), posx, posy);
        // for (int i = 0; )
        // System.out.println(Arrays.deepToString(place));
        System.out.println(rule.turnPlace(board.getBoard(), posx, posy));

        // boardへ置く処理
      }
      
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

  }

}
