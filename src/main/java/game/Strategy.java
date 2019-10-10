package game;

import util.ExternalException;

public class Strategy
{

    private StrategyType type;

    enum StrategyType
    {
        RANDOM, BKT, IDDFS
    }

    public Strategy(String strategy)
    {
        type = convertToStrategyType(strategy);
    }

    private StrategyType convertToStrategyType(String strategy)
    {
        for (StrategyType type : StrategyType.values())
            if (type.name().equals(strategy))
                return type;
        throw new ExternalException("The strategy provided is invalid");
    }


}