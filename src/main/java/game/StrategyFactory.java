package game;

import util.ExternalException;

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
            default:
                throw new ExternalException("Strategy is invalid");
        }
    }

}
