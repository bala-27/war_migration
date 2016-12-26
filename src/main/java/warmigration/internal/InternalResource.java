package warmigration.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import warmigration.repositories.SimpleRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/internal/resource")
public class InternalResource {

    private SimpleRepository simpleRepository;

    @Autowired
    public InternalResource(SimpleRepository simpleRepository){

        this.simpleRepository = simpleRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response simpleRequest() {
        System.out.println("Blah");
        return Response.ok("Looks good").build();
    }

    @GET
    @Path("/illegal")
    public void illegalRequest(){
        throw new IllegalStateException();
    }

}
