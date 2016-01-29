package com.example.helloworld.resources;

/**
 * Created by plinde on 1/17/16.
 */

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.Saying;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Api("/hello-world")
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    final Logger logger = LoggerFactory.getLogger(getClass());

//    logger.info("spring.cloud.config.uri : " + url);
//    logger.info("production.host : " + host);


    @GET
    @Timed
    @ApiOperation("sayHello")
    public Saying sayHello(@QueryParam("name") String name) {

        final String value = String.format(template, name);

        logger.info(String.valueOf(counter.get() + 1), value);


/*
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8888/bar/default");
        WebTarget resourceWebTarget = webTarget.path("resource");

        WebTarget helloworldWebTarget = resourceWebTarget.path("helloworld");
        WebTarget helloworldWebTargetWithQueryParam =
                helloworldWebTarget.queryParam("greeting", "Hi World!");

        Invocation.Builder invocationBuilder =
                helloworldWebTargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE);
        invocationBuilder.header("some-header", "true");

        Response response = invocationBuilder.get();
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
*/


        return new Saying(counter.incrementAndGet(), value);
    }
}