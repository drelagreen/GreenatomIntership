package com.project.controller;

import com.project.domain.User;
import com.project.domain.dto.RecaptchaResponseDto;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;


    private static final String recaptchaSecret = "6Lc2gtgZAAAAAMMYDUyrz1h1t5iylOdpCDCgRoGa";

    private static final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("recaptcha.clientCode")
    private String recaptchaClientCode;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("recaptchaClientCode", recaptchaClientCode);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String recaptchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        String url = String.format(RECAPTCHA_URL, recaptchaSecret, recaptchaResponse);
        RecaptchaResponseDto response = restTemplate.postForObject(url, Collections.EMPTY_LIST, RecaptchaResponseDto.class);
        if (!response.isSuccess()){
            model.addAttribute("recaptchaError", "Fill captcha");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);

        if (isConfirmEmpty){
            model.addAttribute("password2Error", "Field \"Confirm password\" can not be empty");
        }


        boolean isPasswordsEquals = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);
        if (isPasswordsEquals){
            model.addAttribute("passwordError", "Passwords are different");
        }

        if (isConfirmEmpty || !isPasswordsEquals || bindingResult.hasErrors() || !response.isSuccess()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);

            return "registration";
        }

        if (userService.addUser(user)){
            model.addAttribute("message","Activation e-mail has been sent");
            return "/login";
        } else {
            model.addAttribute("usernameError", "User already exist");
            return "registration";
        }

    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated  = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "User has been successfully activated!");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }
        return "redirect:/login";
    }

}
