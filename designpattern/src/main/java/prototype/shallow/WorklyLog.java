package prototype.shallow;

import java.util.Date;

/**
 * 工作周报原型对象：
 * 浅克隆演示
 * @author 张贵东
 *
 */
public class WorklyLog implements Cloneable {
	private Attachment attachment;//不是值类型
	private String name;
	private Date date;//不是值类型
	private String content;
	public Attachment getAttachment() {
		return this.attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	 //使用clone()方法实现浅克隆,克隆的附件是同一份引用
	public WorklyLog clone(){
		Object object = null;
		try {
			object = super.clone();
			return (WorklyLog) object;
		} catch (Exception e) {
			System.out.println("不支持复制！");
            return null;
		}
	}
	
}
