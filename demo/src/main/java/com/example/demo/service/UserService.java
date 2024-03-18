package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* Аннотация @Service указывает, что класс "UserService" является службой (сервисом), который
предоставляет определенные функции или операции. */
@Service
public class UserService {
    private final UserRepository userRepository;

    /* @Autowired - аннотация, которая автоматически связывает экземпляр UserRepository с параметром конструктора. */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Этот метод возвращает список всех пользователей в виде объекта List<User>. Он вызывает findAll() из объекта
     * userRepository, который возвращает все записи из базы данных.
     * @return
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * Этот метод сохраняет нового пользователя в базу данных. Он принимает объект User в качестве
     * параметра и вызывает save(user) у объекта userRepository.
     * Возвращается сохраненный пользователь (объект User).
     * @param user
     * @return
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * Этот метод в службе UserService вызывает метод deleteById из репозитория UserRepository
     * для удаления записи пользователя. Он передает переданный идентификатор (ID) репозиторию
     * для выполнения фактического удаления записи из базы данных.
     * @param id
     */
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
