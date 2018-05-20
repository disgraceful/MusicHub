package com.mymedia.web.dao;

import com.mymedia.web.mvc.model.Role;
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
public class RoleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOG = LogManager.getLogger(SongDAO.class);

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public List<Role> getAllRoles() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Role> roles = session.createQuery("from Role").list();
        return roles;
    }

    @Transactional
    public Role getRole(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Role.class, new String(id));
    }

    @Transactional
    public Role addRole(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        String id = (String)session.save(role);
        return getRole(id);
    }

    @Transactional
    public Role updateUser(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(role);
        return getRole(role.getId());
    }

    @Transactional
    public void deleteRole(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(role);
    }

    @Transactional
    public void deleteRoleById(String id){
        deleteRole(getRole(id));
    }
}
