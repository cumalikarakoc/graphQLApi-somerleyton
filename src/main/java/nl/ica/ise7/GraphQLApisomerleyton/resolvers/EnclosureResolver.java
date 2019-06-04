package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.GraphQLApisomerleyton.models.Area;
import nl.ica.ise7.GraphQLApisomerleyton.models.Enclosure;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EnclosureResolver implements GraphQLResolver<Enclosure> {
    @Autowired
    private AreaRepository areaRepository;

    public Area getArea(Enclosure enclosure){
        return areaRepository.findOne(enclosure.getEnclosureIdentity().getAreaName());
    }

    public int getEnclosureNumber(Enclosure enclosure){
        return enclosure.getEnclosureIdentity().getEnclosureNumber();
    }
}
