package prototype.shallow;

import java.util.Date;

public class Client {
	public static void main(String[] args) {
		WorklyLog  log_previous, log_new;
		//创建原型对象
		log_previous = new WorklyLog();
		Attachment  attachment = new Attachment(); //创建附件对象
		attachment.setName("附件1");
        log_previous.setAttachment(attachment);  //将附件添加到周报中
        log_previous.setName("第13周工作周报");
        log_previous.setDate(new Date());
        log_previous.setContent("周一：汇报  周二：见客户。。。");
        log_new  = log_previous.clone(); //调用克隆方法创建克隆对象
        log_new.setName("第14周工作周报");
        log_new.getAttachment().setName("附件2");
        //比较周报
        System.out.println("周报是否相同？ " + (log_previous ==  log_new));
        //比较附件：
        System.out.println("附件是否相同？ " +  (log_previous.getAttachment() == log_new.getAttachment()));
        
        //输出周报：
        System.out.println("--------log_previous---------");
        System.out.println("周报名称："+log_previous.getName());
        System.out.println("周报日期"+log_previous.getDate());
        System.out.println("周报内容"+log_previous.getContent());
        System.out.println("周报附件"+log_previous.getAttachment().getName());
        
        System.out.println("-------log_new----------");
        System.out.println("周报名称："+log_new.getName());
        System.out.println("周报日期"+log_new.getDate());
        System.out.println("周报内容"+log_new.getContent());
        System.out.println("周报附件"+log_new.getAttachment().getName());
	}
}
