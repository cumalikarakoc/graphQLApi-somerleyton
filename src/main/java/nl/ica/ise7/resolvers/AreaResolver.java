package nl.ica.ise7.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.models.Area;
import nl.ica.ise7.models.Enclosure;
import nl.ica.ise7.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaResolver implements GraphQLResolver<Area> {
    @Autowired
    private EnclosureRepository enclosureRepository;

    public List<Enclosure> getEnclosures(Area area) {
        return enclosureRepository.findAllByEnclosureIdentity_AreaName(area.getName());
    }
}
