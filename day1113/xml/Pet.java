/*
 * 한마리의 반려동물을 담게 될 VO 클래스
 * VO란? Value Objsect의 약자로거, 로직 작성이 목적이 아닌 단지 데이터만을 보관할 용도로 사용되는
 * 객체를 가리키는 어플리케이션 설계용어중 하나
 * 
 */
package day1113.xml;

public class Pet {
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	private String name;
	private int age;
	private String gender;
	
}
