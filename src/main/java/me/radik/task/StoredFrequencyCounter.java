package me.radik.task;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 *  This implementation of {@link me.radik.task.FrequencyCounter} counts frequency of elements from iterator.
 *  External SQL-database used for storing counting results.
 *  Tested on sqlite3 and Postgres
 */
public class StoredFrequencyCounter<T extends Serializable> implements FrequencyCounter<T> {

    private final static Logger LOGGER = Logger.getLogger(StoredFrequencyCounter.class.getName());

    private final static String INSERT_QUERY = "INSERT INTO frequency (key, amount) VALUES (?, 1)";
    private final static String UPDATE_QUERY = "UPDATE frequency SET amount=? WHERE key=?";
    private final static String COUNT_QUERY = "SELECT amount FROM frequency WHERE key=?";
    private final static String MOST_FREQUENT_QUERY = "SELECT * FROM frequency ORDER BY amount DESC LIMIT ?";
    private final static String DROP_TABLE = "DROP TABLE IF EXISTS frequency";
    private final static String CREATE_TABLE_QUERY = "CREATE TABLE frequency (key TEXT UNIQUE, amount INTEGER)";

    private Connection _connection;

    /**
     * @param connection The database connection. The frequency counter uses this database to store counting results.
     */
    public StoredFrequencyCounter(Connection connection) {
        _connection = connection;
    }

    @Override
    public Collection<T> getMostFrequent(Iterator<T> iterator) {
        return getMostFrequent(iterator, 10);
    }

    @Override
    public Collection<T> getMostFrequent(Iterator<T> iterator, int count) {
        try {
            prepareDb();
            while (iterator.hasNext()) {
                read(iterator.next());
            }
            return getMostFrequent(count);
        } catch (SQLException ex) {
            LOGGER.severe(String.format("SQLException: %s;\n Error code: %s", ex.getMessage(), ex.getErrorCode()));
        }
        return new ArrayList<T>();
    }

    private void prepareDb() throws SQLException {
        Statement statement = _connection.createStatement();
        statement.addBatch(DROP_TABLE);
        statement.addBatch(CREATE_TABLE_QUERY);
        statement.executeBatch();
    }

    private void read(T t) throws SQLException {
        PreparedStatement countStatement = _connection.prepareStatement(COUNT_QUERY);
        countStatement.setString(1, serialize(t));
        ResultSet countResultSet = countStatement.executeQuery();

        if (countResultSet.next()) {
            PreparedStatement updateCount = _connection.prepareStatement(UPDATE_QUERY);
            int amount = countResultSet.getInt("amount");
            updateCount.setString(2, serialize(t));
            updateCount.setInt(1, amount + 1);
            updateCount.execute();
        } else {
            PreparedStatement insertStatement = _connection.prepareStatement(INSERT_QUERY);
            insertStatement.setString(1, serialize(t));
            insertStatement.execute();
        }
    }

    private Collection<T> getMostFrequent(int count) throws SQLException {
        List<T> result = new ArrayList<T>();
        PreparedStatement mostFrequent = _connection.prepareStatement(MOST_FREQUENT_QUERY);
        mostFrequent.setInt(1, count);
        ResultSet rs = mostFrequent.executeQuery();
        while (rs.next()) {
            T key = deserialize(rs.getString("key"));
            result.add(key);
        }
        return result;
    }

    /**
     * Serializes an object to string for storing in database in TEXT type column.
     * @param object The object to serialize.
     * @return Serialization result of passed object.
     */
    protected String serialize(T object) {
        return object.toString();
    }

    /**
     * Deserializes string extracted from database to T object.
     * @param string The string to deserialize to T object.
     * @return A deserialized T object.
     */
    protected T deserialize(String string) {
        return (T) string;
    }
}
