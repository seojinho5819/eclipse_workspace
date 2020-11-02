package day1030.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.midi.Soundbank;



/*
  Stream 이란 현실에서는 흐르는 물줄기를 의미
  		전산에서는 흐름의 대상이 물이 아닌 데이터 이다!!
  		but전산에서는 흐름의 방향에 따라 다음과 같이 분류(기준은 실행중인 프로그램)
  		
  		1)입력(Input): 실행중인 프로그램으로 데이터가 흘러들어가는 것
  		2)출력(Output): 실행중인 프로그램에서 데이터가 흘러나오는것
  		자바에서는 입출력과 관련된 패키지명이 java.io이다 여기에는 입출력 처리를 위한 수많은 api지원
 * */
public class FileReadApp{
		//파일을 대상으로 데이터를 읽어들이는 FileInputStream을 학습해본다!
	FileInputStream fis;

	public FileReadApp(){
		File file = new File("C:\\Users\\SeoJinHo\\eclipse-workspace1\\SeProject\\res\\data\\memo.txt");

	//로컬 상의 파일을 대상으로 스트림(관)을 생성하자!
		try{//이 영역은 에러가 발생할 가능성이 있는 코드임을 명시..
			fis = new FileInputStream(file);
			System.out.println("스트림생성 성공입니다");
			
			int data;
			
			while(true) {
				data=fis.read();
				if(data==-1)break;
				System.out.print((char)data);
				
			}
			
		}catch(FileNotFoundException e){//혹여나 우려했던 에러가 발생한다면, 비정상 종료하지말고, 실행부는 이 catch문 블럭을 수행해라!
			System.out.println("지정한 파일을 찾을 수 없습니다");
			e.printStackTrace();
		}catch(IOException e) {
			System.out.println("파일을 읽을수 없습니다..");
			e.printStackTrace();
		}finally {
		//이 영역은 try문을 수행하던 catch문을 수행하던 모조건 거쳐야 할 영역이무로,
			//이 영역에 자원을 닫는 코드를 작성해야 한다!
			//주로 db연걸 끊기,스트림연결끊기
			if(fis != null) {
				//db,stream닫을때 반드시 null여부를 따져보는 습관을 갖자!
				try {
					fis.close();//스트림닫음
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void main (String [] args){
		new FileReadApp();
	}
}
