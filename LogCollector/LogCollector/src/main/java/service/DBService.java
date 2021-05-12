package service;

import model.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class DBService implements IDBService {

    @Value("${spring.datasource.url}")
    private String connectionUrl;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
    static String JDBC_DRIVER = "org.h2.Driver";
    Connection connection;
    private final String INSERT_LOG = "insert into message_log " +
            "(created_at, first_name, message, second_name, user_id)" +
            " values (?,?,?,?,?)";

    private final String SELECT_LOG = "select created_at, first_name, message, second_name, user_id" +
            " from message_log" +
            " where created_at =?";

    public DBService() {
    }

    public void setConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(connectionUrl, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void setLog(List<Log> logs) throws SQLException {
        for (Log log : logs) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG);
            preparedStatement.setDate(1, Date.valueOf(log.getCreated_at().toLocalDate()));
            preparedStatement.setString(2, log.getFirst_name());
            preparedStatement.setString(3, log.getMessage());
            preparedStatement.setString(4, log.getSecond_name());
            preparedStatement.setInt(5, log.getUser_id());
            preparedStatement.execute();
        }
    }

    @Override
    public ResultSet getLog(Date date) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOG);
        preparedStatement.setDate(1, date);
        return preparedStatement.getResultSet();
    }

    @Override
    protected void finalize() throws Throwable {
        connection.close();
        super.finalize();
    }
}
