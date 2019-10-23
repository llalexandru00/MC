package util;

import game.Input;
import game.State;
import game.Strategy;
import game.StrategyFactory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the IO operations related to the console
 */
public class Console implements IOManager
{
    /**
     * The main driver used for IO handling
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Utility for reading one single integer. It optionally prompts a message beforehand and it validates the input.
     * @param message
     * The message to be displayed before the integer is given as input.
     * @return
     * Returns the given integer.
     */
    private int readInt(String message)
    {
        if (message != null)
        {
            System.out.println(message);
        }

        try
        {
            return scanner.nextInt();
        }
        catch (InputMismatchException e)
        {
            throw new ExternalException("The given expression is not an integer.");
        }
    }


    /**
     * Utility for reading one single string. It also prompts a message beforehand
     * @param message
     * The message to be displayed before the string is given as input.
     * @return
     * Returns the given integer.
     */
    private String readString(String message)
    {
        if (message != null)
        {
            System.out.println(message);
        }
        return scanner.next();
    }

    /**
     * Standard enumeration of input requirements which loops until correct input is given.
     * @return
     * A container which has all the information provided by the user.
     */
    @Override
    public Input readInput()
    {
        do {
            try
            {
                int missionaries = readInt("Insert the number of missionaries: ");
                int cannibals = readInt("Insert the number of cannibals: ");
                int boatDimension = readInt("Insert the dimension of the boat: ");
                return new Input(missionaries, cannibals, boatDimension);
            }
            catch (ExternalException e)
            {
                writeErr(e.getMessage());
                readString(null);
            }
        } while (true);
    }

    /**
     * Standard reading of the strategy.
     * @return
     * A strategy to be used in the game.
     */
    public Strategy readStrategy()
    {
        do {
            String strategy = readString("Insert the strategy (RANDOM/BKT/IDDFS/ASTAR): ");
            try
            {
                return StrategyFactory.create(strategy);
            }
            catch (ExternalException e)
            {
                writeErr(e.getMessage());
            }
        } while (true);
    }

    /**
     * The write primitive
     * @param message
     * A message to be written to the console
     */
    @Override
    public void write(String message)
    {
        System.out.println(message);
    }

    /**
     * The write error primitive
     * @param message
     * A message to be written to the console marked as error message
     */
    @Override
    public void writeErr(String message)
    {
        System.err.println("[ERROR] " + message);
    }

    /**
     * Utility used for writing the solution in a proper format
     * @param solution
     * The solution to be written to the console
     */
    @Override
    public void writeSolution(List<State> solution) {
        if (solution == null)
        {
            write("No solution could be found.");
            return;
        }
        write("The solution has " + (solution.size()-1) + " transitions.");
        for (State state : solution)
        {
            System.out.println(state);
        }
    }
}
