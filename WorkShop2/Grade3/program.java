import view.Console;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by zack on 2017-10-25.
 */
public class program {


    public static void main(String[] args) throws IOException, ParseException {
        Console start = new Console();
        System.out.println("* Welcome to the jolly pirate *");
        start.showAuthentication();
    }
}