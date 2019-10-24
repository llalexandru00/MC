import game.*;
import statistic.GameThread;
import statistic.Result;
import statistic.StatisticViewer;
import util.Console;
import util.ExternalException;
import util.IOManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class StatisticDriver
{

    private static final int BATCH_SIZE = 10;
    private static final int MAX_ITERATIONS = 1000;
    private static final int MAX_MIS = 15;
    private static final int MIN_MIS = 3;
    private static final int MAX_CAN = 15;
    private static final int MIN_CAN = 3;
    private static final int MAX_BOAT = 5;
    private static final int MIN_BOAT = 2;
    private static final int MAX_THREAD_POOL = 200;
    private static final int THREAD_TIMEOUT = 2;

    private static final IOManager io = new Console();

    private static ArrayList<Input> generateInputs()
    {
        ArrayList<Input> inputs = new ArrayList<>();
        Random rand = new Random();
        int iterations = 0;
        while (inputs.size() < BATCH_SIZE && iterations < MAX_ITERATIONS)
        {
            int missioaries = rand.nextInt(MAX_MIS - MIN_MIS) + MIN_MIS;
            int cannibals = rand.nextInt(MAX_CAN - MIN_CAN) + MIN_CAN;
            int boatDimension = rand.nextInt(MAX_BOAT - MIN_BOAT) + MIN_BOAT;
            try {
                Input input = new Input(missioaries, cannibals, boatDimension);
                inputs.add(input);
            } catch (ExternalException ignored){}
            iterations++;
        }

        if (iterations == MAX_ITERATIONS)
        {
            io.writeErr("The batch size couldn't be respected due to an overrun of the iteration limit.");
        }

        return inputs;
    }

    public static void main(String[] args)
    {
        ArrayList<Input> inputs = generateInputs();
        ArrayList<GameThread> games = new ArrayList<>();

        for (Input input : inputs)
        {
            ArrayList<Strategy> strategies = StrategyFactory.createAll();
            for (Strategy strategy : strategies)
            {
                Configuration config = new Configuration(input, strategy);
                GameThread game = new GameThread(config);
                games.add(game);
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(
                Math.min(inputs.size() * StrategyFactory.createAll().size(), MAX_THREAD_POOL));

        List<Future<Result>> results;
        try {
            results = executor.invokeAll(games, THREAD_TIMEOUT, TimeUnit.SECONDS);

            // wait twice as much as needed in order to be sure all threads are terminated or were timeout
            executor.awaitTermination(THREAD_TIMEOUT * 2, TimeUnit.SECONDS);

            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        StatisticViewer viewer = new StatisticViewer(inputs, StrategyFactory.createAll());

        for (int i=0; i<results.size(); i++)
        {
            Future<Result> future = results.get(i);
            try {
                Result result;
                if (future.isCancelled() || !future.isDone())
                {
                    result = new Result(null ,
                            new Configuration(inputs.get(i / 4), StrategyFactory.createAll().get(i % 4)),
                            0);
                    result.timedOut();
                }
                else
                {
                    result = future.get();
                }

                viewer.add(result);
            }
            catch (InterruptedException | ExecutionException | CancellationException e) {
                e.printStackTrace();
                return;
            }
        }

        viewer.display(io);

    }

}
