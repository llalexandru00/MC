import game.Configuration;
import util.Console;
import game.Game;
import util.IOManager;

public class MCDriver
{

    private static IOManager io = new Console();
    private static Configuration config = new Configuration();

    public static void main(String[] args)
    {
        // TODO: write rules
        config.setInput(io.readInput());
        config.setStrategy(io.readStrategy());
        Game game = new Game(config, io);
        game.start();
    }

}
