package org.example.organization.dao;

import org.example.organization.model.Organization;
import org.example.organization.requestobject.OrganizationListRequest;
import org.example.utils.customexception.NoObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizationDaoImp implements OrganizationDao {

    private final EntityManager entityManager;
    private final Logger log;

    @Autowired
    public OrganizationDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.log = LoggerFactory.getLogger(getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Organization> all(OrganizationListRequest organizationListRequest) {
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder(organizationListRequest);
        TypedQuery<Organization> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Organization getById(Long id) {
        TypedQuery<Organization> query = entityManager.createNamedQuery("OrganizationGetById", Organization.class);
        query.setParameter("ORGANIZATION_ID", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            log.error("Failed to retrieve an organization with id " + id + " from db. An organization does not exist.");
            throw new NoObjectException(id, "There is no organization with id " + id, "organization");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addOrUpdate(Organization organization) {
        entityManager.persist(organization);
    }

    private CriteriaQuery<Organization> criteriaBuilder(OrganizationListRequest organizationListRequest) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);

        List<Predicate> predicates = new ArrayList<>();

        Predicate namePredicate = builder.equal(organizationRoot.get("shortName"), organizationListRequest.getName());
        predicates.add(namePredicate);

        if(organizationListRequest.getInn() != null) {
            Predicate innPredicate = builder.equal(organizationRoot.get("inn"), organizationListRequest.getInn());
            predicates.add(innPredicate);
        }

        if(organizationListRequest.getIsActive() != null) {
            Predicate isActivePredicate = builder.equal(organizationRoot.get("isActive"), organizationListRequest.getIsActive());
            predicates.add(isActivePredicate);
        }

        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[0])));

        return criteriaQuery;
    }
}
