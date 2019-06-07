package nl.ica.ise7.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.models.Animal;
import nl.ica.ise7.models.Exchange;
import nl.ica.ise7.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExchangeResolver implements GraphQLResolver<Exchange> {
    @Autowired
    private AnimalRepository animalRepository;

    public Animal getAnimal(Exchange exchange){
        return animalRepository.findOne(exchange.getIdentity().getAnimalId());
    }
}
