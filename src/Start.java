import ai.AIMain;
import game.Main;

/**
 * Created by mccloskeybr on 4/1/16.
 */
public class Start {

    public static void main(String[] args) {

        Main main = new Main();
        AIMain ai = new AIMain();

        //noinspection InfiniteLoopStatement
        while (true) {
            main.update();
            ai.update();
        }

    }

}
