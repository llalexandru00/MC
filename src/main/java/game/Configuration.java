package game;

/**
 * A huge container to encapsulate the input and the strategy
 */
public class Configuration
{
    /** The input stored */
    private Input input;

    /** The strategy stored */
    private Strategy strategy;

    /**
     * Basic setter for the input
     * @param input
     * The input of the configuration
     */
    public void setInput(Input input)
    {
        this.input = input;
    }

    /**
     * Basic setter for the strategy
     * @param strategy
     * The strategy of the configuration
     */
    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }

    /**
     * Basic getter for the strategy
     * @return
     * The strategy of the configuration
     */
    Strategy getStrategy()
    {
        return strategy;
    }

    /**
     * Basic getter for the strategy
     * @return
     * The strategy of the configuration
     */
    Input getInput()
    {
        return input;
    }
}
