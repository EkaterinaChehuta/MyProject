package com.example.testEightList.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionConfig {
    private static Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public ConnectionConfig() {
        connection();
    }

    public static void connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            connection = getNewConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getNewConnection() throws IOException, SQLException {
        Properties props = new Properties();

        try (InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/database.properties"))) {
            props.load(inputStream);
        }

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
