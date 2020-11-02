/*
 * 자바의 컴포넌트에 이미지를 넣어보자!!(이미지 아이콘!!)
 * */
package day1102.icon;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class IconApp extends JFrame{
	JButton bt;
	ImageIcon icon;
	public IconApp() {
		//os에 의존적인 경로를 사용하지 말고 클래스 패스를 기준으로 한 경로로 자원을 이용해 보자!!
		
		//icon = new ImageIcon("C:/Users/SeoJinHo/eclipse-workspace1/SeProject/res/data/Twitter.png");
		URL url = this.getClass().getClassLoader().getResource("res/Twitter.png");
		icon = new ImageIcon(url);
		bt = new JButton(icon);
		
		
		//Imane클래스는 이미지의 크기를 조정할 수 있는 기능이 있다.. getScaledInstace..
		Image img = icon.getImage();
		img = img.getScaledInstance(100, 45, Image.SCALE_SMOOTH);
		icon.setImage(img);//아이콘에 변경된 이미지를 다시젹용
		bt.setPreferredSize(new Dimension(100,45));
		setLayout(new FlowLayout());
		add(bt);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new IconApp();
	}

}
