package me.radik.task;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class FrequencyCounterTest {

    protected FrequencyCounter<String> _counter;

    public FrequencyCounterTest() {
        _counter = new SimpleFrequencyCounter<String>();
    }

    @Test
    public void emptyIterator() {
        assertEquals(0, _counter.getMostFrequent(new Iterators.Array<String>(new String[]{})).size());
    }

    @Test
    public void iteratorWith450Words() {
        List<String> words = new ArrayList<String>();
        String[] samples = new String[]{
                "hockey", "sochi", "olympic",
                "russia", "usa", "datsyk",
                "ovechkin", "malkin", "kovalchuk"};

        for (int i = 0; i < samples.length; i++) {
            for (int count = 0; count < (i + 1) * 1000; count++) {
                words.add(samples[i]);
            }
        }

        Collection<String> frequent3words = _counter.getMostFrequent(words.iterator(), 3);
        assertEquals(3, frequent3words.size());
        assertThat(frequent3words, hasItems("ovechkin", "malkin", "kovalchuk"));

    }

}
