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

    @Test
    void testInitExecutesSqlSuccessfully() throws SQLException {
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);

        Mockito.mockStatic(ConnectionFactory.class);
        when(ConnectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        assertDoesNotThrow(DataInicialization::init);

        verify(mockConnection).prepareStatement(anyString());
        verify(mockStatement).execute();
        verify(mockStatement).close();
        verify(mockConnection).close();

        Mockito.clearAllCaches();
    }

    @Test
    void testInitHandlesSQLException() throws SQLException {

        Connection mockConnection = Mockito.mock(Connection.class);

        Mockito.mockStatic(ConnectionFactory.class);
        when(ConnectionFactory.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Mock SQL Exception"));


        assertDoesNotThrow(DataInicialization::init);


        verify(mockConnection).prepareStatement(anyString());
        verify(mockConnection).close();


        Mockito.clearAllCaches();
    }
}