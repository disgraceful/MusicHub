package com.mymedia.web.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.mvc.model.User;

@Repository
@EnableTransactionManagement
public class UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOG = LogManager.getLogger(UserDAO.class);

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        return users;
    }

    @Transactional
    public User getUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(User.class, new Integer(id));
    }


    @Transactional
    public User addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        int id = (Integer)session.save(user);
        return getUser(id);
    }

    @Transactional
    public User updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        return getUser(user.getId());
        
    }

    @Transactional
    public void deleteUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Transactional
    public void deleteUser(int id){
        deleteUser(getUser(id));
    }
}
