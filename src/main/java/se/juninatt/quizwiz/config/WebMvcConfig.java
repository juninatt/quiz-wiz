package se.juninatt.quizwiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

/**
 * Configuration class for customizing the web MVC settings for the application.
 * This class is used to set up resource handling, particularly for static resources.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Configures resource handlers for serving static resources.
     * This method sets up cache control headers and versioning for resources served from the /static/ path.
     *
     * @param registry The resource handler registry used to add resource handlers.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache().mustRevalidate())
                .resourceChain(true)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }
}

