package statistic;

import game.Configuration;
import game.State;

import java.util.ArrayList;

public class Result {

    private ArrayList<State> solution;
    private Configuration config;
    private long runTime;
    private boolean timedOut = false;

    public Result(ArrayList<State> solution, Configuration config, long runTime)
    {
        this.solution = solution;
        this.config = config;
        this.runTime = runTime;
    }

    public void timedOut()
    {
        timedOut = true;
    }

    @Override
    public String toString()
    {
        return (solution == null ? 0 : solution.size()) + " " + runTime;
    }

    Configuration getConfiguration() {
        return config;
    }

    long getRunTime() {
        return runTime;
    }

    long getSolutionSize() {
        if (solution == null)
            return 0;
        return solution.size();
    }

    boolean isTimedOut() {
        return timedOut;
    }
}
