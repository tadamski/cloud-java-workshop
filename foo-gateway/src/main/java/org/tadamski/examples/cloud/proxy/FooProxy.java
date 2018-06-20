package org.tadamski.examples.cloud.proxy;

import org.tadamski.examples.cloud.foo.api.Foo;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class FooProxy {

    private final String targetPath = System.getProperty("proxy.foo.url");

    public Foo getFoo(String name){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(targetPath+"/foo/"+name);
        return target.request(MediaType.APPLICATION_JSON).get(Foo.class);
    }

}