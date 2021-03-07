package demo.project.chatbot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import demo.project.chatbot.service.ChatService;

@RestController
@RequestMapping(value="/")
public class ChatController {

    private static Map<String, String> mapMessages;
    static {
        mapMessages = new HashMap<>();
        mapMessages.put("hello", "Hello, how are you ?");
        mapMessages.put("hi", "Hi there");
        mapMessages.put("how are you", "I am good, Thanks For asking");
        mapMessages.put("what is your name", "I am a bot, just developed");
        mapMessages.put("what can you do", "Some Stuffs");
        mapMessages.put("thanks", "Thank you");
        mapMessages.put("welcome", " welcome :)");
        mapMessages.put("bye", "Bye, Nice talk with you!");
        mapMessages.put("", "Hello, how may I help you!");
    }
    
    @Autowired
    private ChatService chatService;
    
    /*
    @GetMapping
    public String GetHome() {
        return "Hello from rest Controller...";
    }
    */
    @GetMapping("/greeting")
    public ModelAndView greeting(@RequestParam(name="name", required=false, defaultValue="User") String name, ModelAndView model) {
        ModelAndView modelAndView =  new ModelAndView("greeting");
        modelAndView.addObject("name", name);
        System.out.println("name=> "+name);
        return modelAndView;
    }
    
    @GetMapping("/startchat")
    public ModelAndView startChat(@RequestParam(name="message", required=false, defaultValue="") String message) {
        
        String reply = "Hello, how may I help you!";
        ModelAndView modelAndView =  new ModelAndView("chat");
        modelAndView.addObject("message", reply);
        return modelAndView;
    }
    
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/chat")
    public String chat(@RequestParam(name="message", required=false, defaultValue="") String message) {
        
        String reply = "I don't understand that, I am still learning...";
        System.out.println("message=> "+message);
        if(mapMessages.containsKey(message.toLowerCase())) {
            reply = mapMessages.get(message.toLowerCase());
            System.out.println("reply=> "+reply);
        }
        String response = chatService.startChat(message);
        if(!"".equals(response)){
        	reply = response;
        }
        
        return reply;
    }

}
