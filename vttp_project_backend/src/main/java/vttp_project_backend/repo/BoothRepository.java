package vttp_project_backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp_project_backend.models.BoothDetails;

@Repository
public class BoothRepository {
    private final String SQL_FIND_BOOTH_BY_EVENT = "select * from booth_details where event_details_id = ?";
    private final String SQL_FIND_BOOTH_BY_USER = "select * from booth_details where user_data_id = ?";
    private final String SQL_FIND_BOOTH_BY_ID = "select * from booth_details where booth_id = ?";
    private final String SQL_FIND_BOOTH_BY_NAME = "select * from booth_details where booth_name = ?";
    private final String SQL_INSERT_BOOTH = "insert into booth_details (booth_id, user_data_id, event_details_id, booth_name, description) values (?,?,?,?,?)";
    
    @Autowired JdbcTemplate template;

    public Optional<BoothDetails> findBoothById(String id) {
        BoothDetails b = new BoothDetails();

        try {
            b = template.queryForObject(SQL_FIND_BOOTH_BY_ID, 
                BeanPropertyRowMapper.newInstance(BoothDetails.class), id);
            return Optional.of(b);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<BoothDetails> findBoothByName(String name) {
        BoothDetails b = new BoothDetails();

        try {
            b = template.queryForObject(SQL_FIND_BOOTH_BY_NAME, 
                BeanPropertyRowMapper.newInstance(BoothDetails.class), name);
            return Optional.of(b);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public boolean createBooth(BoothDetails b) {
        int added = template.update(SQL_INSERT_BOOTH, b.getBoothId(), b.getUserDataId(), 
            b.getEventDetailsId(), b.getBoothName(), b.getDescription());

        return added > 0;
    }

    public List<BoothDetails> getBooths(String id) {
        List<BoothDetails> boothList = template.query(SQL_FIND_BOOTH_BY_EVENT, BeanPropertyRowMapper
            .newInstance(BoothDetails.class), id);

        return boothList;
    }

    public List<BoothDetails> findBoothsByUser(String id) {
        List<BoothDetails> boothList = template.query(SQL_FIND_BOOTH_BY_USER, BeanPropertyRowMapper
            .newInstance(BoothDetails.class), id);

            return boothList;
    }


}
