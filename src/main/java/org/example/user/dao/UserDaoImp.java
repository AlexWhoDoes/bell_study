package org.example.user.dao;

import org.example.user.requestobject.UserListRequest;
import org.example.user.model.User;
import org.example.utils.customexception.NoObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
public class UserDaoImp implements UserDao {

    private final EntityManager entityManager;
    private final Logger log;

    @Autowired
    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.log = LoggerFactory.getLogger(getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> all(UserListRequest userListRequest) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder(userListRequest);
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(Long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT p FROM User p WHERE p.id =: USER_ID", User.class);
        query.setParameter("USER_ID", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            log.error("Failed to retrieve a user with id " + id + " from db. A user does not exist.");
            throw new NoObjectException(id, "There is no user with id " + id, "user");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long addOrUpdate(User newUser) {
        entityManager.persist(newUser);
        return newUser.getId();
    }

    private CriteriaQuery<User> criteriaBuilder(UserListRequest userListRequest) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        Predicate officeIdPredicate = builder.equal(userRoot.get("office").get("id"), userListRequest.getOfficeId());
        predicates.add(officeIdPredicate);

        if(userListRequest.getFirstName() != null) {
            Predicate firstNamePredicate = builder.equal(userRoot.get("firstName"), userListRequest.getFirstName());
            predicates.add(firstNamePredicate);
        }

        if(userListRequest.getSecondName() != null) {
            Predicate secondNamePredicate = builder.equal(userRoot.get("secondName"), userListRequest.getSecondName());
            predicates.add(secondNamePredicate);
        }

        if(userListRequest.getMiddleName() != null) {
            Predicate middleNamePredicate = builder.equal(userRoot.get("middleName"), userListRequest.getMiddleName());
            predicates.add(middleNamePredicate);
        }

        if (userListRequest.getPosition() != null) {
            Predicate positionPredicate = builder.equal(userRoot.get("position"), userListRequest.getPosition());
            predicates.add(positionPredicate);
        }

        if (userListRequest.getDocCode() != null) {
            Predicate docCodePredicate = builder.equal(userRoot.get("document").get("documentType").get("code"), userListRequest.getDocCode());
            predicates.add(docCodePredicate);
        }

        if (userListRequest.getCitizenshipCode() != null) {
            Predicate citizenshipCodePredicate = builder.equal(userRoot.get("citizenship").get("code"), userListRequest.getCitizenshipCode());
            predicates.add(citizenshipCodePredicate);
        }

        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[0])));

        return criteriaQuery;
    }
}
