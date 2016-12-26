package warmigration.filters;

import org.springframework.stereotype.Component;
import warmigration.repositories.SimpleRepository;
import warmigration.repositories.UserRepository;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@PreMatching
@Provider
@Priority(Priorities.AUTHORIZATION)
public class RequestFilter implements ContainerRequestFilter {
    public static final String METHOD_TYPE_OPTIONS = "OPTIONS";
    private UserRepository userRepository;

    public RequestFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
        ignoreAuthPaths.add("external/noauth");
    }

    private static final List<String> ignoreAuthPaths = new ArrayList<>();
    @Context
    private HttpServletRequest servletRequest;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String userId = requestContext.getHeaderString("X_USER_ID");
        if (shouldIgnoreAuth(requestContext)) {
            return;
        }
        if (userId == null || !userRepository.isValidUserId(userId)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Not authorized!").build());
        }
    }

    private boolean shouldIgnoreAuth(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath(true);
        String remoteHost = servletRequest.getRemoteHost();
        // is client behind something?
        String ipAddress = servletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = servletRequest.getRemoteAddr();
        }

        if (requestContext.getMethod().equalsIgnoreCase(METHOD_TYPE_OPTIONS))
            return true;

        if (ignoreAuthPaths.contains(path)) {
            System.out.println("Ignoring Authorization for this request. Path[" + path + "]");
            return true;
        } else if (path.startsWith("api-docs")) {
            return true;
        } else {
            return false;
        }
    }

}
