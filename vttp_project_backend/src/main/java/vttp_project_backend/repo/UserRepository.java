package vttp_project_backend.repo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp_project_backend.models.UserData;
import vttp_project_backend.models.UserSettings;

@Repository
public class UserRepository {
    private final String SQL_FIND_SETTINGS = "select * from user_settings where user_data_id = ?";
    private final String SQL_FIND_USER_BY_ID = "select * from user_data where user_data_id = ?";
    private final String SQL_FIND_USER_BY_EMAIL = "select * from user_data where email = ?";
    private final String SQL_INSERT_USER = "insert into user_data (user_data_id, email, display_name, user_password, image_url) values (?,?,?,?,?)";
    private final String SQL_INSERT_USER_NO_IMAGE = "insert into user_data (user_data_id, email, display_name, user_password) values (?,?,?,?)";
    private final String SQL_UPDATE_USER_DATA = "update user_data set image_url = ? where user_data_id = ?";
    private final String SQL_LOGIN = "select * from user_data where (email, user_password) = (?, ?)";
    private final String SQL_INSERT_USER_SETTINGS = "insert into user_settings(user_settings_id, user_data_id) values (?,?)";
    private final String SQL_UPDATE_USER_SETTINGS = "update user_settings set email_notif = ?, tele_notif = ? where user_settings_id = ?";
    private final String SQL_DELETE_USER_SETTINGS = "delete from user_settings where user_settings_id = ?";
    private final String SQL_DELETE_USER = "delete from user_data where user_data_id = ?";

    @Autowired private JdbcTemplate template;

    public Optional<UserData> findUserById(String id) {
        UserData u = new UserData();

        try {
            u = template.queryForObject(SQL_FIND_USER_BY_ID, 
                BeanPropertyRowMapper.newInstance(UserData.class), id);
            return Optional.of(u);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }        
    }

    public Optional<UserData> findUserByEmail(String email) {
        UserData u = new UserData();

        try {
            u = template.queryForObject(SQL_FIND_USER_BY_EMAIL, 
                BeanPropertyRowMapper.newInstance(UserData.class), email);
            return Optional.of(u);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }        
    }

    public boolean createUser(UserData u) {
        
        int added = 0;

        if (u.getImageUrl() == null) {
            added = template.update(SQL_INSERT_USER_NO_IMAGE, 
                u.getUserDataId(), u.getEmail(), u.getDisplayName(), u.getUserPassword());
        } else {
            added = template.update(SQL_INSERT_USER, 
                u.getUserDataId(), u.getEmail(), u.getDisplayName(), u.getUserPassword(), u.getImageUrl());
        }
        
        return added > 0;
    }

    public boolean createUserSettings(String id) {
        
        int added = template.update(SQL_INSERT_USER_SETTINGS, id, id);

        return added > 0;
    }

    public Optional<UserData> login(String email, String password) {
        UserData u = new UserData();

        try {
            u = template.queryForObject(SQL_LOGIN, 
                BeanPropertyRowMapper.newInstance(UserData.class), email, password);
            return Optional.of(u);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }  
    }

    public Optional<UserSettings> getUserSettings(String id) {
        UserSettings u = new UserSettings();

        try {
            u = template.queryForObject(SQL_FIND_SETTINGS, 
                BeanPropertyRowMapper.newInstance(UserSettings.class), id);
            return Optional.of(u);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }  
    }

    public boolean updateUserSettings(UserSettings u) {
        int added = template.update(SQL_UPDATE_USER_SETTINGS, u.isEmailNotif(), u.isTeleNotif(), 
            u.getUserSettingsId());
        return added > 0;
    }

    public boolean updateUserData(String url, String id) {
        int added = template.update(SQL_UPDATE_USER_DATA, url, id);
        return added > 0;
    }

    public boolean deleteUser(String id){
        int added = template.update(SQL_DELETE_USER, id);
        return added > 0;
    }

    public boolean deleteUserSettings(String id){
        int added = template.update(SQL_DELETE_USER_SETTINGS, id);
        return added > 0;
    }

}
