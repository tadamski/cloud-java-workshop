package org.tadamski.examples.cloud;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class FooService {

    @PersistenceContext(unitName = "FooPU")
    private EntityManager em;


    public Foo findByName(String name){
        return em.createNamedQuery("Foo.findByName", Foo.class).setParameter("name", name).getSingleResult();
    }
}
