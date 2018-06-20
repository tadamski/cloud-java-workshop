package org.tadamski.examples.cloud;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class FooService {

    private Map<String, Foo> foos = new HashMap<>();

    public FooService(){
        foos.put("gyrd", new Foo("gyrd", 5));
    }

    public Foo findByName(String name){
        return foos.get(name);
    }
}
