package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import javassist.NotFoundException;
import nl.ica.ise7.GraphQLApisomerleyton.models.Species;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.SpeciesRepository;

public class Mutation implements GraphQLMutationResolver {
    private SpeciesRepository speciesRepository;

    public Mutation(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public Species newSpecies(String speciesName, String description, String family, String species, String subspecies) {
        Species speciesInstance = new Species();
        speciesInstance.setSpeciesName(speciesName);
        speciesInstance.setDescription(description);
        speciesInstance.setFamily(family);
        speciesInstance.setSpecies(species);
        speciesInstance.setSubspecies(subspecies);

        speciesRepository.save((speciesInstance));

        return speciesInstance;
    }

    public Boolean removeSpecies(String speciesName) throws NotFoundException {
        Species species = speciesRepository.findBySpeciesName(speciesName);
        if(species == null){
            throw new NotFoundException("The species to be deleted is not found");
        }
        speciesRepository.delete(species);
        return true;
    }
}
