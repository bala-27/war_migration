package warmigration.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import warmigration.repositories.SimpleRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/external/resource")
public class ExternalResource {

    private SimpleRepository simpleRepository;

    @Autowired
    public ExternalResource(SimpleRepository simpleRepository) {

        this.simpleRepository = simpleRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response simpleRequest() {
        return Response.ok("Looks good").build();
    }

}
