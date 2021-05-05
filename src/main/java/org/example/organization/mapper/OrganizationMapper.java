package org.example.organization.mapper;

import org.example.organization.model.Organization;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrganizationMapper {

    /**
     * Converts List of organizations to List of responseMaps
     * @param organizationList
     * @return
     */
    public List<Map<String, String>> all(List<Organization> organizationList) {
        if(organizationList.isEmpty()) {
            throw new RuntimeException("no organization have found with such parameters");
        }

        List<Map<String, String>> out = new ArrayList<>();

        for (Organization organization: organizationList) {
            Map<String, String> responseMap = responseOrganizationMapBuilder(organization);
            out.add(responseMap);
        }

        return out;
    }

    /**
     * Converts organization to responseMap
     * @param organization
     * @return
     */
    public Map<String, String> getById(Organization organization) {

        Map<String, String> responseMap = new LinkedHashMap<>();

        responseMap.put("id", String.valueOf(organization.getId()));
        responseMap.put("name", organization.getShortName());
        responseMap.put("fullName", organization.getFullName());
        responseMap.put("inn", organization.getInn());
        responseMap.put("kpp", organization.getKpp());
        responseMap.put("address", organization.getAddress());
        responseMap.put("phone", organization.getPhone());
        responseMap.put("isActive", String.valueOf(organization.getIsActive()));

        return responseMap;
    }

    private Map<String, String> responseOrganizationMapBuilder(Organization organization) {
        Map<String, String> responseOrganizationMap = new LinkedHashMap<>();

        responseOrganizationMap.put("id", String.valueOf(organization.getId()));
        responseOrganizationMap.put("name", organization.getShortName());
        responseOrganizationMap.put("isActive", String.valueOf(organization.getIsActive()));

        return responseOrganizationMap;
    }
}
