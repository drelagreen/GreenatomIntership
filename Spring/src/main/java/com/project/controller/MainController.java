package com.project.controller;

import com.project.domain.Message;
import com.project.domain.User;
import com.project.reps.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static com.project.controller.ControllerUtils.getErrors;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadpath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "")String filter, Model model){
        Iterable<Message> messages;

        if (filter==null || filter.isEmpty()){
            messages = messageRepo.findAll();
        } else {
            messages = messageRepo.findByTag(filter);
            model.addAttribute("filter", filter);
        }
        model.addAttribute("messages",messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file,
            @Valid Message message,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message",message);
        } else {
            if (file != null || !file.isEmpty() || !file.getOriginalFilename().isEmpty() || !file.getOriginalFilename().equals("") || !file.getOriginalFilename().equals(" ")) {
                File uploadDir = new File(uploadpath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();

                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadpath + "/" + resultFileName));

                message.setFilename(resultFileName);
            }
            model.addAttribute("message",null);
            messageRepo.save(message);
        }
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages",messages);
        return "/main";
    }
}