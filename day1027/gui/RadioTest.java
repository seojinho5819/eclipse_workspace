package day1027.gui;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;

public class RadioTest extends Frame{
	CheckboxGroup  group = new CheckboxGroup();
	
	public RadioTest() {
		group = new CheckboxGroup();
		setLayout(new FlowLayout());
		
		this.add(new Checkbox("운동",group,false));
		this.add(new Checkbox("컴퓨터",group,false));
		this.add(new Checkbox("영화",group,false));
		this.add(new Checkbox("독서",group,false));
		this.add(new Checkbox("산책",group,false));
		this.add(new Checkbox("애견돌보기",group,true));
		setSize(300,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		System.out.println("겁나빠름");
		new RadioTest();
	}
}
