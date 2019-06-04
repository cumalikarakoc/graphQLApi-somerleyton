package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import javassist.NotFoundException;
import nl.ica.ise7.GraphQLApisomerleyton.models.*;
import nl.ica.ise7.GraphQLApisomerleyton.models.compositeKeys.EnclosureIdentity;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

public class Mutation implements GraphQLMutationResolver {
    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private KeeperRepository keeperRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private EnclosureRepository enclosureRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private FoodKindRepository foodKindRepository;

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
        supplierRepository.updateSupplier(supplier.getName(), input.getName(), input.getPhone(), input.getAddress());
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

    public Enclosure newEnclosure(String areaName) {
        EnclosureIdentity enclosureIdentity = new EnclosureIdentity();
        enclosureIdentity.setAreaName(areaName);
        enclosureIdentity.setEnclosureNumber(enclosureRepository.getNextEnclosureNumber(areaName));

        Enclosure enclosure = new Enclosure();
        enclosure.setEnclosureIdentity(enclosureIdentity);

        return enclosureRepository.save(enclosure);
    }

    public boolean removeEnclosure(String areaName, int enclosureNumber) throws Exception {
        EnclosureIdentity enclosureIdentity = new EnclosureIdentity();
        enclosureIdentity.setAreaName(areaName);
        enclosureIdentity.setEnclosureNumber(enclosureNumber);

        Enclosure enclosure = enclosureRepository.findOne(enclosureIdentity);
        if (enclosure == null) {
            throw new NotFoundException("The enclosure to be removed is not found.");
        }
        try {
            enclosureRepository.delete(enclosure);
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());
        }
        return true;
    }

    public Animal newAnimal(Animal input) throws Exception {
        if (animalRepository.exists(input.getId())) {
            throw new Exception("The animal to be added already exists.");
        }
        Animal animal = new Animal();
        return animalRepository.save(mapInputToAnimalModel(animal, input));
    }

    public boolean removeAnimal(String id) throws Exception {
        if (animalRepository.findOne(id) == null) {
            throw new NotFoundException("The animal to be removed is not found.");
        }

        try {
            animalRepository.delete(id);
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());
        }

        return true;
    }

    public Animal updateAnimal(String id, Animal input) throws NotFoundException {
        Animal animal = animalRepository.findOne(id);
        if (animal == null) {
            throw new NotFoundException("The animal to be updated is not found.");
        }

        Animal mappedAnimal = mapInputToAnimalModel(animal, input);

        if (id.equals(input.getId())) {
            return animalRepository.save(mappedAnimal);
        }

        animalRepository.updateAnimalId(id, input.getId());
        return animalRepository.save(mappedAnimal);
    }

    private Animal mapInputToAnimalModel(Animal animal, Animal input) {
        animal.setId(input.getId());
        animal.setName(input.getName());
        animal.setBirthPlace(input.getBirthPlace());
        animal.setBirthDate(input.getBirthDate());
        animal.setGender(input.getGender());
        animal.setSpeciesName(input.getSpeciesName());
        return animal;
    }

    private FoodKind newFoodKind(FoodKind input) throws Exception {
        if(foodKindRepository.exists(input.getName())){
            throw new Exception("The foodType to be added does already exists");
        }
        FoodKind foodKind = new FoodKind();
        foodKind.setName(input.getName());
        return foodKind;
    }

    private FoodKind updateFoodKind(String name, String input) throws NotFoundException {
        FoodKind foodKind = foodKindRepository.findOne(name);
        if(foodKind == null){
            throw new NotFoundException("The food kind to be updated is not found.");
        }
        foodKindRepository.updateFoodKind(name, input);
        foodKind.setName(input);
        return foodKind;
    }

    private Boolean removeFoodKind(String name) throws NotFoundException {
        FoodKind foodKind = foodKindRepository.findOne(name);
        if(foodKind == null){
            throw new NotFoundException("The foodkind to be deleted does not exist.");
        }
        foodKindRepository.delete(foodKind);
        return true;
    }

}
