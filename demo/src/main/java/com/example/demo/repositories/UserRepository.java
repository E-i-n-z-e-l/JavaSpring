package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Аннотация @Repository указывает, что класс "UserRepository" является репозиторием,
который обеспечивает доступ к данным. */
@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Этот метод возвращает список всех пользователей. Он создает SQL-запрос, который выбирает все записи
     * из таблицы "userTable".<p></p>
     * Затем используется RowMapper, который преобразует каждую строку результата запроса в объект класса "User"
     * с помощью методов setId, setFirstName и setLastName.<p></p>
     * Наконец, метод jdbc.query(sql, userRowMapper) выполняет запрос и возвращает список пользователей.
     * @return
     */
    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    /**
     * Этот метод сохраняет нового пользователя в базу данных. Он создает SQL-запрос, который вставляет новую запись
     * в таблицу "userTable" с использованием данных из объекта User.<p></p>
     * Затем метод jdbc.update(sql, user.getFirstName(), user.getLastName()) выполняет запрос на обновление базы данных.
     * @param user
     * @return
     */
    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName, lastName) VALUES (?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return  user;
    }

    /**
     * Этот метод выполняет операцию удаления записи пользователя из базы данных по заданному
     * идентификатору (ID). Он создает SQL-запрос, который удаляет запись из таблицы "userTable"
     * соответствующую переданному идентификатору.
     * @param id
     */
    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id = ?";
        jdbc.update(sql, id);
    }

    /**
     * updateUser(User user) - это метод, который выполняет SQL-запрос для обновления
     * данных пользователя в базе данных.
     * <p></p>
     * Используем SQL-запрос с параметрами (?) и метод jdbc.update(), чтобы выполнить обновление. Передаем значения
     * из объекта User (имя, фамилия, идентификатор) в качестве параметров.
     * @param user
     */
    public void updateUser(User user) {
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

    /**
     * findById(int id) - это метод, который выполняет SQL-запрос для выборки пользователя по его идентификатору.
     * <p></p>
     * Используем SQL-запрос с параметром (?) и метод jdbc.queryForObject().
     * <p></p>
     * Определяем объект RowMapper<User>, который преобразует(маппит) результаты запроса на объект User.
     * <p></p>
     * Этот метод возвращает объект User с заполненными значениями из результата запроса.
     * @param id
     * @return
     */
    public User findById(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        RowMapper<User> userRowMapper = (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            return user;
        };

        return jdbc.queryForObject(sql, userRowMapper, id);
    }
}
