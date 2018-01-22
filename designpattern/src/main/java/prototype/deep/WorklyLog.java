package prototype.deep;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 工作周报原型对象：
 * 浅克隆演示
 * @author 张贵东
 *
 */
public class WorklyLog implements Serializable {
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
		ByteArrayOutputStream bao = null;
		ObjectOutputStream oos = null;
		ByteArrayInputStream bis= null;
        ObjectInputStream ois= null;
		try {
			 //将对象写入流中:序列化
			bao = new ByteArrayOutputStream();;
			oos = new ObjectOutputStream(bao);
			oos.writeObject(this);
			
			 //将对象从流中取出：反序列化
			bis = new  ByteArrayInputStream(bao.toByteArray());
			ois=new  ObjectInputStream(bis);
			return (WorklyLog) ois.readObject();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				ois.close();
				bis.close();
				oos.close();
				bao.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
