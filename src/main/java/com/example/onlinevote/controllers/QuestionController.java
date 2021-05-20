package com.example.onlinevote.controllers;

import com.example.onlinevote.dto.ResultDto;
import com.example.onlinevote.models.Question;
import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.models.Result;
import com.example.onlinevote.models.Score;
import com.example.onlinevote.multithreading.Recount;
import com.example.onlinevote.repositories.QuestionRepository;
import com.example.onlinevote.repositories.ResultRepository;
import com.example.onlinevote.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final Recount recountService;
    @Autowired
    private final ResultRepository resultRepository;
    @Autowired
    private final ScoreRepository scoreRepository;

    public QuestionController(QuestionRepository questionRepository, Recount recountService, ResultRepository resultRepository, ScoreRepository scoreRepository) {
        this.questionRepository = questionRepository;
        this.recountService = recountService;
        this.resultRepository = resultRepository;
        this.scoreRepository = scoreRepository;
    }

    @PostMapping("/remove/{question}")
    public String removeQuestion(@PathVariable(name = "question") Question question) {
        questionRepository.delete(question);
        return "redirect:/quiz/details/" + question.getQuiz().getId();
    }

    @GetMapping("/edit/{question}")
    public String editQuestion(Model model, @PathVariable(name = "question") Question question) {
        List<Result> resultList = resultRepository.getAllByQuestion_Id(question.getId());
        Map<String, Integer> statistics = getVotePercentage(resultList, question);
        model.addAttribute("question", question);
        model.addAttribute("statistics", statistics);
        model.addAttribute("size", resultList.size());
        return "question-edit-page";
    }

    @PostMapping("/edit")
    public String editQuestionPost(@ModelAttribute Question question) {

        List<Result> resultList = resultRepository.getAllByQuestion_Id(question.getId());
        Quiz quiz = question.getQuiz();
        Integer notAnswered = -1;
        resultList = resultList.stream().filter(e -> !e.getAnswer().equals(notAnswered)).collect(Collectors.toList());



        resultList.forEach(e -> {
            ResultDto recount = recountService.recount(e, question.getAnswerNumber());
            Score score = scoreRepository.getByUserAndQuiz(recount.getUser(), quiz);

            if (e.isTrue() && !recount.getTrue()) {
                score.removeScore();
            } else if (!e.isTrue() && recount.getTrue()) {
                score.addScore();
            }else {
                System.out.println("No change");
            }
            scoreRepository.save(score);
        });
        questionRepository.save(question);


        return "redirect:/quiz/details/" + quiz.getId();
    }

    public Map<String, Integer> getVotePercentage(List<Result> resultList, Question question) {
        Map<String, Integer> voteCount = new HashMap<>();
        question.getOptions().forEach((integer, s) -> {
            voteCount.put(s, 0);
        });

        for (Result result : resultList) {
            if (question.getOptions().containsKey(result.getAnswer())) {

                String s = question.getOptions().get(result.getAnswer());
                if (s != null) {
                    voteCount.put(s, voteCount.get(s) + 1);
                }
            }

        }
        return voteCount;
    }


}
