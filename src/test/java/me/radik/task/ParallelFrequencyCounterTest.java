package me.radik.task;

/**
 * Created by radik on 17/02/14.
 */
public class ParallelFrequencyCounterTest extends FrequencyCounterTest {
    public ParallelFrequencyCounterTest() {
        _counter = new ParallelFrequencyCounter<String>();
    }
}
