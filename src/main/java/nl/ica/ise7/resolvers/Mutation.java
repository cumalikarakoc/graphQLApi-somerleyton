package nl.ica.ise7.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import javassist.NotFoundException;
import nl.ica.ise7.models.*;
import nl.ica.ise7.models.compositeKeys.*;
import nl.ica.ise7.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.Set;

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
    private ExchangeRepository exchangeRepository;

    @Autowired
    private FoodKindRepository foodKindRepository;

    @Autowired
    private AnimalEnclosureRepository animalEnclosureRepository;

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

    public Exchange newExchange(Exchange input) throws Exception {
        input.setIsNew(true);
        try {
            return exchangeRepository.save(input);
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());
        }
    }

    public Exchange updateExchange(ExchangeIdentity identity, Exchange input) throws Exception {
        if (exchangeRepository.findOne(identity) == null) {
            throw new NotFoundException("Exchange to be updated is not found.");
        }

        try {
            exchangeRepository.updateExchangeIdentity(
                    identity.getAnimalId(), identity.getExchangeDate(),
                    input.getIdentity().getAnimalId(), input.getIdentity().getExchangeDate()
            );
            input.setIsNew(false);
            return exchangeRepository.save(input);
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());

        }
    }

    public boolean removeExchange(ExchangeIdentity identity) throws Exception {
        if (exchangeRepository.findOne(identity) == null) {
            throw new NotFoundException("Exchange to be removed is not found.");
        }

        try {
            exchangeRepository.delete(identity);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());
        }
    }

    public AnimalEnclosure placeAnimalInEnclosure(String animalId, String areaName, int enclosureNumber) throws Exception {
        Animal animal = animalRepository.findOne(animalId);
        if (animal == null) {
            throw new Exception("The animal " + animalId + " is not found.");
        }

        EnclosureIdentity enclosureIdentity = new EnclosureIdentity();
        enclosureIdentity.setAreaName(areaName);
        enclosureIdentity.setEnclosureNumber(enclosureNumber);
        Enclosure enclosure = enclosureRepository.findOne(enclosureIdentity);

        if (enclosure == null) {
            throw new Exception("The enclosure " + areaName + "-" + enclosureNumber + " is not found.");
        }

        AnimalEnclosure animalEnclosure = new AnimalEnclosure();
        animalEnclosure.setIdentity(new AnimalEnclosureIdentity(animalId, new Date()));
        animalEnclosure.setAreaName(areaName);
        animalEnclosure.setEnclosureNumber(enclosureNumber);
        try {
            return animalEnclosureRepository.save(animalEnclosure);
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());
        }
    }

    public Iterable<FoodKind> newFoodKind(FoodKind input) throws Exception {
        if (foodKindRepository.exists(input.getName())) {
            throw new Exception("The foodType to be added does already exists");
        }
        FoodKind foodKind = new FoodKind();
        foodKind.setName(input.getName());
        try {
            foodKindRepository.save(foodKind);
        } catch (Exception e) {
            throw new Exception(e.getCause().getCause().getLocalizedMessage());
        }
        return foodKindRepository.findAll();
    }

    public FoodKind updateFoodKind(String name, String input) throws NotFoundException {
        FoodKind foodKind = foodKindRepository.findOne(name);
        if (foodKind == null) {
            throw new NotFoundException("The food kind to be updated is not found.");
        }
        foodKindRepository.updateFoodKind(name, input);
        foodKind.setName(input);
        return foodKind;
    }

    public Boolean removeFoodKind(String name) throws NotFoundException {
        FoodKind foodKind = foodKindRepository.findOne(name);
        if (foodKind == null) {
            throw new NotFoundException("The foodkind to be deleted does not exist.");
        }
        foodKindRepository.delete(foodKind);
        return true;
    }

    public Supplier addFoodKindToSupplier(String foodType, String supplierName) throws Exception {
        FoodKind foodKind = foodKindRepository.findOne(foodType);
        if (foodKind == null) {
            throw new NotFoundException("The foodkind to be added to an supplier does not exist.");
        }
        Supplier supplier = supplierRepository.findOne(supplierName);
        if (supplier == null) {
            throw new NotFoundException("The suppplier does not exists");
        }
        Set<FoodKind> foodKinds = supplier.getFoodKinds();
        if (foodKinds.contains(foodKind)) {
            throw new Exception("This supplier already supplies this foodkind");
        }
        foodKinds.add(foodKind);
        supplier.setFoodKinds(foodKinds);
        foodKind.getSuppliers().add(supplier);
        foodKindRepository.save(foodKind);
        supplierRepository.save(supplier);
        return supplier;
    }


}
