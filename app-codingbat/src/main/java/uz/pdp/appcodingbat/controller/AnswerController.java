package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entitiy.Answer;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.AnswerDto;
import uz.pdp.appcodingbat.service.AnswerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    /**
     * In this method we are returning all Answers
     *
     * @return Answers
     */

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers() {
        List<Answer> answers = answerService.getAnswers();
        return ResponseEntity.ok(answers);
    }

    /**
     * In this method we are returning a Answer with given id
     *
     * @param id Integer
     * @return Answer
     */

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable Integer id) {

        Answer answer = answerService.getAnswerById(id);
        return ResponseEntity.ok(answer);
    }

    /**
     * In this method we are creating new Answer
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation Answer
     */

    @PostMapping
    public ResponseEntity<ApiResponse> addAnswer(@Valid @RequestBody AnswerDto answerDto
    ) {
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Answer editing method
     *
     * @param id        Integer
     * @param answerDto Json
     * @return ApiResponse
     * We are getting id and AnswerDto Json Object.
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAnswer(@PathVariable Integer id, @Valid @RequestBody AnswerDto answerDto) {
        ApiResponse apiResponse = answerService.editAnswer(id, answerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Deleting Answer by Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Integer id) {
        ApiResponse apiResponse = answerService.deleteAnswer(id);

        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
