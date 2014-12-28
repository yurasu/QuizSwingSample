import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Question {
	public void qust(int s, JLabel l, JRadioButton[] rd) {
		// 問題を出す。
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		l.setAlignmentY(Component.CENTER_ALIGNMENT);
		switch (s) {
		case 0:
			l.setText("変数の型でint型の最大値はいくつ?");
			rd[0].setText("1 : 256");
			rd[1].setText("2 : 98776342");
			rd[2].setText("3：2147483647");
			break;
		case 1:
			l.setText("変数の型を変換するのに使う方法はどれ？");
			rd[0].setText("1：キャスト");
			rd[1].setText("2：スコープ");
			rd[2].setText("3：インクリメント");
			break;
		case 2:
			l.setText("変数aが「4以上でかつ10以下」か「40未満」。正しい条件式はどれ？");
			rd[0].setText("1：(a >= 4 || a < 10) && a < 40");
			rd[1].setText("2：(a >= 4 || a <= 10) || a < 40");
			rd[2].setText("3：(a >= 4 && a <= 10) || a < 40");
			System.out.println("");
			break;
		default:
			l.setText("終わりです。");
		}
	}

	public boolean ans(int f, JRadioButton[] radi) {
		boolean answer = false;
		// 問題が正解か判断する。
		switch (f) {
		case 0:
			if (radi[2].isSelected()) {
				answer = true;
			}
			break;
		case 1:
			if (radi[0].isSelected()) {
				answer = true;
			}
			break;
		case 2:
			if (radi[2].isSelected()) {
				answer = true;
			}
			break;
		}
		// 問題が正解かを返す。
		return answer;
	}
}
