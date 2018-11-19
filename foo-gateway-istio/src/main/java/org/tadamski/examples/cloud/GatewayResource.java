package org.tadamski.examples.cloud;

import org.tadamski.examples.cloud.foo.api.Foo;
import org.tadamski.examples.cloud.proxy.FooProxy;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class GatewayResource {

    @Inject
    private FooProxy fooProxy;

    @GET
    @Path("/foo/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response foo(@PathParam("name") String name) {
        Foo result = fooProxy.getFoo(name);
        return Response.ok(result).build();
    }

}
