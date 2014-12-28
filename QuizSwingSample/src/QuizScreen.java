import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class QuizScreen extends JFrame implements ActionListener {
	public static void main(String[] args) {
		QuizScreen sc = new QuizScreen();
		sc.setVisible(true);
	}

	JRadioButton[] radio = new JRadioButton[4];
	JLabel label = new JLabel();
	int i = 0;
	ArrayList<Integer> list = new ArrayList<Integer>();
	Question ques = new Question();
	JPanel p = new JPanel();
	JPanel labelPanel;
	JButton btn;
	Container cont = getContentPane();
	JPanel btnPanel = new JPanel();
	int answer = 0;
	private AudioClip seikai = Applet.newAudioClip(getClass().getResource(
			"Seikai01-1.wav"));
	private AudioClip huseikai = Applet.newAudioClip(getClass().getResource(
			"Huseikai01-1.wav"));
	private AudioClip results = Applet.newAudioClip(getClass().getResource(
			"Results02-1.wav"));
	private AudioClip startmusic = Applet.newAudioClip(getClass().getResource(
			"START!!.wav"));

	public QuizScreen() {

		// 画面や出現位置の初期設定
		setSize(500, 400);
		setTitle("クイズサンプル");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// ボタンなどの部品の配置
		startmusic.play();
		btn = new JButton("スタート");
		btn.setActionCommand("スタート");
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setAlignmentY(Component.CENTER_ALIGNMENT);
		p.add(btn);
		cont.add(p, BorderLayout.CENTER);

		btn.addActionListener(this);
		// 起動時処理
		for (int i = 0; i < 3; i++) {
			list.add(i);
			Collections.shuffle(list);
		}
	}

	// 画面更新の内容
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ((cmd.equals("スタート") || cmd.equals("次へ")) && i < 3) {
			p.remove(btn);
			btn = null;
			btn = new JButton("確認");
			btn.setActionCommand("確認");
			btn.addActionListener(this);
			btnPanel.add(btn);
			labelPanel = new JPanel();
			radio[0] = new JRadioButton("1");
			radio[1] = new JRadioButton("2");
			radio[2] = new JRadioButton("3");
			ques.qust(list.get(i), label, radio);

			ButtonGroup btnG = new ButtonGroup();
			btnG.add(radio[0]);
			btnG.add(radio[1]);
			btnG.add(radio[2]);
			labelPanel.add(label);
			p.add(radio[0]);
			p.add(radio[1]);
			p.add(radio[2]);
			cont.add(labelPanel, BorderLayout.NORTH);
			cont.add(btnPanel, BorderLayout.SOUTH);
			cont.validate();
			repaint();
		} else if (cmd.equals("確認") && ques.ans(list.get(i), radio)) {
			seikai.play();
			JOptionPane.showMessageDialog(this, "正解です。");
			btn = null;
			btn = new JButton("次へ");
			btn.setActionCommand("次へ");
			p.removeAll();
			labelPanel.removeAll();
			btnPanel.removeAll();
			btn.addActionListener(this);
			p.add(btn);
			i++;
			answer++;
			cont.validate();
			repaint();
		} else if (cmd.equals("確認") && ques.ans(list.get(i), radio) == false) {
			huseikai.play();
			JOptionPane.showMessageDialog(this, "不正解です。");
			btn = null;
			btn = new JButton("次へ");
			btn.setActionCommand("次へ");
			p.removeAll();
			labelPanel.removeAll();
			btnPanel.removeAll();
			btn.addActionListener(this);
			p.add(btn);
			i++;
			cont.validate();
			repaint();
		} else if (cmd.equals("終わり")) {
			System.exit(1);
		} else if (i >= 3) {
			p.removeAll();
			results.play();
			btn.setText("終わり");
			btn.setActionCommand("終わり");
			p.add(btn);
			String ans = "正解数は" + String.valueOf(answer) + "です。";
			label.setText(ans);
			labelPanel.add(label);
			cont.validate();
			repaint();
		}

	}
}
