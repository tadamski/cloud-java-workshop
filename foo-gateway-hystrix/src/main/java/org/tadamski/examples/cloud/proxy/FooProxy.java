package org.tadamski.examples.cloud.proxy;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.tadamski.examples.cloud.foo.api.Foo;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class FooProxy {

    private final String targetPath = System.getProperty("proxy.foo.url");

    public Foo getFoo(String name){
        Response response = new GetBlipCountCommand(name).execute();
        if(response.getStatus() != Response.Status.OK.getStatusCode()){
            throw new WebApplicationException(response);
        }
        return response.readEntity(Foo.class);
    }

    private class GetBlipCountCommand extends HystrixCommand<Response> {

        private final String name;

        public GetBlipCountCommand(String name) {
            super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("foo-service"))
                    .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(3)));
            this.name = name;
        }

        @Override
        protected Response run() {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(targetPath+"/foo/"+name);
            return target.request(MediaType.APPLICATION_JSON).get();
        }

//        @Override
//        protected Response getFallback() {
//            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
//        }
    }
}