package com.cloud.base.HiberDao.impl;

import com.cloud.base.HiberDao.PersonDao;
import com.cloud.base.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Person person) {
        em.persist(person);
    }

    @Override
    public void update(Person person) {
        em.persist(em.merge(person));
    }

    @Override
    public Person findPersonByID(Integer id) {
        return em.find(Person.class, id);
    }

    @Override
    public List<Person> findPersonsNoPage() {
        String queryString = "select * from person";
        Query query = em.createNativeQuery(queryString, Person.class);
        List<?> result = query.getResultList();
        List<Person> persons = new ArrayList<>();
        if (result != null) {
            for (Object aResult : result) {
                Person person = (Person) aResult;
                persons.add(person);
            }
        }
        return persons;
    }

    @Override
    public void delete(Person person) {
        em.remove(em.merge(person));
    }

}
