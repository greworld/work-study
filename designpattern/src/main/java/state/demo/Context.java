package state.demo;

/**
 * @author 张贵东
 * @version 1.0
 * @Description: TODO
 * @date 2017/10/30 8:34
 * @since JDK 1.8
 */
public class Context {
    private Color state = null;

    public void push() {
        //如果当前red状态，切换到blue
        if (state == Color.red) {
            state = Color.blue;
        }
        //如果当前blue状态，切换到green
        else if (state == Color.blue) {
            state = Color.green;
        }
        //如果当前black状态，切换到red
        else if (state == Color.black) {
            state = Color.red;
        }
        //如果当前green状态，切换到black
        else if (state == Color.green) {
            state = Color.black;
        }
        Sample sample = new Sample(state);
        sample.operate();
    }


    public void pull() {
        //与push状态切换正好相反
        if (state == Color.green) {
            state = Color.blue;
        } else if (state == Color.black) {
            state = Color.green;
        } else if (state == Color.blue) {
            state = Color.red;
        } else if (state == Color.red) {
            state = Color.black;
        }
        Sample2 sample2 = new Sample2(state);
        sample2.operate();
    }
}
