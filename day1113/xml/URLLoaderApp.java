/*
 * 
 * 지금까지는 URL상의 자원을 가져올때, 그 대상을 이미지로 하였다!
 * ImageIO.read()등 실습
 * 
 * 지금 이 실습에서는 임지 뿐만아니라, 모든 종류의 자원을 대상으로 연겨하여
 * 스트림으로 데이터를 읽어보는 실습을 진행해본다.
 * 
 */

package day1113.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLLoaderApp {
   //웹상의 모든 자원을 대상으로, 연결 및 데이터를 읽어올 수 있는 객체
   
   URLConnection con; //추상클래스이므로, URL객체로부터 인스턴스를 얻는다.
   HttpURLConnection http; //con 을 최신객체로 업그레이드할거.
   URL url;
   FileOutputStream fos;
   
   public URLLoaderApp() {
      try {
         url = new URL("https://image.shutterstock.com/image-photo/astronaut-spacewalk-deep-space-image-600w-744238948.jpg");
         
         //지정한 원격지의 자원과 연결을 시도해보자.
         con = url.openConnection();
         http = (HttpURLConnection)con;
         //http의 자원을 GET방식으로 자원을 요청하자!!
         http.setRequestMethod("GET");
         
         //연결 객체로부터 스트림을 얻어와서 데이터를 읽어보자.
         InputStream is = http.getInputStream();
         
         //파일로저장하자. (그냥 일반디렉토리 res에 하겠음)
         File file = new File("C:\\Users\\SeoJinHo\\eclipse-workspace1\\SeProject\\res\\copy.jpg");
         fos = new FileOutputStream(file);
         
         //한바이트씩 읽어와서 출력스트림을 이용하여 파일에쓰자
         int data = -1;
         while(true) {
            data = is.read();
            if(data == -1)break;
            fos.write(data);
         }
         System.out.println("인터넷상의 파일을 로컬로 저장완료 하였습니다.");
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if(fos!=null) {
            try {
               fos.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
         
      }
   }
   
   public static void main(String[] args) {
      new URLLoaderApp();
   }
}

