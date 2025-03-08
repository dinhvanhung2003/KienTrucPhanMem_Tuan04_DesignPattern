package com.example.designpattern.singletonpattern;


import java.util.HashMap;
import java.util.Map;

// Singleton cho quản lý cấu hình
class ConfigManager {
    private static ConfigManager instance;
    private Map<String, String> configMap;

    // Constructor private để ngăn chặn tạo nhiều instance
    private ConfigManager() {
        configMap = new HashMap<>();
    }

    // Phương thức để lấy instance duy nhất
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    // Phương thức để lấy giá trị cấu hình
    public String getConfig(String key) {
        return configMap.get(key);
    }

    // Phương thức để đặt giá trị cấu hình
    public void setConfig(String key, String value) {
        configMap.put(key, value);
    }
}

// Chương trình kiểm tra Singleton
public class SingletonExample {
    public static void main(String[] args) {
        ConfigManager config = ConfigManager.getInstance();
        config.setConfig("database_url", "jdbc:mysql://localhost:3306/mydb");
        config.setConfig("api_key", "123456");

        // Lấy lại cấu hình từ instance duy nhất
        System.out.println("Database URL: " + config.getConfig("database_url"));
        System.out.println("API Key: " + config.getConfig("api_key"));

        // Kiểm tra Singleton
        ConfigManager config2 = ConfigManager.getInstance();
        System.out.println("ConfigManager là cùng một instance: " + (config == config2));
    }
}

