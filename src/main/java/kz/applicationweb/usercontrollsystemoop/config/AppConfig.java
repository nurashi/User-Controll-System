package kz.applicationweb.usercontrollsystemoop.config;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private static AppConfig instance;
    private Map<String, String> configSettings;
    
    private AppConfig() {
        configSettings = new HashMap<>();
        configSettings.put("app.name", "User Management System");
        configSettings.put("app.version", "1.0");
        configSettings.put("swagger.title", "User Management System API");
        configSettings.put("swagger.description", "API Documentation for User Management System");
        configSettings.put("swagger.version", "1.0");
    }
    
    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
    
    public String getConfig(String key) {
        return configSettings.get(key);
    }
    
    public void setConfig(String key, String value) {
        configSettings.put(key, value);
    }
}