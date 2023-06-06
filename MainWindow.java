import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
/*
このウィンドウで決めること
・対戦相手 1p vs CPU or 1p vs 2p (radiogoup radioButton。デフォは1p vs cpu)
・置ける場所のハイライトのon off　（チェックボックス。チェックを入れるとon。デフォでonが良いかな）
・自分の色　黒（先手） or 白（後手）　（radiogroup radiobutton デフォは1pが黒）
*/

/*
雑記
Window.javaを作ってそれを継承していった方がいいかなぁと思いました。
*/
public class MainWindow{
    private Player p1;
    private Player p2;

    public MainWindow() {
        JFrame f = new JFrame();
        BoxLayout boxlayout = new BoxLayout(f.getContentPane(),BoxLayout.X_AXIS);
        f.setLayout(boxlayout);
        f.setTitle("ゲーム設定");
        f.setSize(300, 150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        TitledBorder opponentBorder = BorderFactory.createTitledBorder(lineBorder, "対戦形式");
        JPanel opponentPanel = new JPanel();
        opponentPanel.setLayout(new BoxLayout(opponentPanel, BoxLayout.PAGE_AXIS));
		opponentPanel.setBorder(opponentBorder);

        TitledBorder seleceColorBorder = BorderFactory.createTitledBorder(lineBorder, "自分の色");
        JPanel selectColorPanel = new JPanel();
        selectColorPanel.setLayout(new BoxLayout(selectColorPanel, BoxLayout.PAGE_AXIS));
        selectColorPanel.setBorder(seleceColorBorder);

        ButtonGroup bgroupOpponent = new ButtonGroup();
        ButtonGroup bgroupSelectColor = new ButtonGroup();
        JRadioButton radioOpponent1 = new JRadioButton("1p vs CPU");
        JRadioButton radioOpponent2 = new JRadioButton("1p vs 2p");
        JCheckBox highlight = new JCheckBox("Highlight");
        JRadioButton radioSelectBlack = new JRadioButton("黒       ");
        JRadioButton radioSelectWhite = new JRadioButton("白       ");
        JButton startButton = new JButton("START");

        bgroupOpponent.add(radioOpponent1);
        bgroupOpponent.add(radioOpponent2);
        bgroupSelectColor.add(radioSelectBlack);
        bgroupSelectColor.add(radioSelectWhite);

        radioOpponent1.setSelected(true);
        highlight.setSelected(true);
        radioSelectBlack.setSelected(true);
        opponentPanel.add(radioOpponent1);
        opponentPanel.add(radioOpponent2);
        panel.add(highlight);
        selectColorPanel.add(radioSelectBlack);
        selectColorPanel.add(radioSelectWhite);
        panel.add(startButton);

        f.add(opponentPanel);
        f.add(selectColorPanel);
        f.add(panel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opponent = getSelectedButtonText(bgroupOpponent);
                String myColor = getSelectedButtonText(bgroupSelectColor);
                boolean isHighlight = highlight.isSelected();
        
                int p1color = 0;
                int p2color = 1;
                boolean isCom = true;
                if (myColor.equals("黒")) {
                    p1color = 0;
                    p2color = 1;
                } else if (myColor.equals("白")) {
                    p1color = 1;
                p2color = 0;
                }
                if (opponent.equals("1p vs CPU")) {
                    isCom = true;
                } else if (opponent.equals("1p vs 2p")){
                    isCom = false;
                }
                p1 = new Player(false, p1color);
                p2 = new Player(isCom, p2color);
                GameWindow gw = new GameWindow(p1, p2, isHighlight);
                f.dispose();
            }
        });

        f.setVisible(true);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
