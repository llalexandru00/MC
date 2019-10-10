package game;


import util.IOManager;
import util.InternalException;

public class Game
{

    private Configuration config;
    private IOManager io;


    public Game(Configuration config, IOManager io)
    {
        if (config == null)
            throw new InternalException("Configuration is null, can't start game");
        this.config = config;
        this.io = io;
    }

    public void start()
    {
        System.out.println("start");
    }
}
