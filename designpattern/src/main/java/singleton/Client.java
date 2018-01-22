package singleton;

public class Client {
	public static void main(String[] args) {
		//获取对象实例
		TaskManager instance1 = TaskManager.getInstance();
		TaskManager instance2 = TaskManager.getInstance();
		//判断是否相等
		System.out.println(instance1==instance2);//true
		
		instance1.init();
		instance1.displayProcesses();
		instance1.displayServeces();
		instance1.closeWindow();
		
	}
}
