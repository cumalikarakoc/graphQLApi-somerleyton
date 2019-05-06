package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import nl.ica.ise7.GraphQLApisomerleyton.exceptions.AnimalNotFoundException;
import nl.ica.ise7.GraphQLApisomerleyton.models.Animal;
import nl.ica.ise7.GraphQLApisomerleyton.models.Enclosure;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.EnclosureRepository;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.AnimalRepository;

public class Mutation implements GraphQLMutationResolver {
    private AnimalRepository animalRepository;
    private EnclosureRepository enclosureRepository;

    public Mutation(EnclosureRepository enclosureRepository, AnimalRepository animalRepository) {
        this.enclosureRepository = enclosureRepository;
        this.animalRepository = animalRepository;
    }

    public Animal newAnimal(String animal_name ) {
        Animal animal = new Animal();
        animal.setName(animal_name);

        animalRepository.save(animal);

        return animal;
    }

    public Enclosure newEnclosure(){
        Enclosure enclosure = new Enclosure();
        enclosureRepository.save(enclosure);
        return enclosure;
    }

    public boolean deleteAnimal(Long id) {
        Animal animal = animalRepository.findOne(id);
        if(animal == null) {
            throw new AnimalNotFoundException("The animal to be updated was not found", id);
        }
        animalRepository.delete(animal);
        return true;
    }
}
