package edu.touro.mco152.bm;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * An interface for classes that are UI implementations for benchmarks
 */
public interface BenchmarkUI
{
    /**
     * Starts executing core work of program
     * @throws Exception if method call fails
     */
    void beginWork();

    /**
     * Renders DiskMarks and posts their process on the UI
     * @param markList A list of DiskMarks to render onto any given UI
     */
    void render(List<DiskMark> markList);

    /**
     * A method that executes the programs completion
     */
    void complete();

    /**
     * Keeps tabs on DiskMarks and when ready, stages them to be rendered. When this method deems necessary, it will call
     * {@code render()}.
     * @param diskMark
     */
    void stageData(DiskMark diskMark);

    /**
     * @return true if the program is cancelled
     */
    boolean isAborted();

    /**
     * Sets internal progression value
     * @param percentComplete int representation of completion percentage of the program
     */
    void setProgression(int percentComplete);

    /**
     * @param isAborted true if the program is aborted, false otherwise
     */
    void abort(boolean isAborted);

    /**
     * @param propertyChangeListener Action to perform when change occurs
     */
    void antePropertyChangeListener(PropertyChangeListener propertyChangeListener);
}
