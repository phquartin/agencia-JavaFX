package com.agencia.project.util.initdb;

import com.agencia.project.util.conn.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class DataInicializationTest {

    /**
     * Class Description:
     * The `DataInicialization` class is responsible for initializing the database schema.
     * It contains a single `init` method that executes a predefined SQL script to create the necessary database tables.
     */

    @Test
    void testInitExecutesSqlSuccessfully() throws SQLException {
        // Mock the connection and prepared statement
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);

        // Stub the ConnectionFactory to return the mock connection
        Mockito.mockStatic(ConnectionFactory.class);
        when(ConnectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        // Assert that the method does not throw any exception
        assertDoesNotThrow(DataInicialization::init);

        // Verify the SQL execution
        verify(mockConnection).prepareStatement(anyString());
        verify(mockStatement).execute();
        verify(mockStatement).close();
        verify(mockConnection).close();

        // Clear the mocked static method
        Mockito.clearAllCaches();
    }

    @Test
    void testInitHandlesSQLException() throws SQLException {
        // Mock the connection and prepared statement
        Connection mockConnection = Mockito.mock(Connection.class);

        // Stub the ConnectionFactory to throw an exception
        Mockito.mockStatic(ConnectionFactory.class);
        when(ConnectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Mock SQL Exception"));

        // Assert that the method does not throw any exception
        assertDoesNotThrow(DataInicialization::init);

        // Verify the interaction with the connection
        verify(mockConnection).prepareStatement(anyString());
        verify(mockConnection).close();

        // Clear the mocked static method
        Mockito.clearAllCaches();
    }
}