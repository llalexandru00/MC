import game.Configuration;
import game.Input;
import game.State;
import util.Console;
import game.Game;
import util.IOManager;

import java.util.ArrayList;

public class MCDriver
{

    private static IOManager io = new Console();
    private static Configuration config = new Configuration();
    private static final String rules = "Rules\n\nThis game is about some missionaries and some cannibals\n"+
            "which lay on the side of one river. There is also a boat with a defined dimension.\n" +
            "Multiple entities can join the boat as long as they don't exceed the size of it.\n"+
            "In order to go to the other side of the river the boat should have at least one passenger.\n" +
            "The goal is to move all the entities from one side to the other using the boat, while \n" +
            "trying to avoid having more cannibals than missionaries somewhere in the same time:  \n" +
            "on a side of the river or on the boat.\n";

    public static void main(String[] args)
    {
        io.write(rules);
        Input params = io.readInput();
        config.setInput(params);
        config.setStrategy(io.readStrategy());
        Game game = new Game(config);
        ArrayList<State> solution = game.solve();
        io.writeSolution(solution);
    }

}
