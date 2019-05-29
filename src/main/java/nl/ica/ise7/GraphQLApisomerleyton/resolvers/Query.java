package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import javassist.NotFoundException;
import nl.ica.ise7.GraphQLApisomerleyton.models.Species;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Query implements GraphQLQueryResolver {

    @Autowired
    private SpeciesRepository speciesRepository;

    public Iterable<Species> allSpecies() {
        return speciesRepository.findAll();
    }

    public Species speciesByName(String speciesName) throws NotFoundException {
        Species species = speciesRepository.findBySpeciesName(speciesName);
        if(species == null){
            throw new NotFoundException("The species " + speciesName + " is not found" );
        }
        return species;
    }

}
