package warmigration.external;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sidharth on 25/12/16.
 */
@Path("/resource")
public class ExternalResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response simpleRequest() {
        return Response.ok("Looks good").build();
    }

}
