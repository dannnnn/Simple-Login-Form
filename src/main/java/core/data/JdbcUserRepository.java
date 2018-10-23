package core.data;

import core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(User user){
        jdbcTemplate.update(
                "INSERT INTO users (username, password, first_name, last_name)" +
                        " VALUES (?, ?, ?, ?)",
                user.getUsername(),
                user.getPassword(),
                user.getFirst_name(),
                user.getLast_name());
        return user;
    }

    public User findByUsername(String username){
        return jdbcTemplate.queryForObject("select username, password, first_name, last_name from users where username=?",
                new UserRowMapper(), username);
    }

    public User validateUser(User user){
        String sql = "select * from users where username='" + user.getUsername() + "'";

        List<User> users = jdbcTemplate.query(sql, new UserRowMapper());
        return users.size()> 0 ? users.get(0) : null;
    }

    private static class UserRowMapper implements RowMapper<User>{
        public User mapRow(ResultSet rs, int rowNum) throws SQLException{
            return new User(
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("first_name"),
            rs.getString("last_name"));
        }
    }
}
