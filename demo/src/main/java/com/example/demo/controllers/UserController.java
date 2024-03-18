package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/* Аннотация @Controller указывает, что класс является контроллером, который обрабатывает запросы от клиента. */
@Controller
public class UserController {
    private final UserService userService;

    /*  @Autowired - это аннотация, которая автоматически связывает экземпляр UserService
    с параметром конструктора. */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Этот метод обрабатывает GET-запрос на путь "/users". Он вызывает метод findAll() из объекта userService,
     * который возвращает список пользователей.<p></p>
     * Затем список пользователей добавляется в модель (model.addAttribute("users", users)), чтобы передать
     * его в представление.<p></p>
     * Наконец, метод возвращает имя представления "user-list", которое будет отображено пользователю.
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();


        model.addAttribute("users", users);
        return "user-list";
    }

    /**
     * Этот метод обрабатывает GET-запрос на путь "/user-create". <p></p>
     * Он принимает параметр User user, но этот параметр обычно не используется в данном контексте.<p></p>
     * Метод просто возвращает имя представления "user-create", которое будет отображено пользователю.<p></p>
     * @param user
     * @return
     */
    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    /**
     * Этот метод обрабатывает POST-запрос на путь "/user-create". Он принимает параметр User user,
     * который представляет нового пользователя.<p></p>
     * Метод вызывает saveUser(user) из объекта userService для сохранения пользователя.<p></p>
     * Затем метод перенаправляет пользователя на путь "/users".
     * @param user
     * @return
     */
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Этот метод в контроллере UserController обрабатывает GET-запрос на пути "/user-delete/{id}",
     * где "{id}" - это путь переменной, содержащей идентификатор (ID) пользователя. Переменная id
     * получается из пути с помощью аннотации @PathVariable. В методе вызывается метод deleteById из
     * службы UserService для удаления пользователя по указанному идентификатору. После успешного
     * удаления записи, метод возвращает перенаправление на путь "/users", где пользователь будет
     * перенаправлен после удаления.
     * @param id
     * @return
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * updateUserForm() - это GET-метод, который обрабатывает запрос на путь "/user-update/{id}".
     * <p></p>
     * Он принимает значение идентификатора пользователя из пути и модель Model в качестве параметров.
     * <p></p>
     * Метод userService.findById(id) вызывает метод findById(id) из userService для получения объекта User по
     * его идентификатору. Затем метод добавляет этот объект User в модель с помощью model.addAttribute(),
     * чтобы он был доступен при отображении представления. Наконец, метод возвращает имя представления "user-update",
     * которое содержит форму для обновления данных пользователя.
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    /**
     * updateUser() - это POST-метод, который обрабатывает запрос на путь "/user-update".
     * <p></p>
     * Он принимает объект User из формы с помощью аннотации @ModelAttribute.
     * <p></p>
     * Метод userService.updateUser(user) вызывает метод updateUser(user) из userService, чтобы обновить
     * данные пользователя в базе данных.
     * <p></p>
     * После успешного обновления данных, метод выполняет перенаправление на путь "/users" с помощью "redirect:/users".
     * Перенаправление позволяет отобразить список пользователей после обновления данных.
     * @param user
     * @return
     */
    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}
