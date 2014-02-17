package me.radik.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StoredFrequencyCounterTest extends FrequencyCounterTest{
    public StoredFrequencyCounterTest(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/frequency", "radik", "");
            _counter = new StoredFrequencyCounter(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
