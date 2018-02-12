package io.public_library.person;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.public_library.auth.service.SecurityService;
import io.public_library.auth.service.UserService;
import io.public_library.auth.validator.UserValidator;

@Controller
public class PersonController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model, Principal user) {
    	model.addAttribute("userForm", new Person());
    	if (user != null) {
    		return "redirect:/";
    	}

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Person userForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
    	System.out.println("registraton post");
        System.out.println(userForm.getUsername());
        System.out.println(bindingResult);
    	userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	System.out.println("binding result has errors");
        	for (Object object : bindingResult.getAllErrors()) {
        		if(object instanceof FieldError) {
        	        FieldError fieldError = (FieldError) object;
        	        System.out.println(fieldError.getField());
        	        System.out.println(fieldError.getCode());
        	        System.out.println(fieldError.getDefaultMessage());
        	        System.out.println(fieldError.toString());
        	        String fieldErrorCode = fieldError.getCode();
        	        if(fieldErrorCode.equals("Size.userForm.username")) {
        	        	redirectAttrs.addFlashAttribute("message1", fieldErrorCode);
        	        }
					if(fieldErrorCode.equals("Duplicate.userForm.username")) {
						redirectAttrs.addFlashAttribute("message2", fieldErrorCode);
        	        }
					if(fieldErrorCode.equals("Size.userForm.password")) {
						redirectAttrs.addFlashAttribute("message3", fieldErrorCode);   	        	
        	        }
					if(fieldErrorCode.equals("Diff.userForm.passwordConfirm")) {
						redirectAttrs.addFlashAttribute("message4", fieldErrorCode);   	        	
        	        }
        	        
        	    }
			}
        	//redirectAttrs.addFlashAttribute("errors", bindingResult.);
            return "redirect:/registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, Principal principal) {
    	//model.addAttribute("userForm", new Person());
    	if (principal != null) {
    		return "redirect:/";
    	}
    	
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("logout", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "allbooks"}, method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
        return "allbooks";
    }
}
