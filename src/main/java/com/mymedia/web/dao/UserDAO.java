package com.mymedia.web.dao;

import com.mymedia.web.mvc.model.Song;
import com.mymedia.web.mvc.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nazar on 11.04.2017.
 */
@Repository
@EnableTransactionManagement
public class UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOG = LogManager.getLogger(SongDAO.class);

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
        LOG.info(user.getUsername() + " ");
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
