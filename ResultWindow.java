import javax.swing.*;
import java.awt.*;

/**
 * リザルトを表示を作成するクラス
 */
public class ResultWindow {

    /**
     * リザルトを表示するウィンドウを生成するクラス
     * @param p1 Player1のインスタンス
     * @param p2 Player2のインスタンス
     * @param board boardのインスタンス
     */
    public ResultWindow(Player p1, Player p2, Board board) {

        // テキスト生成
        int p1count = board.countPiece(p1.getColor());
        int p2count = board.countPiece(p2.getColor());
        String winner = "";
        if (p1count > p2count) winner = "Player1の勝利！";
        else if (p1count < p2count ) winner = "Player2の勝利！";
        else if (p1count == p2count) winner = "drow！";
        String p1info = "Player1(" + p1.getStringColor() + "):" + p1count + "    ";
        String p2info = "Player2(" + p2.getStringColor() + "):" + p2count;


        JFrame f = new JFrame();
        BoxLayout boxlayout = new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS);
        f.setLayout(boxlayout);
        f.setTitle("result");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel winnerPanel = new JPanel();
        winnerPanel.setLayout(new BoxLayout(winnerPanel, BoxLayout.Y_AXIS));
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
        JLabel winLabel = new JLabel(winner);
        JLabel p1Label = new JLabel(p1info);
        JLabel p2Label = new JLabel(p2info);
        winLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 30));
        p1Label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
        p2Label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 15));
        winLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        winnerPanel.add(winLabel);
        playerPanel.add(p1Label);
        playerPanel.add(p2Label);
        f.add(winnerPanel);
        f.add(playerPanel);
        f.setVisible(true);
    }
}