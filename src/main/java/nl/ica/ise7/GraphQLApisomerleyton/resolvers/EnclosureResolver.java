package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.GraphQLApisomerleyton.models.Animal;
import nl.ica.ise7.GraphQLApisomerleyton.models.Enclosure;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.AnimalRepository;

public class EnclosureResolver implements GraphQLResolver<Enclosure> {
    private AnimalRepository animalRepository;

    public EnclosureResolver(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    public Iterable<Animal> getAnimals(Enclosure enclosure){
        return animalRepository.findAnimalsByEnclosure(enclosure.getEnclosureNumber());
    }
}
