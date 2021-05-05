package org.example.office.mapper;

import org.example.office.model.Office;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class OfficeMapper {

    /**
     * Converts List of offices to List of responseMaps
     * @param listOffice
     * @return
     */
    public List<Map<String, String>> all(List<Office> listOffice) {

        List<Map<String, String>> out = new ArrayList<>();
        for (Office office: listOffice) {
            Map<String, String> responseBody = new LinkedHashMap<>();

            responseBody.put("id", String.valueOf(office.getId()));
            responseBody.put("name", office.getOfficeName());
            responseBody.put("isActive", String.valueOf(office.getIsActive()));
            out.add(responseBody);
        }

        return out;
    }

    /**
     * Converts office to responseMap
     * @param office
     * @return
     */
    public Map<String, String> getById(Office office) {
        Map<String, String> responseBody = new LinkedHashMap<>();

        responseBody.put("id", String.valueOf(office.getId()));
        responseBody.put("name", office.getOfficeName());
        responseBody.put("address", office.getAddress());
        responseBody.put("phone", office.getPhone());
        responseBody.put("isActive", String.valueOf(office.getIsActive()));

        return responseBody;

    }
}
