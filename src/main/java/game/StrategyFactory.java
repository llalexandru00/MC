package game;

import util.ExternalException;

import java.util.ArrayList;

/**
 * Basic factory used for generating strategies
 */
public class StrategyFactory
{

    /**
     * Main function of the strategy factory used to generate strategies based on the given string
     * @param strategy
     * The name of the strategy to be generated
     * @return
     * The strategy with the specified name
     */
    public static Strategy create(String strategy)
    {
        switch (strategy)
        {
            case "RANDOM":
                return new RandomStrategy();
            case "BKT":
                return new BktStrategy();
            case "IDDFS":
                return new IddfsStrategy();
            case "ASTAR":
                return new AStarStrategy();
            default:
                throw new ExternalException("Strategy is invalid");
        }
    }

    public static ArrayList<Strategy> createAll() {
        ArrayList<Strategy> all = new ArrayList<>();
        all.add(new RandomStrategy());
        all.add(new BktStrategy());
        all.add(new IddfsStrategy());
        all.add(new AStarStrategy());
        return all;
    }
}
