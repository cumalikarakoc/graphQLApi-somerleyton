package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.GraphQLApisomerleyton.models.Animal;
import nl.ica.ise7.GraphQLApisomerleyton.models.Exchange;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExchangeResolver implements GraphQLResolver<Exchange> {
    @Autowired
    private AnimalRepository animalRepository;

    public Animal getAnimal(Exchange exchange){
        return animalRepository.findOne(exchange.getIdentity().getAnimalId());
    }
}
