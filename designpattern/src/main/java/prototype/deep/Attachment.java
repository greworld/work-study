package prototype.deep;

import java.io.Serializable;

/**
 * 工作周报中的附件
 * @author 张贵东
 *
 */
public class Attachment implements Serializable{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void download(){
		System.out.println("下载附件，文件名为" + name);
	}
}
