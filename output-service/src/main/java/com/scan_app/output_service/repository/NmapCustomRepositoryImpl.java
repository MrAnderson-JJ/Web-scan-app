package com.scan_app.output_service.repository;

import com.scan_app.output_service.entity.Nmaprun;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class NmapCustomRepositoryImpl implements NmapCustomRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Nmaprun> filterScans(List<String> scanIds, Integer port, Integer maxDistance) {

        List<ObjectId> objectIds = scanIds.stream()
                .filter(ObjectId::isValid)
                .map(ObjectId::new)
                .collect(Collectors.toList());

        if (objectIds.isEmpty()) {
            return Collections.emptyList();
        }

        Criteria criteria = Criteria.where("_id").in(objectIds);

        if (port != null) {
            criteria = criteria.and("host.ports.port").elemMatch(
                    Criteria.where("portid").is(port)
                            .and("state.state").is("open")
            );
        }

        if (maxDistance != null) {
            criteria = criteria.and("host.distance.value").lt(maxDistance + 1);
        }

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Nmaprun.class);
    }
}
