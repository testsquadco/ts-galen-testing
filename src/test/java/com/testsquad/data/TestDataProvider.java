package com.testsquad.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;
import java.util.Map;

public class TestDataProvider {
    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private static final String DATA_PATH = "testdata/%s.yaml";

    public static <T> T loadTestData(String fileName, Class<T> type) {
        try (InputStream input = TestDataProvider.class.getClassLoader()
                .getResourceAsStream(String.format(DATA_PATH, fileName))) {
            return mapper.readValue(input, type);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data: " + fileName, e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> loadTestData(String fileName) {
        return loadTestData(fileName, Map.class);
    }
} 