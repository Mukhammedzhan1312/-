import java.util.HashMap;
import java.util.Map;

class ConfigurationManager {

    private static ConfigurationManager instance;

    // Хранилище конфигурационных данных
    private Map<String, String> settings;

    private ConfigurationManager() {
        settings = new HashMap<>();
    }

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }


    public void loadSettings(String source) {
        // Логика загрузки настроек из файла, базы данных или других источников
        settings.put("appName", "MyApplication");
        settings.put("version", "1.0");
        System.out.println("Settings loaded from: " + source);
    }

    public String getSetting(String key) {
        return settings.get(key);
    }

    public void updateSetting(String key, String value) {
        settings.put(key, value);
        logChange(key, value);
    }

    // Метод для сохранения настроек
    public void saveSettings() {
        System.out.println("Settings saved.");
    }


    private void logChange(String key, String value) {
        System.out.println("Setting changed: " + key + " = " + value);
    }
}

public class Main {
    public static void main(String[] args) {

        try {
            ConfigurationManager configManager = ConfigurationManager.getInstance();


            System.out.println("Loading settings...");
            configManager.loadSettings("configFile.xml");


            String appName = configManager.getSetting("appName");
            String version = configManager.getSetting("version");
            System.out.println("App Name: " + appName);
            System.out.println("Version: " + version);

            System.out.println("Updating appName...");
            configManager.updateSetting("appName", "UpdatedAppName");

            System.out.println("Saving settings...");
            configManager.saveSettings();

            appName = configManager.getSetting("appName");
            System.out.println("Updated App Name: " + appName);

        } catch (Exception e) {

            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

