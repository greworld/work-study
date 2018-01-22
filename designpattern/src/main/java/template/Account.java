package template;

public abstract class Account {
	//登录系统
	public boolean login(String account,String password){
		System.out.println("账号："+account);
		System.out.println("密码："+password);
		//模拟登录
		if("刘亦菲".equals(account)&&"123456".equals(password)){
			return true;
		}else{
			return false;
		}
	}
	//计算利息
	public abstract void calculateInterest();
	
	//显示利息
	public void display(){
		System.out.println("显示利息");
	}
	
	//登录错误
	public void handle(String account,String password){
		if(!login(account,password)){
			System.out.println("账号和密码错误");
			return;
		}
		calculateInterest();
		display();
	}
}
