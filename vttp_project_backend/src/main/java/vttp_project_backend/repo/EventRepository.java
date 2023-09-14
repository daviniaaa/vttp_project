package vttp_project_backend.repo;

// import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

// import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

// import vttp_project_backend.models.EventDetails;
import vttp_project_backend.models.ExternalApi.DataObject;

@Repository
public class EventRepository {
    private final String C_EVENTS = "external_events";
    private final String A_EVENT_ID = "uuid";
    // @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private MongoTemplate mongoTemplate;

    public boolean addEvent(DataObject d) {
        DataObject o = mongoTemplate.insert(d, C_EVENTS);

        return o != null;
    }

    public Optional<DataObject> getEventById(String uuid) {

        Criteria c = Criteria.where(A_EVENT_ID).is(uuid);
        Query query = Query.query(c);
        DataObject d = mongoTemplate.findOne(query, DataObject.class, C_EVENTS);

        if (d == null) {
            return Optional.empty();
        }
        
        return Optional.of(d);
    }

    public List<DataObject> getEvents() {
        System.out.println("eventRepo getEvents() called");
        List<DataObject> events = mongoTemplate.findAll(DataObject.class, C_EVENTS);
        
        return events;
    }

    public List<DataObject> search(String[] searchTerms) {
        System.out.println("eventRepo search() called");

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchTerms);
        TextQuery query = TextQuery.queryText(textCriteria);
        List<DataObject> searchResults = mongoTemplate.find(query, DataObject.class, 
            C_EVENTS);

        return searchResults;
    }

}
