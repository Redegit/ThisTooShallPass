package org.server.api;


import org.server.entity.User;
import org.server.repository.UserRepository;
import org.server.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest-контроллер для работы с пользователями.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final JdbcTemplate jdbcTemplate;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    /**
     * Конструктор класса, в котором устанавливаются все необходимые зависимости.
     *
     * @param jdbcTemplate   объект для выполнения запросов к базе данных
     * @param jwtService     объект для работы с JWT-токенами
     * @param userRepository репозиторий для работы с темами
     */
    public UserController(JdbcTemplate jdbcTemplate, JwtService jwtService,
                          UserRepository userRepository) {

        this.jdbcTemplate = jdbcTemplate;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    /**
     * Получение всех тем
     *
     * @return Список всех тем
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    /**
     * Получение пользователя по идентификатору
     *
     * @param id Идентификатор пользователя
     * @return Объект ResponseEntity с найденной записью на прием к врачу или ошибкой 404, если запись не найдена
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


//    /**
//     * Получение среза записей на прием с сортировкой и пагинацией.
//     *
//     * @param pageIndex     номер страницы
//     * @param pageSize      размер страницы
//     * @param sortDirection направление сортировки
//     * @param sortBy        поле для сортировки
//     * @return срез записей на прием с сортировкой и пагинацией
//     */
//    @PostMapping("/slice")
//    public Map<String, Object> getPatientsSlice(
//            @RequestParam int pageIndex,
//            @RequestParam int pageSize,
//            @RequestParam Sort.Direction sortDirection,
//            @RequestParam String sortBy
//    ) {
//
//        Page<User> page;
//        page = userRepository.findAll(PageRequest.of(pageIndex, pageSize, sortDirection, sortBy));
//
//        int totalPages = page.getTotalPages();
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("data", page);
//        result.put("totalPages", totalPages);
//        return result;
//    }

    /**
     * Создание нового пользователя
     *
     * @param user объект пользователя
     */
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {

        return userRepository.save(user);
    }

    /**
     * Перезаписывание объекта пользователя
     *
     * @param user новый объект пользователя
     * @return Объект ResponseEntity с статусом ok или badRequest
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Обновление пользователя по id
     *
     * @param id   id пользователя
     * @param user обновленный объект пользователя
     * @return Объект ResponseEntity со статусом ok и обновленным объектом или notFound, если объекта с данным id не найдено
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            user.setId((long) id);
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаление пользователя
     *
     * @param id id пользователя
     * @return Пустой объект ResponseEntity или со статусом notFound, если записи с таким id не существует
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
