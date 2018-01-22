package template;

public class Client {
	public static void main(String[] args) {
		Account account; 
		//读取配置文件
		account = (Account)XMLUtil.getBean(); //读取配置文件并反射生成具体折扣对象
		account.handle("刘亦菲", "123456");  
	}
}
