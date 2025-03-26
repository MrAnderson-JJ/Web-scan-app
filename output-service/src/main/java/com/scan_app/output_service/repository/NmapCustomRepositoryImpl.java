package com.scan_app.output_service.repository;

import com.scan_app.output_service.entity.Nmaprun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NmapCustomRepositoryImpl implements NmapCustomRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Nmaprun> filterScans(List<String> scanIds, Integer port, Integer maxDistance) {
        System.out.println("method");
        Criteria criteria = new Criteria();

        criteria = criteria.and("_id").in(scanIds);

        if (port != null) {
            criteria = criteria.and("host.ports.port").elemMatch(
                    Criteria.where("portid").is(port)
                            .and("state.state").is("open")
            );
        }

/*        if (maxDistance != null) {
            criteria = criteria.and("host.trace.hop").elemMatch(
                    Criteria.where("ttl").lte(maxDistance)
            );
        }*/

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Nmaprun.class);
    }
}
