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
    public static List<Entrepreneur> getEntrepreneurs() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = Resources.getResource("entrepreneurs.json").openStream();

        Map<String, List<Entrepreneur>> map = objectMapper.readValue(inputStream, new TypeReference<Map<String, List<Entrepreneur>>>() {});
        return map.values().stream()
                .flatMap(Collection::stream)
                .toList();
    }

    public static List<Company> getCompanies() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = Resources.getResource("companies.json").openStream();

        Map<String, List<Company>> map = objectMapper.readValue(inputStream, new TypeReference<Map<String, List<Company>>>() {});
        return map.values().stream()
                .flatMap(Collection::stream)
                .toList();
    }

    public static List<CloseInfoItem> getCloseInfoItems() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = Resources.getResource("closeinfo.json").openStream();

        Map<String, List<CloseInfoItem>> map = objectMapper.readValue(inputStream, new TypeReference<Map<String, List<CloseInfoItem>>>() {});
        return map.values().stream()
                .flatMap(Collection::stream)
                .toList();
    }

}
