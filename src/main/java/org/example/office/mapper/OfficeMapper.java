package org.example.office.mapper;

import org.example.office.model.Office;
import org.example.office.officeview.OfficeView;
import org.example.office.officeview.OfficeViewShort;
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
    public List<OfficeViewShort> all(List<Office> listOffice) {

        List<OfficeViewShort> out = new ArrayList<>();
        for (Office office: listOffice) {
            OfficeViewShort officeViewShort = new OfficeViewShort();

            officeViewShort.setId(office.getId());
            officeViewShort.setName(office.getOfficeName());
            officeViewShort.setIsActive(String.valueOf(office.getIsActive()));

            out.add(officeViewShort);
        }

        return out;
    }

    /**
     * Converts office to responseMap
     * @param office
     * @return
     */
    public OfficeView getById(Office office) {
        OfficeView officeView = new OfficeView();

        officeView.setId(office.getId());
        officeView.setName(office.getOfficeName());
        officeView.setAddress(office.getAddress());
        officeView.setPhone(office.getPhone());
        officeView.setIsActive(String.valueOf(office.getIsActive()));

        return officeView;

    }
}
