package org.example.office.dao;
import org.example.office.model.Office;
import org.example.office.requestobject.OfficeListRequest;

import org.springframework.data.jpa.domain.Specification;


public class OfficeSpecification {

    /**
     * Creates a filter to retrieve a list of offices
     * @param officeListRequest
     * @return
     */

    public static Specification<Office> getSpecification(OfficeListRequest officeListRequest) {

        Specification<Office> specification = getSpecId(officeListRequest);


        if (officeListRequest.getName() != null) {
            specification = specification.and(getSpecOfficeName(officeListRequest));
        }

        if (officeListRequest.getPhone() != null) {
            specification = specification.and(getSpecOfficePhone(officeListRequest));
        }

        if (officeListRequest.getIsActive() != null) {
            specification = specification.and(getSpecOfficeIsActive(officeListRequest));
        }

        return specification;
    }


    private static Specification<Office> getSpecId(OfficeListRequest officeListRequest) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("organization").get("id"), String.valueOf(officeListRequest.getOrgId()));
    }

    private static Specification<Office> getSpecOfficeName(OfficeListRequest officeListRequest) {
        if (officeListRequest.getName() == null) {
            return null;
        } else {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("officeName"), officeListRequest.getName());
        }
    }

    private static Specification<Office> getSpecOfficePhone(OfficeListRequest officeListRequest) {
        if (officeListRequest.getPhone() == null) {
            return null;
        } else {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("phone"), officeListRequest.getPhone());
        }
    }

    private static Specification<Office> getSpecOfficeIsActive(OfficeListRequest officeListRequest) {
        if (officeListRequest.getIsActive() == null) {
            return null;
        } else {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), officeListRequest.getIsActive());
        }
    }
}
