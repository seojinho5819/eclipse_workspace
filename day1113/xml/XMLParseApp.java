package day1113.xml;
/*
 * java로 xml을 파싱하는 방법 2가지
 * 
 * 1) DOM방식 - html과 같은원리
 *             즉, 모든태그마다 1:1대응하는 DOM객체를 메모리에 생성해놓고.
 *             필요한 객체를 접근하는 방식 //무겁다.. pc 쪾에서는 괜찮은데 핸드폰이런데서는 조금무리
 *             무거운 DOM객체 메모리 많이잡아먹음
 * 
 * 2) SAX방식      xml 문서를이루는 엘리먼트, 텍스트 등의 모든 노드에 대한 이벤트를 발생시켜주는 방식
 *             따라서 개발자는 적절한 자바의 객체를 메모리에 올려, xml을 대신하여 데이터를 사용하면 된다.
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLParseApp {
   //실행중인 자바프로세스가 파일에 접근하기 위해서는 파일입력스트림 계열이 필요하다.
   FileInputStream fis;
   InputStreamReader reader;
   BufferedReader buffr;
   URL url; //파일이 있는경로
   URI uri;
   File file;
   
   public XMLParseApp() {
      url = this.getClass().getClassLoader().getResource("res/pets.xml");
      try {
         uri = url.toURI();
         file = new File(uri);
         parseData();//파싱시작! 
        
    
      } catch (URISyntaxException e) {
         e.printStackTrace();
      }
   }
   public void  readTest() {
	 
       try {
		fis = new FileInputStream(file = new File(uri));
		   //육안으로 확인시 한글이 깨질 수 있으므로, Reader로 업그레이드 하겠다.
		   reader = new InputStreamReader(fis);
		   //한문자씩 읽어들이면 너무 시간이 오래걸리므로, 한줄씩 읽어들일 수 있는 버퍼처리된 스트림으로 업그레이드하자
		   buffr = new BufferedReader(reader);
		   
		   //XML 파싱은 추후에 하고, 우리가 정의한 xml을 제대로 스트림으로 읽어들일 수 있는지 체크해본다.
		   String data = null;
		   while(true){
		      data = buffr.readLine();
		      if(data == null)break;
		      System.out.println(data);
		   }
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
   }
   
   //xml 파싱해보자!
   public void parseData() {
      //SAX방식의 파서는 SAXParserFactory라는 객체로부터 얻는다.
      SAXParserFactory factory; //Factory로부터 parser의 인스턴스를 얻을 수 있다.
      factory = SAXParserFactory.newInstance(); //static 메서드를 이용하여 인스턴스 얻는다.
      try {
         SAXParser saxParser = factory.newSAXParser(); //fatctory로부터 parser의 인스턴스를 얻는다.
         saxParser.parse(file, new MyHandler());
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public static void main(String[] args) {
      new XMLParseApp();
   }

}
