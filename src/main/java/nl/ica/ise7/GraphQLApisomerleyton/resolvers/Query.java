package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import nl.ica.ise7.GraphQLApisomerleyton.exceptions.AnimalNotFoundException;
import nl.ica.ise7.GraphQLApisomerleyton.models.Animal;
import nl.ica.ise7.GraphQLApisomerleyton.models.Enclosure;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.EnclosureRepository;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.AnimalRepository;

public class Query implements GraphQLQueryResolver {
    private AnimalRepository animalRepository;
    private EnclosureRepository enclosureRepository;

    public Query(EnclosureRepository enclosureRepository, AnimalRepository animalRepository) {
        this.enclosureRepository = enclosureRepository;
        this.animalRepository = animalRepository;
    }

    public Iterable<Animal> allAnimals() {
        return animalRepository.findAll();
    }

    public Animal animalById(Long id){
        Animal animal = animalRepository.findOne(id);
        if(animal == null){
            throw new AnimalNotFoundException("The animal was not found", id);
        }
        return animalRepository.findOne(id);
    }

    public Iterable<Enclosure> allEnclosures() {
        return enclosureRepository.findAll();
    }


}
