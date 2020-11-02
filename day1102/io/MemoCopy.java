
/*지난주에는 문서파일(영문)과 바이너리파일(사진) 등을 복사해보았다.
 * 하지만 문서파일의 경우 한글이 섞여있을때 어떤 결과가 나오는지 테스트 해본다
 * 
 * [스트림의 유형]
 * 스트림의 기본은 1byte씩 처리하는 바이트 기반의 스트림이다
 * 하지만 , 스트림상으로 흐르는 데이터를 문자로 해석할 수 있는 스트림을 문자기반 스트림이라
 * 한다. 문자기반 스트림은 접미어가 ~Reader,~Writer로 끝난다..
 * */

package day1102.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoCopy {
	
	FileInputStream fis;
	FileOutputStream fos;
	
	FileReader reader; //파일을 대상으로 한 문자 기반의 입력 스트림
	FileWriter writer; //파일을 대상으로 한 문자 기반의 출력 스트림
	
	String path ="C:\\Users\\SeoJinHo\\eclipse-workspace1\\SeProject\\res\\data\\test.txt";
	String path2 ="C:\\Users\\SeoJinHo\\eclipse-workspace1\\SeProject\\res\\data\\test2.txt";
	
	public MemoCopy() {
		// TODO Auto-generated constructor stub
		try {
			//fis = new FileInputStream(path);
			//fos = new FileOutputStream(path2);
			
			reader = new FileReader(path);
			writer = new FileWriter(path);
			
			int data;
				while(true) {
				data = fis.read();
				if(data==-1)break;
				System.out.println((char)data);
				fos.write(data);
				}
				
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(fis!=null) {
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
		new MemoCopy();
	}

}
