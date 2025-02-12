package com.testsquad.utils;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class EnvironmentConfig {
    private static final String ENV_PATH = "environments/%s.yaml";
    private static Map<String, Object> config;

    public static void loadEnvironment(String env) {
        try (InputStream input = EnvironmentConfig.class.getClassLoader()
                .getResourceAsStream(String.format(ENV_PATH, env))) {
            Yaml yaml = new Yaml();
            config = yaml.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load environment config: " + env, e);
        }
    }

    public static String getBaseUrl() {
        return getString("baseUrl");
    }

    public static String getApiBaseUrl() {
        return getString("apiBaseUrl");
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getCredentials() {
        return (Map<String, String>) config.get("credentials");
    }

    private static String getString(String key) {
        return (String) config.get(key);
    }
} 