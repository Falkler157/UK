package service;

import model.Log;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDBService {

    /**
     * Метод для записи логов в БД
     */
    void setLog(List<Log> logs) throws SQLException;

    /**
     * Публичный метод для получение логов из базы за определенную дату
     */
    ResultSet getLog(Date date) throws SQLException;
}
