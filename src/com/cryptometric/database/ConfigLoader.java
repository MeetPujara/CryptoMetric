package com.cryptometric.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Error: config.properties file not found!");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}