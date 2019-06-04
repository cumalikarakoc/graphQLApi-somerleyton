package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import javassist.NotFoundException;
import nl.ica.ise7.GraphQLApisomerleyton.models.Keeper;
import nl.ica.ise7.GraphQLApisomerleyton.models.Species;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.KeeperRepository;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Mutation implements GraphQLMutationResolver {
    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private KeeperRepository keeperRepository;

    public Species newSpecies(Species input) throws Exception {
        if (speciesRepository.exists(input.getSpeciesName())) {
            throw new Exception("The species to be added already exists.");
        }
        Species species = new Species();

        return speciesRepository.save((mapInputToSpeciesModel(species, input)));
    }

    public Boolean removeSpecies(String speciesName) throws NotFoundException {
        Species species = speciesRepository.findBySpeciesName(speciesName);
        if (species == null) {
            throw new NotFoundException("The species to be deleted is not found");
        }
        speciesRepository.delete(species);
        return true;
    }

    public Species updateSpecies(String speciesName, Species input) throws NotFoundException {
        Species species = speciesRepository.findBySpeciesName(speciesName);
        if (species == null) {
            throw new NotFoundException("The species to be updated is not found");
        }

        Species mappedSpecies = mapInputToSpeciesModel(species, input);

        // save function saves an entity if the id doesnt exist in the database.
        // if the id has the same value as the input id then it will perform an update
        if (speciesName.equals(input.getSpeciesName())) {
            return speciesRepository.save(mappedSpecies);
        }
        speciesRepository.updateSpeciesName(speciesName, input.getSpeciesName());
        return speciesRepository.save(mappedSpecies);
    }

    private Species mapInputToSpeciesModel(Species species, Species input) {
        species.setSpeciesName(input.getSpeciesName());
        species.setDescription(input.getDescription());
        species.setFamily(input.getFamily());
        species.setSpecies(input.getSpecies());
        species.setSubspecies(input.getSubspecies());
        return species;
    }

    public Keeper newKeeper(String name) throws Exception {
        if (keeperRepository.exists(name)) {
            throw new Exception("The keeper to be added already exists.");
        }
        Keeper keeper = new Keeper();
        keeper.setName(name);
        return keeperRepository.save(keeper);
    }

    public Boolean removeKeeper(String name) throws NotFoundException {
        Keeper keeper = keeperRepository.findOne(name);
        if (keeper == null) {
            throw new NotFoundException("The keeper to be removed is not found.");
        }
        keeperRepository.delete(keeper);
        return true;
    }

    public Keeper updateKeeper(String name, Keeper input) throws NotFoundException {
        Keeper keeper = keeperRepository.findOne(name);
        if (keeper == null) {
            throw new NotFoundException("The keeper to be updated is not found.");
        }

        keeperRepository.updateKeeper(keeper.getName(), input.getName());
        keeper.setName(input.getName());
        return keeper;
    }
}
