package warmigration;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean externalJersey() {
        String name = "External";
        List<String> packages = Lists.newArrayList("warmigration.external");
        Set<Class<?>> classes = Sets.newHashSet(
                org.glassfish.jersey.filter.LoggingFilter.class,
                org.glassfish.jersey.media.multipart.MultiPartFeature.class,
                warmigration.filters.RequestFilter.class,
                warmigration.filters.ResponseFilter.class);
        String urlMappings = "/external/*";

        return getServletRegistrationBean(name, packages, classes, urlMappings);
    }

    @Bean
    public ServletRegistrationBean internalJersey() {
        String name = "Internal";
        List<String> packages = Lists.newArrayList("warmigration.internal");
        Set<Class<?>> classes = Sets.newHashSet(
                org.glassfish.jersey.filter.LoggingFilter.class,
                org.glassfish.jersey.media.multipart.MultiPartFeature.class,
                warmigration.filters.RequestFilter.class,
                warmigration.filters.ResponseFilter.class,
                warmigration.filters.SimpleExceptionMapper.class);
        String urlMappings = "/internal/*";

        return getServletRegistrationBean(name, packages, classes, urlMappings);
    }

    private ServletRegistrationBean getServletRegistrationBean(String name, List<String> packages, Set<Class<?>> classes,
                                                               String urlMappings) {
        ResourceConfig config = new ResourceConfig();
        config.packages(packages.toArray(new String[0]));
        config.registerClasses(classes);
        ServletRegistrationBean publicJersey
                = new ServletRegistrationBean(new ServletContainer(config));
        publicJersey.addUrlMappings(urlMappings);


        publicJersey.setName(name);
        publicJersey.setLoadOnStartup(1);
        return publicJersey;
    }

}
