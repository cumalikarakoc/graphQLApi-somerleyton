package nl.ica.ise7;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import nl.ica.ise7.adapters.GraphQLErrorAdapter;
import nl.ica.ise7.resolvers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GraphQlApiSomerleytonApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQlApiSomerleytonApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public Query query() {
        return new Query();
    }

    @Bean
    public Mutation mutation() {
        return new Mutation();
    }

    @Bean
    public EnclosureResolver enclosureResolver() {
        return new EnclosureResolver();
    }

    @Bean
    public AreaResolver areaResolver() {
        return new AreaResolver();
    }

    @Bean
    public AnimalResolver animalResolver() {
        return new AnimalResolver();
    }

    @Bean
    public ExchangeResolver exchangeResolver() {
        return new ExchangeResolver();
    }

    @Bean
    public AnimalEnclosureResolver animalEnclosureResolver() {
        return new AnimalEnclosureResolver();
    }
}
