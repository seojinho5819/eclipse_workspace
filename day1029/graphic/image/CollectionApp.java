package day1029.graphic.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;

public class CollectionApp {
	//List형을 테스트해보자
	public void showList() {
		//최상위 객체인 List는 인터페이스 이며, List로 기본적으로 가져야할 추상메서드가 명시되어 있다..
		//Generic Type으로 선언하면 컬렉션 프레임웍에 넣을수 있는 자료형을 제한할 수있다
		ArrayList<JButton> list = new ArrayList<JButton>(); //리스트 구조들은 배열과 거의 같다!!
		//js의 배열 과 동일한 동작방식
		list.add(new JButton("나버튼"));
		//list.add("사과");
		//list.add("복숭아");
		//list.add("멜론");
		list.add(new JButton("나버튼"));
		System.out.println("데이터 수는"+list.size());//배열은 length지만 컬렉션 프레임웤은 모두 size()이다
		list.get(0);
		for(int i = 0;i<list.size();i++) {
			JButton bt1 = list.get(i);
			System.out.println(bt1.getText());
		}
	}
	public void showSet() {
		HashSet<String> set = new HashSet<String>();
		set.add("banana");
		set.add("banana");
		set.add("banana");
		set.add("banana");
		
		System.out.println("HashSet의 크기는"+set.size());
		//결론: 똑똑하다.. 즉 중복된 데이터는 받아드리지 않음
		HashSet<String> set2 = new HashSet<String>();
		set2.add("바나나");
		set2.add("포도");
		set2.add("블루베리");
		Iterator<String> it = set2.iterator();
		while(it.hasNext()) {
			String e = it.next();
			System.out.println(e);
		}
	}
	public void showMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("k1","장미");
		map.put("k2","튤립");
		map.put("k3","안개꽃");
		map.put("k3","할미꽃");
		System.out.println("map의 길이는"+map.size());
		
		//반복문을 이용해 모든 꽃 출력
		Set<String> keySet = map.keySet();//key만 따로 추출
		//그리고 Set은 Iterator를 지원하므로 key를 일렬로 늘어뜨리자!
		Iterator<String> keyIter = keySet.iterator();
		
		while(keyIter.hasNext()) {
			String key = keyIter.next();
			String value = map.get(key);//맵의 데이터중 해당키와 일치하는 데이터 반환!
			System.out.println(value);
		}
		
	}
	
	public static void main(String[] args) {
		CollectionApp app = new CollectionApp();
		app.showList();
		app.showSet();
		app.showMap();
		
	}
	//리스트는 중복된 데이터를 허용하고 추가한다
}
