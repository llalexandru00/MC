package util;

import game.Input;
import game.State;
import game.Strategy;
import java.util.List;

/**
 * Standard interface for IO operations
 */
public interface IOManager
{
    /** Used for reading the container for the information required */
    Input readInput();

    /** Used for reading the strategy used for the game */
    Strategy readStrategy();

    /** Write primitive */
    void write(String message);

    /** Write raw string without new line */
     void writeRaw(String message);

    /** Write error primitive */
    void writeErr(String message);

    /** Macro for formatting one list of states */
    void writeSolution(List<State> solution);
}
