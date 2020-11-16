package day1110.network.unicasting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UniServer extends JFrame{
	JTextField t_port; 
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port=7777;
	
	Thread thread; //메인쓰레드 대신, 접속자를 감지하게될 쓰레드!! (accept() 메서드 때문에..)
	
	
	public UniServer() {
		t_port = new JTextField(Integer.toString(port),10);
		bt = new JButton("서버가동");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		//조립
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//가동버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() {
					public void run() {
						startServer();
					}
				};
				thread.start();//Runnable 로 진입시키자!!!
			}
		});
		
		setVisible(true);
		setBounds(600,200,300,400); //x,y,width,height 순서 
		
	}
	
	//서버 가동
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText())); //서버소켓 생성 
			area.append("서버 준비\n");
			
			//서버는 여러명의 접속자를 감지해야 한다면 각 접속자마다 비동기적으로 즉 독립적으로 서로 상관없이
			//대화를 주고 받는 주체는 쓰레드의 인스턴스로 처리하자!!
			while(true) {
			
			//서버 가동하기 
			//자바는 쓰레드기반이므로, 지금까지 메인실행부라 불렸던 실행주체도 사실은 시스템에 의해 생성된 쓰레드였다..
			//하지만, 메인쓰레드는 개발자가 생성하는 일반쓰레드와는 하는 역할에 차이가 있다. 
			//메인쓰레드는 프로그램을 운영해주는 역할 특히 그래픽처리, 이벤트처리까지 담당하므로, 절대로 아래의 업무금지
			//1) 무한루프에 빠뜨리지 말것
			//2) 대기상태에 빠지게 하지 말것!! (accept(), 스트림의 read()...등등 )
			//    참고로 안드로이드에서는 메인쓰레드의 1,2번 시도자체를 에러로 본다!!
			//결론) 별도의 쓰레드를 생성하여 처리하자!! 
			Socket socket = server.accept();//접속자 감지와 동시에 대화용 소켓 반환!!
			area.append("접속자 발견\n");
			
			//대화용 쓰레드 생성후 소켓에 넘겨주자
			MessageThread messageThread = new MessageThread(this, socket);
			messageThread.start();//Runnable로 진입!
			
			
			
			
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new UniServer();
	}

}
