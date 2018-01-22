package factory;
/**
 * 果冻
 * @author 张贵东
 *
 */
public class Jelly implements Food {

	public Food produce() {
		System.out.println("产生果冻。。。");
		return new Jelly();
	}

}
