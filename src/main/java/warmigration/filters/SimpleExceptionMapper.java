package warmigration.filters;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Component
public class SimpleExceptionMapper implements ExceptionMapper<IllegalStateException> {


    @Override
    public Response toResponse(IllegalStateException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
    }
}
