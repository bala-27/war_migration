package warmigration;

import com.google.common.collect.Sets;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class WarMigrationResourceConfig extends ResourceConfig {

    public WarMigrationResourceConfig() {
        packages("warmigration.external", "warmigration.internal");
        registerClasses(
                Sets.newHashSet(
                        org.glassfish.jersey.filter.LoggingFilter.class,
                        org.glassfish.jersey.media.multipart.MultiPartFeature.class,
                        warmigration.filters.RequestFilter.class,
                        warmigration.filters.ResponseFilter.class,
                        warmigration.filters.SimpleExceptionMapper.class)
        );
    }

}
