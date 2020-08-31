import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * CalculatorWithSwing
 */
public class CalculatorWithSwing extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel contentPane = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JTextField result = new JTextField("");
	/* 結果を一時的に保存する */
	double numTemp = 0.0;
	boolean flagTemp = false;
	boolean calcFlag = false;
	String opeTemp = "";

	/* GUIフレームのビルド */
	public CalculatorWithSwing() {
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(250, 300));
		this.setTitle("CalculatorWithSwing");
		this.setContentPane(contentPane);

		contentPane.add(result, BorderLayout.NORTH);

		final JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 4));
		contentPane.add(p, BorderLayout.CENTER);

		/* パネルに文字をセット */
		p.add(new NumberButton("7"), 0);
		p.add(new NumberButton("8"), 1);
		p.add(new NumberButton("9"), 2);
		p.add(new CalcButton("÷"), 3);
		p.add(new NumberButton("4"), 4);
		p.add(new NumberButton("5"), 5);
		p.add(new NumberButton("6"), 6);
		p.add(new CalcButton("×"), 7);
		p.add(new NumberButton("1"), 8);
		p.add(new NumberButton("2"), 9);
		p.add(new NumberButton("3"), 10);
		p.add(new CalcButton("－"), 11);
		p.add(new NumberButton("0"), 12);
		p.add(new ClearButton("C"), 13);
		p.add(new CalcButton("＝"), 14);
		p.add(new CalcButton("＋"), 15);

		this.setVisible(true);
	}

	/* 入力結果の表示 */
	public void showResult(final String c) {
		if (!calcFlag)
			result.setText(result.getText() + c);
		else {
			result.setText(c);
			calcFlag = false;
		}
	}

	/* 数字ボタン */
	public class NumberButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public NumberButton(final String n) {
			super(n);
			this.addActionListener(this);
		}

		public void actionPerformed(final ActionEvent e) {
			final String num = this.getText();
			showResult(num);
		}
	}

	/* 演算子ボタン */
	public class CalcButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public CalcButton(final String o) {
			super(o);
			this.addActionListener(this);
		}

		/* 計算 */
		public void actionPerformed(final ActionEvent e) {
			if (flagTemp) {
				final double resultValue = (Double.valueOf(result.getText())).doubleValue();

				if (opeTemp.equals("＋")) numTemp += resultValue;
				else if (opeTemp.equals("－")) numTemp -= resultValue;
				else if (opeTemp.equals("×")) numTemp *= resultValue;
				else if (opeTemp.equals("÷")) numTemp /= resultValue;

				result.setText(String.valueOf(numTemp));
			}

			opeTemp = this.getText();
			numTemp = (Double.valueOf(result.getText())).doubleValue();
			calcFlag = true;

			if (opeTemp.equals("＝")) flagTemp = false;
			else flagTemp = true;
		}
	}

	/* クリアボタン */
	public class ClearButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public ClearButton(String s) {
			super(s);
			this.addActionListener(this);
		}

		/* 初期化 */
		public void actionPerformed(final ActionEvent e) {
			numTemp = 0.0;
			calcFlag = false;
			flagTemp = false;
			result.setText("");
		}
	}

	public static void main(final String[] args){
		new CalculatorWithSwing();
	}
}