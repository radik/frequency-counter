package me.radik.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by radik on 14/02/14.
 */
@RunWith(JUnit4.class)
public class FrequencyCounterTest {


    @Test
    public void emptyIterator() {

    }

    @Test
    public void iteratorWith450Words() {
        List<String> words = new ArrayList<String>();
        String[] samples = new String[]{
                "hockey", "sochi", "olympic",
                "russia", "usa", "datsyk",
                "ovechkin", "malkin", "kovalchuk"};

        for (int i = 0; i < samples.length; i++) {
            for (int count = 0; count < (i + 1) * 10; count++) {
                words.add(samples[i]);
            }
        }

        FrequencyCounter counter = new SimpleFrequencyCounter();
        Collection<String> frequent3words = counter.getMostFrequent(words.iterator(),3);
        assertEquals(3, frequent3words.size());
        assertThat(frequent3words, hasItems("ovechkin", "malkin", "kovalchuk"));

    }
}
