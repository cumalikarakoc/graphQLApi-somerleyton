package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import javassist.NotFoundException;
import nl.ica.ise7.GraphQLApisomerleyton.models.Area;
import nl.ica.ise7.GraphQLApisomerleyton.models.Keeper;
import nl.ica.ise7.GraphQLApisomerleyton.models.Species;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.AreaRepository;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.KeeperRepository;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Query implements GraphQLQueryResolver {

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private KeeperRepository keeperRepository;

    @Autowired
    private AreaRepository areaRepository;

    public Iterable<Species> allSpecies() {
        return speciesRepository.findAll();
    }

    public Species speciesByName(String speciesName) throws NotFoundException {
        Species species = speciesRepository.findBySpeciesName(speciesName);
        if (species == null) {
            throw new NotFoundException("The species " + speciesName + " is not found.");
        }
        return species;
    }

    public Iterable<Keeper> allKeepers() {
        return keeperRepository.findAll();
    }

    public Keeper keeperByName(String name) throws NotFoundException {
        Keeper keeper = keeperRepository.findOne(name);
        if (keeper == null) {
            throw new NotFoundException("The keeper " + name + " is not found.");
        }
        return keeper;
    }

    public Iterable<Area> allAreas() {
        return areaRepository.findAll();
    }

    public Area areaByName(String name) throws NotFoundException {
        Area area = areaRepository.findOne(name);
        if (area == null) {
            throw new NotFoundException("The area " + name + " is not found.");
        }
        return area;
    }

    public Area areaByHeadKeeper(String keeperName) throws NotFoundException {
        Keeper keeper = keeperRepository.findOne(keeperName);
        if (keeper == null) {
            throw new NotFoundException("The keeper " + keeperName + " is not found.");
        }

        Area area = areaRepository.findByHeadKeeper(keeper);
        if (area == null) {
            throw new NotFoundException("The keeper " + keeper.getName() + " is not an head keeper.");
        }
        return area;
    }
}
