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

import vttp_project_backend.models.EventDetails;

@Repository
public class EventRepository {
    // private final String SQL_FIND_EVENTS = "select * from event_details";
    // private final String SQL_FIND_EVENT_BY_ID = "select * from event_details where event_details_id = ?";
    
    private final String C_EVENTS = "events";
    
    private final String A_EVENT_DETAILS_ID = "eventDetailsId";

    // @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private MongoTemplate mongoTemplate;

    public List<EventDetails> getEvents() {
        System.out.println("eventRepo getEvents() called");
        List<EventDetails> events = mongoTemplate.findAll(EventDetails.class,
         C_EVENTS);
        
        return events;
    }

    public List<EventDetails> search(String[] searchTerms) {
        System.out.println("eventRepo search() called");

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchTerms);
        TextQuery query = TextQuery.queryText(textCriteria);
        List<EventDetails> searchResults = mongoTemplate.find(query, EventDetails.class, 
            C_EVENTS);

        return searchResults;
    }

    public Optional<EventDetails> getEventById(String id) {
        EventDetails ev = new EventDetails();

        Criteria c = Criteria.where(A_EVENT_DETAILS_ID).is(id);
        Query query = Query.query(c);
        ev = mongoTemplate.findOne(query, EventDetails.class, C_EVENTS);

        if (ev == null) {
            return Optional.empty();
        }
        
        return Optional.of(ev);
    }


    // public List<EventDetails> getEvents() {
    //     List<EventDetails> events = new LinkedList<>();
    //     SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_EVENTS);

    //     while (rs.next()) {
    //         EventDetails e = new EventDetails();
    //         e.setEventDetailsId(rs.getString("event_details_id"));
    //         e.setUserDataId(rs.getString("user_data_id"));
    //         e.setTitle(rs.getString("title"));
    //         e.setDescription(rs.getString("description"));
    //         if (rs.getString("image_url") != null) {
    //             e.setImageUrl(rs.getString("image_url"));
    //         }
    //         e.setCategory(rs.getString("category"));

    //         events.add(e);
    //     }

    //     return events;
    // }

    // public Optional<EventDetails> getEventById(String id) {
    //     EventDetails ev = new EventDetails();
    //     try {
    //         ev = jdbcTemplate.queryForObject(SQL_FIND_EVENT_BY_ID, 
    //             BeanPropertyRowMapper.newInstance(EventDetails.class), id);
    //         return Optional.of(ev);

    //     } catch (EmptyResultDataAccessException e) {
    //         return Optional.empty();
    //     }
    // }
    
}
