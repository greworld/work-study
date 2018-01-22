package proxy.dynimicproxy.cglib;

public class TestCglib {
	public static void main(String[] args) {
		JhonCglib cglib = new JhonCglib();
		Jhon jhon = (Jhon) cglib.getInstance(new Jhon());
		jhon.shopping("小米NOT3");;
	}

}
