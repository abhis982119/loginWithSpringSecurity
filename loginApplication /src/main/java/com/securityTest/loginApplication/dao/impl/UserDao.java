package com.securityTest.loginApplication.dao.impl;

import com.securityTest.loginApplication.dao.api.IUserDAO;
import com.securityTest.loginApplication.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional
public class UserDao implements IUserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public UserEntity getUser(String userName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("userName"), userName));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public void saveUser(UserEntity user) {
        entityManager.persist(user);
    }
}
