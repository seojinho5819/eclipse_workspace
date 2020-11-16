
package day1110.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	int port=8989; //클라이언트를 받아들일 포트번호 이 포트번호에 의해 다른 네트워크 프로세스와
			//구분될 수 있다...예) 오라클 1521 , mysql 3306,
	ServerSocket server; //대화용 소켓이 아니라 접속자들 감시하고 접속자가 발견되면 대화용 소켓을 반환
						//해주는 객체(마치 전화기의 벨이 울리면 그 이후부터 전화를 받고 대화하는것과 비슷)
						//전화받는용 소켓
	public EchoServer() {
		try {
			server = new ServerSocket(port);
			Socket socket = server.accept(); //서버가동 및 클라이언트 접속 기다림..(접속이 들어올떄까지 무한대기 한다 지연 발생)
			System.out.println("접속자 발견");
			//개발자는 반환받은 소켓으로부터 통신에 필요한 입출력 스트림을 얻을 수 있다!!
			//이떄 개발자는 네트웤 하부에 대한 아무런 지식없이 그냥 스트림처리만 하면 알아서 원격지의 대화상대와
			//통신이 가능하며 이 모든것들은 소켓이 알아서 해주는 거다..
			InputStream is =  socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);//한줄씩 읽어들임
			
			String data=null;
			while(true) {
			data=buffr.readLine(); //1byte읽기 (소켓과 연결된 스트림으로부터)
			System.out.print(data);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		new EchoServer();
	}
}
