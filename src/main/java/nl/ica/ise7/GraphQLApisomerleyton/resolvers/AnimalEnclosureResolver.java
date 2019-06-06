package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.GraphQLApisomerleyton.models.Animal;
import nl.ica.ise7.GraphQLApisomerleyton.models.AnimalEnclosure;
import nl.ica.ise7.GraphQLApisomerleyton.models.Enclosure;
import nl.ica.ise7.GraphQLApisomerleyton.models.compositeKeys.EnclosureIdentity;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.AnimalRepository;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

public class AnimalEnclosureResolver implements GraphQLResolver<AnimalEnclosure> {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private EnclosureRepository enclosureRepository;

    public Animal getAnimal(AnimalEnclosure animalEnclosure){
        return animalRepository.findOne(animalEnclosure.getIdentity().getAnimalId());
    }

    public Enclosure getEnclosure(AnimalEnclosure animalEnclosure){
        EnclosureIdentity enclosureIdentity = new EnclosureIdentity();
        enclosureIdentity.setAreaName(animalEnclosure.getAreaName());
        enclosureIdentity.setEnclosureNumber(animalEnclosure.getEnclosureNumber());
        return enclosureRepository.findOne(enclosureIdentity);
    }

    public Date getSince(AnimalEnclosure animalEnclosure){
        return animalEnclosure.getIdentity().getSince();
    }
}
