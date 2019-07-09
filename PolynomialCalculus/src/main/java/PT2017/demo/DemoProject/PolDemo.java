package PT2017.demo.DemoProject;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

//GUI
public class PolDemo {
	
	JFrame appFrame;
	JLabel jlbFirstPol, jlbSecondPol, jlbResult; 
	JTextField jtfFirstPol, jtfSecondPol, jtfResult;
	JPanel panel1, panel2, panel3, panel4;
	JButton addPol, subPol, mulPol, derPol, integPol, clear;
	Polinom pol1 = new Polinom("");
	Polinom pol2 = new Polinom("");
	Polinom res = new Polinom();
	Operations o = new Operations();
	String resText = null;

	public PolDemo() {
		
		createGUI();
	}
	
	public void createGUI() {
		
		appFrame = new JFrame("Polynomial calculus");
		arrangeComponents();
   		appFrame.pack();
   		appFrame.setResizable(false);
   		appFrame.setVisible(true);
   		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	public void arrangeComponents() {
	
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		jlbFirstPol = new JLabel("First polynomial");
		jlbSecondPol = new JLabel("Second polynomial");
		jlbResult = new JLabel("Result");
		panel1.add(jlbFirstPol);
		panel2.add(jlbSecondPol);
		panel4.add(jlbResult);
		jtfFirstPol = new JTextField(40);
		jtfSecondPol = new JTextField(40);
		jtfResult = new JTextField(40);
		panel1.add(jtfFirstPol);
		panel2.add(jtfSecondPol);
		panel4.add(jtfResult);
		addPol = new JButton("Add");
		subPol = new JButton("Substract");
		mulPol = new JButton("Multiplication");
		derPol = new JButton("Differentiation 1");
		integPol = new JButton("Integration 1");
		clear = new JButton("Clear");
		panel3.add(addPol);
		panel3.add(subPol);
		panel3.add(mulPol);
		panel3.add(derPol);
		panel3.add(integPol);
		panel3.add(clear);
		addPol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.clearPol();
				jtfResult.setText(" ");
				pol1.polinom = jtfFirstPol.getText();
				Pattern pattern = Pattern.compile("(-?\\d+)[X]\\^(-?\\d+)");
				Matcher matcher1 = pattern.matcher(pol1.polinom);
				while (matcher1.find()) {
					pol1.addMonom(Integer.parseInt(matcher1.group(2)),  Integer.parseInt(matcher1.group(1)));
				}
				pol2.polinom = jtfSecondPol.getText();
				Matcher matcher2 = pattern.matcher(pol2.polinom);
				while (matcher2.find()) {
					pol2.addMonom(Integer.parseInt(matcher2.group(2)), Integer.parseInt(matcher2.group(1)));;
				}
				res = o.addPol(pol1, pol2);
				resText = res.toString();
				jtfResult.setText("");
				jtfResult.setText(resText);	
				res.clearPol();
			}
		});
		subPol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.clearPol();
				jtfResult.setText(" ");
				pol1.polinom = jtfFirstPol.getText();
				Pattern pattern = Pattern.compile("(-?\\d+)[X]\\^(-?\\d+)");
				Matcher matcher1 = pattern.matcher(pol1.polinom);
				while (matcher1.find()) {
					pol1.addMonom(Integer.parseInt(matcher1.group(2)),  Integer.parseInt(matcher1.group(1)));
				}
				pol2.polinom = jtfSecondPol.getText();
				Matcher matcher2 = pattern.matcher(pol2.polinom);
				while (matcher2.find()) {
					pol2.addMonom(Integer.parseInt(matcher2.group(2)), Integer.parseInt(matcher2.group(1)));;
				}
				res = o.subPol(pol1, pol2);
				resText = res.toString();
				jtfResult.setText("");
				jtfResult.setText(resText);	
				res.clearPol();
			}
		});
		mulPol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfResult.setText("");
				pol1.polinom = jtfFirstPol.getText();
				Pattern pattern = Pattern.compile("(-?\\d+)[X]\\^(-?\\d+)");
				Matcher matcher1 = pattern.matcher(pol1.polinom);
				while (matcher1.find()) {
					pol1.addMonom(Integer.parseInt(matcher1.group(2)),  Integer.parseInt(matcher1.group(1)));
				}
				pol2.polinom = jtfSecondPol.getText();
				Matcher matcher2 = pattern.matcher(pol2.polinom);
				while (matcher2.find()) {
					pol2.addMonom(Integer.parseInt(matcher2.group(2)), Integer.parseInt(matcher2.group(1)));;
				}
				res = o.mulPol(pol1, pol2);
				resText = res.toString();
				jtfResult.setText("");
				jtfResult.setText(resText);	
			}
		});
		derPol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfResult.setText(" ");
				pol1.polinom = jtfFirstPol.getText();
				Pattern pattern = Pattern.compile("(-?\\d+)[X]\\^(-?\\d+)");
				Matcher matcher1 = pattern.matcher(pol1.polinom);
				while (matcher1.find()) {
					pol1.addMonom(Integer.parseInt(matcher1.group(2)),  Integer.parseInt(matcher1.group(1)));
				}
				res = o.derive(pol1);
				resText = res.toString();
				jtfResult.setText("");
				jtfResult.setText(resText);	
			}
		});
		integPol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfResult.setText(" ");
				pol1.polinom = jtfFirstPol.getText();
				Pattern pattern = Pattern.compile("(-?\\d+)[X]\\^(-?\\d+)");
				Matcher matcher1 = pattern.matcher(pol1.polinom);
				while (matcher1.find()) {
					pol1.addMonom(Integer.parseInt(matcher1.group(2)),  Integer.parseInt(matcher1.group(1)));
				}
				res = o.integrate(pol1);
				resText = res.toString();
				jtfResult.setText("");
				jtfResult.setText(resText);	
			}
		});
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfResult.setText("");
				jtfFirstPol.setText("");;
				jtfSecondPol.setText("");
				resText = null;
				res = new Polinom();
				pol1 = new Polinom();
				pol2 = new Polinom();
			}
		});
		JPanel p = new JPanel();
		p.add(panel1);
		p.add(panel2);
		p.add(panel3);
		p.add(panel4);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		appFrame.setContentPane(p);
		appFrame.setVisible(true);
	}
}
