package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/* Action은 범위가 넓다...(버튼에는 클릭, 텍스트박스-엔터 등) 
 * 버튼에 클릭이벤트를 감지해보자!!*/
public class MyActionListener implements ActionListener{
	JTextField t_input;
	JTextArea area;
	
	public MyActionListener(JTextField t_input,JTextArea area) {
		this.area=area;
		this.t_input=t_input;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("나눌렀어?");
		
		String msg = t_input.getSelectedText();
		area.append(msg+"\n");
		t_input.setText("");
	}
}
