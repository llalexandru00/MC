package statistic;

import game.Configuration;
import game.Game;
import game.State;
import statistic.Result;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class GameThread implements Callable<Result>
{
    private Configuration config;
    private ArrayList<State> solution;

    public GameThread(Configuration config)
    {
        this.config = config;
    }

    @Override
    public Result call()
    {
        long startTime = System.nanoTime();
        solution = new Game(config).solve();
        long finishTime = System.nanoTime();
        return new Result(solution, config, (finishTime-startTime)/1000);
    }
}
