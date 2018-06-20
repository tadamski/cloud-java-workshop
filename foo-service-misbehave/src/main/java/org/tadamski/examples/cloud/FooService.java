package org.tadamski.examples.cloud;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@ApplicationScoped
public class FooService {

    @PersistenceContext(unitName = "FooPU")
    private EntityManager em;


    public Foo findByName(String name){
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10));
        return em.createNamedQuery("Foo.findByName", Foo.class).setParameter("name", name).getSingleResult();
    }
}
