package delegate;

public class Leader implements Excutor{
	private Excutor excutor;
	private String requirement;//会议室要求 
	//将受托人对象注入
	public Leader(Excutor excutor) {
		this.excutor = excutor;
	}
	
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public void planMeetingHouse(String requirement) {
		excutor.planMeetingHouse(requirement);//让受托人的方法执行任务
	}

}
