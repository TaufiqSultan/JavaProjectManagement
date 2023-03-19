package sr.unasat.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.unasat.configuration.JPAConfig;
import sr.unasat.entities.User;

import java.util.List;

public class UserDAO {
    private EntityManager entityManager = JPAConfig.getEntityManager();
    private EntityTransaction transaction = entityManager.getTransaction();

    public List<User> findAllUsers() {
        transaction.begin();
        String jpql = "select u from User u";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        List<User> userList = query.getResultList();
        transaction.commit();
        return userList;
    }

    public User findUserById(Long id) {
        transaction.begin();
        String jpql = "select u from User u where u.userID = :id";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        User user = query.setParameter("id", id).getSingleResult();
        transaction.commit();
        return user;
    }

    public User findUserByUsername(String username) {
        transaction.begin();
        String jpql = "select u from User u where u.username = :username";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        User user = query.setParameter("username", username).getSingleResult();
        transaction.commit();
        return user;
    }

    public User insertUser(User user) {
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        return user;
    }

    public int updateUser(User user) {
        transaction.begin();
        String jpql = "update User u set u.userName = :userName, u.userEmail = :userEmail, u.username = :username, u.password = :password where u.userID = :id";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("userName", user.getUserName());
        query.setParameter("userEmail", user.getUserEmail());
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        query.setParameter("id", user.getUserID());
        int rowsUpdated = query.executeUpdate();
        transaction.commit();
        return rowsUpdated;
    }

    public int deleteUser(Long id) {
        transaction.begin();
        String jpql = "delete from User u where u.userID = :id";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("id", id);
        int rowsDeleted = query.executeUpdate();
        transaction.commit();
        return rowsDeleted;
    }
}
