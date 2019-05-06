package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.GraphQLApisomerleyton.models.Animal;
import nl.ica.ise7.GraphQLApisomerleyton.models.Enclosure;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.EnclosureRepository;

public class AnimalResolver implements GraphQLResolver<Animal> {
    private EnclosureRepository enclosureRepository;

    public AnimalResolver(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    public Iterable<Enclosure> getEnclosures(Animal animal) {
        return null;
    }
}
