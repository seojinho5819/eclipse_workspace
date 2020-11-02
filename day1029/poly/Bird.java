package day1029.poly;

public class Bird {
	String name="난새";
	String local="한국";
	
	public void fly() {
		System.out.println("새가 날아요");
	}
	public static void main(String[] args) {
		//새들을 대상으로 다형성 공부하기!!!
		Bird b1 =new Bird();
		Bird b2 =new Duck();
		Bird b3 =new Sparrow();
	}
}
