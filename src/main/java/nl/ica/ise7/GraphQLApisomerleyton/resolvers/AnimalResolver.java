package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.GraphQLApisomerleyton.models.Animal;
import nl.ica.ise7.GraphQLApisomerleyton.models.Species;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AnimalResolver implements GraphQLResolver<Animal> {
    @Autowired
    private SpeciesRepository speciesRepository;

    public Species getSpecies(Animal animal){
        return speciesRepository.findBySpeciesName(animal.getSpeciesName());
    }
}
