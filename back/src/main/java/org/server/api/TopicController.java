package org.server.api;


import org.server.entity.Topic;
import org.server.repository.TopicRepository;
import org.server.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest-контроллер для работы с темами обсуждений.
 */
@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final JdbcTemplate jdbcTemplate;
    private final JwtService jwtService;
    private final TopicRepository topicRepository;

    /**
     * Конструктор класса, в котором устанавливаются все необходимые зависимости.
     *
     * @param jdbcTemplate    объект для выполнения запросов к базе данных
     * @param jwtService      объект для работы с JWT-токенами
     * @param topicRepository репозиторий для работы с темами
     */
    public TopicController(JdbcTemplate jdbcTemplate, JwtService jwtService,
                           TopicRepository topicRepository) {

        this.jdbcTemplate = jdbcTemplate;
        this.jwtService = jwtService;
        this.topicRepository = topicRepository;
    }

    /**
     * Получение всех тем
     *
     * @return Список всех тем
     */
    @GetMapping
    public List<Topic> getAllRawTopics() {
        return topicRepository.findAll();
    }

    /**
     * Получение темы по идентификатору
     *
     * @param id Идентификатор темы
     * @return Объект ResponseEntity с найденной записью на прием к врачу или ошибкой 404, если запись не найдена
     */
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable int id) {
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
//        Page<Topic> page;
//        page = topicRepository.findAll(PageRequest.of(pageIndex, pageSize, sortDirection, sortBy));
//
//        int totalPages = page.getTotalPages();
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("data", page);
//        result.put("totalPages", totalPages);
//        return result;
//    }

    /**
     * Создание новой темы
     *
     * @param topic объект темы
     */
    @PostMapping("/create")
    public Topic createTopic(@RequestBody Topic topic) {

        return topicRepository.save(topic);
    }

    /**
     * Перезаписывание объекта темы
     *
     * @param topic новый объект темы
     * @return Объект ResponseEntity с статусом ok или badRequest
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateTopic(@RequestBody Topic topic) {
        try {
            topicRepository.save(topic);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Обновление темы по id
     *
     * @param id    id темы
     * @param topic обновленный объект темы
     * @return Объект ResponseEntity со статусом ok и обновленным объектом или notFound, если объекта с данным id не найдено
     */
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable int id, @RequestBody Topic topic) {
        Optional<Topic> existingTopic = topicRepository.findById(id);
        if (existingTopic.isPresent()) {
            topic.setId((long) id);
            topicRepository.save(topic);
            return ResponseEntity.ok(topic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаление темы
     *
     * @param id id темы
     * @return Пустой объект ResponseEntity или со статусом notFound, если записи с таким id не существует
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable int id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent()) {
            topicRepository.delete(topic.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
