package com.example.onlinevote.controllers;

import com.example.onlinevote.models.Question;
import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.repositories.QuestionRepository;
import com.example.onlinevote.repositories.QuizRepository;
import com.example.onlinevote.repositories.ResultRepository;
import com.example.onlinevote.repositories.ScoreRepository;
import com.example.onlinevote.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private final QuizRepository quizRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final ResultRepository resultRepository;
    @Autowired
    private final ScoreRepository scoreRepository;

    public QuizController(QuizRepository quizRepository, UserService userService, QuestionRepository questionRepository, ResultRepository resultRepository, ScoreRepository scoreRepository) {
        this.quizRepository = quizRepository;
        this.userService = userService;

        this.questionRepository = questionRepository;
        this.resultRepository = resultRepository;
        this.scoreRepository = scoreRepository;
    }

    @GetMapping("/details/{quiz}")
    public String quizDetails(
            @PathVariable Quiz quiz,
            Model model) {
        List<Question> questions = questionRepository.findByQuizId(quiz.getId());
        model.addAttribute("question", new Question());
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);

        return "quiz-details-page";
    }

    @PostMapping("/question/add")
    public String questionAdd(
            @RequestParam Quiz quiz,
            @ModelAttribute Question question,
            Model model
    ) {
        question.setQuiz(quiz);
        System.out.println(question);

        questionRepository.save(question);

        List<Question> questions = questionRepository.findByQuizId(quiz.getId());

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);

        return "redirect:/quiz/details/" + quiz.getId();
    }

    @GetMapping("/add")
    @Secured("ROLE_ADMIN")
    public String addQuiz(Model model) {
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("questions", new HashSet<Question>());
        return "quiz-add-page";
    }

    @PostMapping("/add")
    @Secured("ROLE_ADMIN")
    public String saveQuiz(
            Principal principal,
            @Valid @ModelAttribute Quiz quiz,
            BindingResult bindingResult
    ) {
        quiz.setAuthor(userService.getUserByUsername(principal.getName()));

        if (bindingResult.hasErrors()) {
            return "quiz-edit-page";
        }

        quizRepository.save(quiz);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String quizEdit(
            Principal principal,
            @RequestParam Quiz quiz,
            Model model) {
        if (!userService.getUserByUsername(principal.getName()).equals(quiz.getAuthor())) {
            return "redirect:/quiz/add";
        }
        model.addAttribute("quiz", quiz);

        return "quiz-edit-page";

    }

    @GetMapping("/remove/{quiz}")
    @Transactional
    public String removeQuiz(@PathVariable(name = "quiz") Quiz quiz) {
        resultRepository.deleteAllByQuestionQuiz(quiz);
        scoreRepository.deleteAllByQuiz(quiz);
        quizRepository.delete(quiz);
        return "redirect:/";
    }


}
