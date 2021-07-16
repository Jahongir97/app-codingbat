package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entitiy.Example;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ExampleDto;
import uz.pdp.appcodingbat.service.ExampleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleController {
    @Autowired
    ExampleService exampleService;

    /**
     * In this method we are returning all Examples
     *
     * @return Examples
     */

    @GetMapping
    public ResponseEntity<List<Example>> getExamples() {
        List<Example> examples = exampleService.getExamples();
        return ResponseEntity.ok(examples);
    }

    /**
     * In this method we are returning a Example with given id
     *
     * @param id Integer
     * @return Example
     */

    @GetMapping("/{id}")
    public ResponseEntity<Example> getExample(@PathVariable Integer id) {

        Example example = exampleService.getExampleById(id);
        return ResponseEntity.ok(example);
    }

    /**
     * In this method we are creating new Example
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation Example
     */

    @PostMapping
    public ResponseEntity<ApiResponse> addExample(@Valid @RequestBody ExampleDto exampleDto
    ) {
        ApiResponse apiResponse = exampleService.addExample(exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Example editing method
     *
     * @param id         Integer
     * @param exampleDto Json
     * @return ApiResponse
     * We are getting id and ExampleDto Json Object.
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editExample(@PathVariable Integer id, @Valid @RequestBody ExampleDto exampleDto) {
        ApiResponse apiResponse = exampleService.editExample(id, exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Deleting Exampleby Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExample(@PathVariable Integer id) {
        ApiResponse apiResponse = exampleService.deleteExample(id);

        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
