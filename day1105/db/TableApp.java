/*
 * 스윙컴포넌트중 이차원 구조의 데이터를 표현하기에 최적화된 JTable을 사용해본다
 * */

package day1105.db;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableApp extends JFrame{
	JTable table;
	String[] column= {"번호","이름","연락처","주소","성별"};
	String[][]data= {
			{"1","batman","011","고담","남"},
			{"2","super","070","크립톤","남"},
			{"3","wonder","074","아마존","여"}
	
	};
	JScrollPane scroll;
	
	public TableApp() {
		
		table = new JTable(data,column); //row3 col5
		scroll = new JScrollPane(table);
		
		
		setLayout(new FlowLayout());
		add(scroll);
		
		//마우스이벤트 구현
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("나눌렀어?");
				int col = table.getSelectedColumn();//유저가 선택한 index(호수)
				int row = table.getSelectedRow();//유저가 선택한 index(층수)
				System.out.println("좌표("+row+","+col+")");
				String value = (String)table.getValueAt(row, col);
				System.out.println(value);
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		setVisible(true);
		setSize(500,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		new TableApp();
	}
}
