package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entitiy.Answer;
import uz.pdp.appcodingbat.entitiy.Task;
import uz.pdp.appcodingbat.entitiy.User;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.AnswerDto;
import uz.pdp.appcodingbat.repository.AnswerRepository;
import uz.pdp.appcodingbat.repository.TaskRepository;
import uz.pdp.appcodingbat.repository.UserRepository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * In this method we are returning all Answers
     *
     * @return Answers
     */

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    /**
     * In this method we are returning a Answer with given id
     *
     * @param id Integer
     * @return Answer
     */

    public Answer getAnswerById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);
    }

    /**
     * In this method we are creating new Answer
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation Answer
     */

    public ApiResponse addAnswer(AnswerDto answerDto) {
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        Answer answer = new Answer();
        answer.setText(answerDto.getText());
        answer.setCorrect(answerDto.isCorrect());
        optionalUser.ifPresent(answer::setUser);
        User user = answer.getUser();
        userRepository.save(user);
        optionalTask.ifPresent(answer::setTask);
        Task task = answer.getTask();
        taskRepository.save(task);
        answerRepository.save(answer);
        return new ApiResponse("Answer successfully added", true);
    }

    /**
     * Answer editing method
     *
     * @param id        Integer
     * @param answerDto Json
     * @return ApiResponse
     * We are getting id and AnswerDto Json Object.
     */

    public ApiResponse editAnswer(Integer id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
            Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
            Answer editingAnswer = optionalAnswer.get();
            editingAnswer.setCorrect(answerDto.isCorrect());
            editingAnswer.setText(answerDto.getText());
            optionalUser.ifPresent(editingAnswer::setUser);
            User user = editingAnswer.getUser();
            userRepository.save(user);
            optionalTask.ifPresent(editingAnswer::setTask);
            Task task = editingAnswer.getTask();
            taskRepository.save(task);
            answerRepository.save(editingAnswer);
            return new ApiResponse("Successfully edited", true);
        }
        return new ApiResponse("Answer not found", false);
    }

    /**
     * Deleting Answer by Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    public ApiResponse deleteAnswer(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Answer deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);

        }
    }
}
