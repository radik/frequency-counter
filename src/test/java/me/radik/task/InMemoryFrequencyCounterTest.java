package me.radik.task;

public class InMemoryFrequencyCounterTest extends FrequencyCounterTest {

    public InMemoryFrequencyCounterTest(){
        _counter = new InMemoryFrequencyCounter<String>();
    }

}
