import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tang on 2017/5/20.
 */
public class Door {
    private Greeter greeter;

    public void knock() {
        System.out.println(greeter.satSomething());
    }

    public void setGreeter(String greeter) {
    }
}
