package org.example.organization.mapper;

import org.example.organization.model.Organization;
import org.example.organization.organizationview.OrganizationView;
import org.example.organization.organizationview.OrganizationViewShort;
import org.example.utils.customexception.NoListException;
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
    public List<OrganizationViewShort> all(List<Organization> organizationList) {
        if(organizationList.isEmpty()) {
            throw new NoListException("organization", "no organization have found with such parameters");
        }

        List<OrganizationViewShort> out = new ArrayList<>();

        for (Organization organization: organizationList) {
            OrganizationViewShort organizationViewShort = responseOrganizationMapBuilder(organization);
            out.add(organizationViewShort);
        }

        return out;
    }

    /**
     * Converts organization to responseMap
     * @param organization
     * @return
     */
    public OrganizationView getById(Organization organization) {

        OrganizationView organizationView = new OrganizationView();

        organizationView.setId(organization.getId());
        organizationView.setName(organization.getShortName());
        organizationView.setFullName(organization.getFullName());
        organizationView.setInn(organization.getInn());
        organizationView.setKpp(organization.getKpp());
        organizationView.setAddress(organization.getAddress());
        organizationView.setPhone(organization.getPhone());
        organizationView.setIsActive(String.valueOf(organization.getIsActive()));

        return organizationView;
    }

    private OrganizationViewShort responseOrganizationMapBuilder(Organization organization) {
        OrganizationViewShort organizationViewShort = new OrganizationViewShort();

        organizationViewShort.setId(organization.getId());
        organizationViewShort.setName(organization.getShortName());
        organizationViewShort.setIsActive(String.valueOf(organization.getIsActive()));

        return organizationViewShort;
    }
}
