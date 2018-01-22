package delegate;
/**
 * 测试
 * @author 张贵东
 *
 */
public class Client {
	public static void main(String[] args) {
		Personnel personnel = new Personnel();
		personnel.setName("王琳");
		
		Leader leader = new Leader(personnel);
		String requirement = "需要能够容纳15人，有空调，有多媒体";	
		leader.setRequirement(requirement);
		leader.planMeetingHouse(requirement);
	}
}
