import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
* このウィンドウでは、ゲームを始める際の設定を決める
* 設定内容：CPU戦かPVPか,自分(1p)の色,置ける場所をハイライトするか否か
* 対戦相手 1p vs CPU or 1p vs 2p (radiogoup radioButton。デフォは1p vs cpu)
* 自分の色　黒（先手） or 白（後手）　（radiogroup radiobutton デフォは1pが黒）
* 置ける場所のハイライトのon off　（チェックボックス。チェックを入れるとon。デフォでonが良いかな）
* @parm p1 Player1
* @parm p2 Player2
*/
public class MainWindow{
    private Player p1;
    private Player p2;

    /**
     * ウィンドウの作成
     */
    public MainWindow() {

        // ウィンドウ内に表示するテキスト
        String windowTitle = "ゲーム設定";
        String battleModeText = "対戦形式";
        String vsCpuText = "1p vs CPU";
        String vsHumanText = "1p vs 2p";
        String colorSelectText = "1Pの色";
        String colorBlack = "黒         ";
        String colorWhite = "白         ";
        String isHighLightText = "<html>置ける場所を<br>ハイライト表示</html>";
        String startText = "START";


        JFrame f = new JFrame();
        BoxLayout boxlayout = new BoxLayout(f.getContentPane(),BoxLayout.X_AXIS);
        f.setLayout(boxlayout);
        f.setTitle(windowTitle);
        f.setSize(320, 150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        TitledBorder opponentBorder = BorderFactory.createTitledBorder(lineBorder, battleModeText);
        JPanel opponentPanel = new JPanel();
        opponentPanel.setLayout(new BoxLayout(opponentPanel, BoxLayout.PAGE_AXIS));
		opponentPanel.setBorder(opponentBorder);

        TitledBorder seleceColorBorder = BorderFactory.createTitledBorder(lineBorder, colorSelectText);
        JPanel selectColorPanel = new JPanel();
        selectColorPanel.setLayout(new BoxLayout(selectColorPanel, BoxLayout.PAGE_AXIS));
        selectColorPanel.setBorder(seleceColorBorder);

        ButtonGroup bgroupOpponent = new ButtonGroup();
        ButtonGroup bgroupSelectColor = new ButtonGroup();
        JRadioButton radioOpponent1 = new JRadioButton(vsCpuText);
        JRadioButton radioOpponent2 = new JRadioButton(vsHumanText);
        JCheckBox highlight = new JCheckBox(isHighLightText);
        JRadioButton radioSelectBlack = new JRadioButton(colorBlack);
        JRadioButton radioSelectWhite = new JRadioButton(colorWhite);
        JButton startButton = new JButton(startText);

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

    /**
     * ラジオボタンで選択されているボタンのテキストを取得するメソッド 
     * @param buttonGroup ラジオボタンのグループ
     * @return ボタンのテキスト
     */
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