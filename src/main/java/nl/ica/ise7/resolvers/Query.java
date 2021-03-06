package nl.ica.ise7.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import javassist.NotFoundException;
import nl.ica.ise7.models.*;
import nl.ica.ise7.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

public class Query implements GraphQLQueryResolver {

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

    public Iterable<Supplier> allSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier supplierByName(String supplierName) throws NotFoundException {
        Supplier supplier = supplierRepository.findOne(supplierName);
        if (supplier == null) {
            throw new NotFoundException("The supplier " + supplierName + " is not found.");
        }
        return supplier;
    }

    public Iterable<Enclosure> allEnclosures() {
        return enclosureRepository.findAll();
    }

    public Iterable<Enclosure> allEnclosuresByArea(String areaName) {
        return enclosureRepository.findAllByEnclosureIdentity_AreaName(areaName);
    }

    public Iterable<Animal> allAnimals() {
        return animalRepository.findAll();
    }

    public Animal animalById(String id) throws NotFoundException {
        Animal animal = animalRepository.findOne(id);
        if (animal == null) {
            throw new NotFoundException("The animal " + id + " is not found.");
        }
        return animal;
    }

    public Iterable<Exchange> allExchanges() {
        return exchangeRepository.findAll();
    }

    public Iterable<Exchange> exchangesByAnimal(String animalId) {
        return exchangeRepository.findExchangesByAnimalId(animalId);
    }

    public Iterable<AnimalEnclosure> enclosuresByAnimalId(String animalId) {
        return animalEnclosureRepository.findEnclosuresByAnimalId(animalId);
    }

    public Iterable<AnimalEnclosure> animalsByEnclosure(String areaName, int enclosureNumber) {
        return animalEnclosureRepository.findAnimalEnclosuresByAreaNameAndEnclosureNumber(areaName, enclosureNumber);
    }

    public Iterable<FoodKind> allFoodKinds(){
        return foodKindRepository.findAll();
    }

    public FoodKind foodKindByName(String id) throws NotFoundException {
        FoodKind foodKind = foodKindRepository.findOne(id);
        if(foodKind == null){
            throw new NotFoundException("The foodkind " + id + " is not found.");
        }
        return foodKind;
    }

    public Iterable<FoodKind> foodKindBySupplier(String supplierName) throws NotFoundException {
        Supplier supplier = supplierRepository.findOne(supplierName);
        if(supplier == null){
            throw new NotFoundException("No supplier found called "+ supplierName);
        }
        return supplier.getFoodKinds();
    }

    public Iterable<Supplier> suppliersByFoodkind(String foodKindName) throws NotFoundException {
        FoodKind foodKind = foodKindRepository.findOne(foodKindName);
        if(foodKind == null){
            throw new NotFoundException("No foodkind found called "+ foodKindName);
        }
        return foodKind.getSuppliers();
    }

}
