public class Timer
{
    long time;
    long stopwatch;

    public void start() throws InterruptedException
    {
        if (stopwatch != 0)
            throw (new InterruptedException(("Timer Already Started")));

        stopwatch = System.nanoTime();
    }

    public long stop()
    {
        time = System.nanoTime() - stopwatch;
        return time;
    }

    public void reset()
    {
        this.time = 0;
        this.stopwatch = 0;
    }
}
