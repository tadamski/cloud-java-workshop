package org.tadamski.examples.cloud.proxy;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.tadamski.examples.cloud.foo.api.Foo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class FooProxy {

    @Inject
    @ConfigProperty(name="foo.service.url")
    private String targetPath;

    public Foo getFoo(String name){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(targetPath+"/foo/"+name);
        return target.request(MediaType.APPLICATION_JSON).get(Foo.class);
    }

}