package nl.ica.ise7.GraphQLApisomerleyton;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import nl.ica.ise7.GraphQLApisomerleyton.adapters.GraphQLErrorAdapter;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.SpeciesRepository;
import nl.ica.ise7.GraphQLApisomerleyton.resolvers.Mutation;
import nl.ica.ise7.GraphQLApisomerleyton.resolvers.Query;
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
    public Query query(SpeciesRepository speciesRepository) {
        return new Query(speciesRepository);
    }

    @Bean
    public Mutation mutation(SpeciesRepository speciesRepository) {
        return new Mutation(speciesRepository);
    }

}
