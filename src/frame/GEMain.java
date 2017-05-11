package frame;

public class GEMain {	// class 는  object를 만들 때 사용. object는 실행될때 메모리에 할당 됨
	private static GEFrame frame;
	public static void main(String[] args) {
		frame = new GEFrame();				// 함수가 호출 될 때, 메모리가 할당 됨. 호출이 끝나면 없어짐. (로컬 변수) Instance variable, member variable
		frame.init();
	}	
}