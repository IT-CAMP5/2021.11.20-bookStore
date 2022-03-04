package pl.camp.it.book.store.configuration;

import io.swagger.annotations.Api;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;

@Configuration
@ComponentScan("pl.camp.it.book.store")
public class AppConfiguration {

    @Bean
    public Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore7?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(Object.class, java.lang.Void.class)
                .select()
                .paths(PathSelectors.ant("/book/**"))
                //.apis(RequestHandlerSelectors.basePackage("pl.camp.it.book.store.controllers.rest"))
                .build()
                .apiInfo(createApiInfo());

        // http://localhost:8080/v2/api-docs - dokumentacja json
        // http://localhost:8080/swagger-ui.html - dokumentacja html
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("Book API",
                "Book store books API",
                "1.0",
                "http://google.pl",
                new Contact("Mateusz", "mateusz.pl", "mateusz@gmail.com"),
                "Comarch Licence",
                "http://google.pl",
                Collections.emptyList());
    }
}
