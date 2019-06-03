package nl.ica.ise7.GraphQLApisomerleyton.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.ica.ise7.GraphQLApisomerleyton.models.Area;
import nl.ica.ise7.GraphQLApisomerleyton.models.Keeper;
import nl.ica.ise7.GraphQLApisomerleyton.repositories.KeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AreaResolver implements GraphQLResolver {
    @Autowired
    private KeeperRepository keeperRepository;

    public Keeper getHeadKeeper(Area area){
        return keeperRepository.findOne(area.getHeadKeeper().getName());
    }
}
