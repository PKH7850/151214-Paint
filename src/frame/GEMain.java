package frame;

public class GEMain {	// class ��  object�� ���� �� ���. object�� ����ɶ� �޸𸮿� �Ҵ� ��
	private static GEFrame frame;
	public static void main(String[] args) {
		frame = new GEFrame();				// �Լ��� ȣ�� �� ��, �޸𸮰� �Ҵ� ��. ȣ���� ������ ������. (���� ����) Instance variable, member variable
		frame.init();
	}	
}