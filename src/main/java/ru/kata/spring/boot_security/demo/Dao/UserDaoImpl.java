package ru.kata.spring.boot_security.demo.Dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User show(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        if(user.getAge() > 0 && user.getAge() <150) {
            entityManager.persist(user);
            entityManager.flush();
        }
    }

    @Override
    public void update(long id, User user) {
        if(user.getAge() > 0 && user.getAge() <150) {
            entityManager.merge(user);
            entityManager.flush();
        }
    }

    @Override
    public void delete(long id) {
        User user = show(id);
        if (null == user) {
            throw new NullPointerException("User not found");
        }
        entityManager.remove(user);
        entityManager.flush();
    }
}
