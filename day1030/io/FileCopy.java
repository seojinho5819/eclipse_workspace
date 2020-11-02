package day1030.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	//String ori ="C://Users//SeoJinHo//eclipse-workspace1//SeProject//res//data//memo.txt";//원본의 위치
	
	String ori = "C:/Users/SeoJinHo/eclipse-workspace1/SeProject/res/travel2/aa.jpg";
	String dest = "C://Users//SeoJinHo//eclipse-workspace1//SeProject//res//data//photo_copy.jpg";//복사될 경로의 위치
	
	
	FileInputStream fis;//파일을 대상으로 한 출력스트림
	FileOutputStream fos; //파일을 대상으로 한 출력스트림
	
	public FileCopy() {
		//아래의 코드는 문법상 문제는 없다, 단 실행시 파일이 없을 경우 에러가 나면서 프로그램이 비정상 종료 될수 있는
		//우려가 있다.. 따라서,sun에서는 이러한 우려에 대한 처리를 예외처리로 강제하고있다
		try {
			fis = new FileInputStream(ori);//스트림생성!
			System.out.println("스트림 생성 성공!");
			fos = new FileOutputStream(dest); //파일 출력 스트림은 , 저정한 경로로 비어있는(empty) 파일을 생성해줌
			
			//스트림 생성이 성공 되었으므로 데이터를 한바이트씩 읽어서 다른 비어있는 파일에 출력해보자!
			int data;
			while(true) {
				data = fis.read();//1byte읽기
				if(data==-1)break;
				fos.write(data);//1byte쓰기
				
			
			}
			System.out.println("복사처리를 완료하였습니다");
			
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null) {
				try {
					fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new FileCopy();
	}
}
