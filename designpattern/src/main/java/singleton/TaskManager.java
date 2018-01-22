package singleton;

public class TaskManager {
	private static class SingletonHolder{
		private static final TaskManager INSTANCE = new TaskManager();
	} 
	//私有化构造方法
	private TaskManager() {
		
	}
	//提供获取对象实例的方法
	public static final TaskManager getInstance(){
		return SingletonHolder.INSTANCE;
	}
	
	//初始化窗口
	public void init(){
		System.out.println("初始化窗口。。。");
	}
	
	//显示进程
	public void displayProcesses(){
		System.out.println("显示进程。。。");
	}
	
	//显示服务
	public void displayServeces(){
		System.out.println("显示服务。。。");
	}
	
	//关闭窗口
	public void closeWindow(){
		System.out.println("关闭窗口。。。");
	}
}
