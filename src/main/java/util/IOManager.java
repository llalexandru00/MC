package util;

import game.Input;
import game.Strategy;

public interface IOManager
{
    Input readInput();
    Strategy readStrategy();
    void writeErr(String message);
}
