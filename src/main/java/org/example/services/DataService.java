package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.example.entities.CloseInfoItem;
import org.example.entities.Company;
import org.example.entities.Entrepreneur;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataService {

    public static <T> List<T> getData(String path, TypeReference<Map<String, List<T>>> typeReference) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = Resources.getResource(path).openStream();

        Map<String, List<T>> map = mapper.readValue(inputStream, typeReference);

        return map.values().stream()
                .flatMap(Collection::stream)
                .toList();
    }
}
