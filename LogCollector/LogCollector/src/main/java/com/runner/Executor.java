package com.runner;

import com.google.gson.Gson;
import model.Log;
import org.springframework.stereotype.Component;
import service.DBService;
import service.RestLogCollector;
import sort.ITreeSort;
import sort.ITreeVisitor;
import sort.Key;
import sort.Tree;

import java.sql.SQLException;
import java.util.List;

@Component
public class Executor {

    DBService dbService;

    RestLogCollector restLogCollector;

    public Executor(RestLogCollector restLogCollector, DBService dbService) {
        this.dbService = dbService;
        this.restLogCollector = restLogCollector;
    }

    public void run() {
        List<Log> logs = restLogCollector.getLog();
        System.out.println("message income");
        ITreeSort treeSort = new Tree(logs.get(0));

        for (int i = 1; i < logs.size(); i++) {
            treeSort.insert(new Tree(logs.get(i)));
        }
        ITreeVisitor treeVisitor = new Key();
        treeSort.traverse(treeVisitor, logs, 0);
        System.out.println("message sort");

        try {
            dbService.setConnection();
            dbService.setLog(logs);
            System.out.println("message insert");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
