package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import javassist.NotFoundException;
import nl.ica.ise7.GraphQLApisomerleyton.models.Area;
import nl.ica.ise7.GraphQLApisomerleyton.models.Keeper;
import nl.ica.ise7.GraphQLApisomerleyton.models.Species;
import nl.ica.ise7.GraphQLApisomerleyton.models.Supplier;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.AreaRepository;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.KeeperRepository;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.SpeciesRepository;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Mutation implements GraphQLMutationResolver {
    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private KeeperRepository keeperRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    SupplierRepository supplierRepository;

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

    public Boolean removeKeeper(String name) throws Exception {
        Keeper keeper = keeperRepository.findOne(name);
        if (keeper == null) {
            throw new NotFoundException("The keeper to be removed is not found.");
        }
        try {
            keeperRepository.delete(keeper);
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());
        }
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

    public Area newArea(String name, String headkeeper) throws Exception {
        if (areaRepository.exists(name)) {
            throw new Exception("The area to be added already exists.");
        }

        Keeper keeper = keeperRepository.findOne(headkeeper);
        if (keeper == null) {
            throw new NotFoundException("The keeper " + headkeeper + " doesn't exist.");
        }

        Area area = new Area();
        area.setName(name);
        area.setHeadKeeper(keeper);
        return areaRepository.save(area);
    }

    public Boolean removeArea(String name) throws Exception {
        Area area = areaRepository.findOne(name);
        if (area == null) {
            throw new NotFoundException("The area to be removed is not found.");
        }
        try {
            areaRepository.delete(area.getName());
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());
        }
        return true;
    }

    public Area updateArea(String name, Area input) throws NotFoundException {
        Area area = areaRepository.findOne(name);
        if (area == null) {
            throw new NotFoundException("The area to be updated is not found.");
        }

        areaRepository.updateArea(area.getName(), input.getName(), input.getHeadKeeper().getName());

        return areaRepository.findOne(input.getName());
    }

    public Supplier newSupplier(Supplier input) throws Exception {
        if (supplierRepository.exists(input.getName())) {
            throw new Exception("Supplier to be added already exists.");
        }
        Supplier supplier = new Supplier();
        supplier.setName(input.getName());
        supplier.setPhone(input.getPhone());
        supplier.setAddress(input.getAddress());
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(String name, Supplier input) throws NotFoundException {
        Supplier supplier = supplierRepository.findOne(name);
        if (supplier == null) {
            throw new NotFoundException("The supplier to be updated is not found.");
        }
        supplierRepository.updateSupplier(supplier.getName(),input.getName() , input.getPhone(), input.getAddress());
        return supplierRepository.findOne(input.getName());
    }

    public Boolean removeSupplier(String name) throws Exception {
        if (!supplierRepository.exists(name)) {
            throw new NotFoundException("The supplier to be deleted is not found.");
        }
        try {
            supplierRepository.delete(name);
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());
        }
        return true;
    }
}
