package com.cstapin.quiz.ui;


import com.cstapin.quiz.service.QuizUserService;
import com.cstapin.quiz.service.dto.QuizCategoryResponse;
import com.cstapin.quiz.service.dto.RandomQuizzesRequest;
import com.cstapin.quiz.service.dto.RandomQuizzesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/web/user/quizzes")
@RequiredArgsConstructor
public class QuizUserWebController {

    private final QuizUserService quizUserService;

    @PostMapping("/random")
    public ResponseEntity<List<RandomQuizzesResponse>> getRandomQuizzes(@Valid @RequestBody RandomQuizzesRequest request) {
        List<RandomQuizzesResponse> randomQuizzes = quizUserService.getRandomQuizzes(request);

        return ResponseEntity.ok().body(randomQuizzes);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<QuizCategoryResponse>> getQuizCategories() {
        List<QuizCategoryResponse> quizCategoryResponses = quizUserService.findQuizCategories();

        return ResponseEntity.ok().body(quizCategoryResponses);
    }
}