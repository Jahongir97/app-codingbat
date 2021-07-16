package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entitiy.Task;
import uz.pdp.appcodingbat.payload.TaskDto;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    /**
     * In this method we are returning all Tasks
     *
     * @return Tasks
     */

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * In this method we are returning a Task with given id
     *
     * @param id Integer
     * @return Task
     */

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id) {

        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * In this method we are creating new Task
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation Task
     */

    @PostMapping
    public ResponseEntity<ApiResponse> addTask(@Valid @RequestBody TaskDto taskDto
    ) {
        ApiResponse apiResponse = taskService.addTask(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Task editing method
     *
     * @param id      Integer
     * @param taskDto Json
     * @return ApiResponse
     * We are getting id and TaskDto Json Object.
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editTask(@PathVariable Integer id, @Valid @RequestBody TaskDto  taskDto) {
        ApiResponse apiResponse = taskService.editTask(id, taskDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Deleting Task by Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        ApiResponse apiResponse = taskService.deleteTask(id);

        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
