package org.tadamski.examples.cloud;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URL;

@RunWith(Arquillian.class)
public class BlipCountTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(Foo.class, FooService.class, FooResource.class, FooApplication.class)
                .addAsResource("datasources.yml")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/load.sql")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @CreateSwarm
    public static Swarm createSwarm() throws Exception {
        Swarm swarm = new Swarm();

        ClassLoader cl = FooService.class.getClassLoader();
        URL dataSourcesConfig = cl.getResource("datasources.yml");

        swarm.withConfig(dataSourcesConfig);
        return swarm;
    }


    @ArquillianResource
    private URL url;

    private Foo testEndpoint(String name) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url + "foo/" + name);
        return target.request("application/json").get(Foo.class);
    }

    @Test
    public void testBlipCount() {
        Assert.assertEquals(testEndpoint("gyrd").getBlipCount(),5);
        Assert.assertEquals(testEndpoint("fab").getBlipCount(),10);
    }
}
