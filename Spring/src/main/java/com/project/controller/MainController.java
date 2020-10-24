package com.project.controller;

import com.project.domain.Message;
import com.project.domain.User;
import com.project.reps.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String main(
            @RequestParam(required = false, defaultValue = "")
            String filter,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<Message> page;

        if (filter==null || filter.isEmpty()){
            page = messageRepo.findAll(pageable);
        } else {
            page = messageRepo.findByTag(filter,pageable);
            model.addAttribute("filter", filter);
        }

        model.addAttribute("page",page);
        model.addAttribute("url","/main");
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "file", required = false) MultipartFile file,
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
            saveFile(file, message);
            model.addAttribute("message",null);
            messageRepo.save(message);
        }
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages",messages);
        return "/main";
    }

    private void saveFile(MultipartFile file, Message message) throws IOException {
        if (file != null && !file.isEmpty() && file.getSize()!=0 && !file.getOriginalFilename().isEmpty() && !file.getOriginalFilename().equals("") && !file.getOriginalFilename().equals(" ")) {
            File uploadDir = new File(uploadpath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();

            String resultFileName = uuidFile + "." +  file.getOriginalFilename();
            file.transferTo(new File(uploadpath + "/" + resultFileName));

            message.setFilename(resultFileName);
        }
    }

    // TODO: 10/18/2020 Вынести в отдельный контроллер!
    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message
    ){
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("userChannel",user);
        model.addAttribute("messages",user.getMessages());
        model.addAttribute("message",message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam(name = "remImage", required = false) String remImage,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (message.getAuthor().equals(currentUser)){
                if (message.getFilename()==null){
                    saveFile(file,message);
                } else {
                    if (remImage!=null&&remImage.equals("on")){
                        message.setFilename(null);
                    }
                }
            // TODO: 10/22/2020 IMAGES
            if (!StringUtils.isEmpty(text)){
                message.setText(text);
            }
            if (!StringUtils.isEmpty(tag)){
                message.setTag(tag);
            }
            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + user;
    }
}