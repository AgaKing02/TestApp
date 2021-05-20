package com.example.onlinevote.controllers;

import com.example.onlinevote.models.*;
import com.example.onlinevote.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/appeal")
public class AppealController {

    private final AppealRepository appealRepository;
    private final AppealResultRepository appealResultRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final ResultRepository resultRepository;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public String getAllAppeal(Model model, Principal principal) {

        try{
            List<Appeal> nonCheckedAppeals = appealRepository.getAllByCheckedFalseAndQuestionQuizAuthorUsername(principal.getName());
            model.addAttribute("appeals", nonCheckedAppeals);
        }catch (NullPointerException e){
            model.addAttribute("appeals", new ArrayList<>());
        }
        return "appeal-page";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/edit/{appeal}/{approved}")
    public String editTheAppeal(@PathVariable(name = "appeal") Appeal appeal,
                                @PathVariable(name = "approved") Boolean checked) {
        AppealResult appealResult = appealResultRepository.getByAppeal(appeal);
        appeal.setChecked(true);
        appealResult.setChecked(true);
        if (checked) {
            appealResult.set_approved(true);
            appealRepository.save(appeal);
            appealResultRepository.save(appealResult);
            return "redirect:/question/edit/" + appeal.getQuestion().getId();
        }else{
            appealRepository.save(appeal);
            appealResultRepository.save(appealResult);
            return "redirect:/appeal";
        }

    }


    @Secured("ROLE_USER")
    @GetMapping("/to/{id}")
    public String getAppealPage(Model model, @PathVariable(name = "id") Long question_id,Principal principal) {
        Question question = questionRepository.getById(Math.toIntExact(question_id));
        Result result=resultRepository.getByQuestionAndUserUsername(question,principal.getName());
        model.addAttribute("question", question);
        model.addAttribute("result",result);
        return "appeal-form-page";
    }

    @Secured("ROLE_USER")
    @PostMapping("/to/{id}")
    public String doAppeal(Principal principal,
                           @PathVariable(name = "id") Long question_id,
                           @RequestParam(name = "proof") String proof) {
        Question question = questionRepository.getById(Math.toIntExact(question_id));
        if (!appealRepository.existsByQuestionAndCheckedTrue(question)) {
            User user = userRepository.findUserByUsername(principal.getName());

            Appeal appeal = new Appeal();
            appeal.setQuestion(question);
            appeal.setUser(user);
            appeal.setProof(proof);

            AppealResult appealResult = new AppealResult();
            appealResult.setAppeal(appealRepository.save(appeal));

            appealResultRepository.save(appealResult);

            return "redirect:/appeal/my";

        }
        return "redirect:/error";
    }

    @Secured("ROLE_USER")
    @GetMapping("/my")
    public String getMyAppealResult(Model model,Principal principal) {
        List<AppealResult> appealResults=appealResultRepository.getAllByAppealUserUsername(principal.getName());
        model.addAttribute("results", appealResults);
        return "my-appeal-page";
    }



}
