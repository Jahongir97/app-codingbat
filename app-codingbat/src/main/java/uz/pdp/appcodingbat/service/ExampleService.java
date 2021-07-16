package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entitiy.Task;
import uz.pdp.appcodingbat.entitiy.Example;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ExampleDto;
import uz.pdp.appcodingbat.repository.ExampleRepository;
import uz.pdp.appcodingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    TaskRepository taskRepository;


    /**
     * In this method we are returning all Examples
     *
     * @return Examples
     */

    public List<Example> getExamples() {
        return exampleRepository.findAll();
    }

    /**
     * In this method we are returning a Example with given id
     *
     * @param id Integer
     * @return Example
     */

    public Example getExampleById(Integer id) {
        Optional<Example> optionalTask = exampleRepository.findById(id);
        return optionalTask.orElse(null);
    }

    /**
     * In this method we are creating new Example
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation Example
     */

    public ApiResponse addExample(ExampleDto exampleDto) {
        boolean exists = exampleRepository.existsByTextAndTaskId(exampleDto.getText(), exampleDto.getTaskId());
        if (exists) {
            return new ApiResponse("This Example already exist", false);
        }
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        Example example = new Example();
        example.setText(exampleDto.getText());
        optionalTask.ifPresent(example::setTask);
        exampleRepository.save(example);
        return new ApiResponse("Example successfully added", true);
    }

    /**
     * Example editing method
     *
     * @param id         Integer
     * @param exampleDto Json
     * @return ApiResponse
     * We are getting id and ExampleDto Json Object.
     */

    public ApiResponse editExample(Integer id, ExampleDto exampleDto) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isPresent()) {
            Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
            Example editingExample = optionalExample.get();
            editingExample.setText(exampleDto.getText());
            optionalTask.ifPresent(editingExample::setTask);
            exampleRepository.save(editingExample);
            return new ApiResponse("Successfully edited", true);
        }
        return new ApiResponse("Example not found", false);
    }

    /**
     * Deleting Exampleby Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    public ApiResponse deleteExample(Integer id) {
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("Example deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);

        }
    }
}
