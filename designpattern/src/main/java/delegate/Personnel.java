package delegate;

public class Personnel implements Excutor{
	private String name;
	private MeetingRoom meetingRoom;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void planMeetingHouse(String requirement) {
		System.out.println("Leader,您的会议室要求是："+requirement);
		System.out.println("开始寻找会议室。。。");
		findAdder();
		System.out.println("找到会议室。。。"+meetingRoom.getName());
	}
	
	public void findAdder(){
		meetingRoom = new MeetingRoom();
		meetingRoom.setName("203会议室");
	}
}
