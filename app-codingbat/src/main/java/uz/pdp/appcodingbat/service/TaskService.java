package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entitiy.Category;
import uz.pdp.appcodingbat.entitiy.Category;
import uz.pdp.appcodingbat.entitiy.Task;
import uz.pdp.appcodingbat.payload.TaskDto;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.repository.CategoryRepository;
import uz.pdp.appcodingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;


    /**
     * In this method we are returning all Tasks
     *
     * @return Tasks
     */

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    /**
     * In this method we are returning a Task with given id
     *
     * @param id Integer
     * @return Task
     */

    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    /**
     * In this method we are creating new Task
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation Task
     */

    public ApiResponse addTask(TaskDto taskDto) {
        boolean exists = taskRepository.existsByNameAndCategoryId(taskDto.getName(), taskDto.getCategoryId());
        if (exists) {
            return new ApiResponse("This Task already exist", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        Task task = new Task();
        task.setText(taskDto.getText());
        task.setHasStar(taskDto.isHasStar());
        task.setHint(taskDto.getHint());
        task.setMethod(taskDto.getMethod());
        task.setName(taskDto.getName());
        task.setSolution(taskDto.getSolution());
        optionalCategory.ifPresent(task::setCategory);
        taskRepository.save(task);
        return new ApiResponse("Task successfully added", true);
    }

    /**
     * Task editing method
     *
     * @param id      Integer
     * @param taskDto Json
     * @return ApiResponse
     * We are getting id and TaskDto Json Object.
     */

    public ApiResponse editTask(Integer id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
            Task editingTask = optionalTask.get();
            editingTask.setName(taskDto.getName());
            editingTask.setText(taskDto.getText());
            editingTask.setSolution(taskDto.getSolution());
            editingTask.setMethod(taskDto.getMethod());
            editingTask.setHint(taskDto.getHint());
            editingTask.setHasStar(taskDto.isHasStar());
            optionalCategory.ifPresent(editingTask::setCategory);
            taskRepository.save(editingTask);
            return new ApiResponse("Successfully edited", true);
        }
        return new ApiResponse("Task not found", false);
    }

    /**
     * Deleting Task by Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    public ApiResponse deleteTask(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("Task deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);

        }
    }
}
