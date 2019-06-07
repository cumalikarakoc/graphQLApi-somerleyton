package nl.ica.ise7.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.models.Animal;
import nl.ica.ise7.models.Species;
import nl.ica.ise7.repositories.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AnimalResolver implements GraphQLResolver<Animal> {
    @Autowired
    private SpeciesRepository speciesRepository;

    public Species getSpecies(Animal animal){
        return speciesRepository.findBySpeciesName(animal.getSpeciesName());
    }
}
