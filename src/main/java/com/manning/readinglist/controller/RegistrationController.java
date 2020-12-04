package com.manning.readinglist.controller;

import com.manning.readinglist.entity.Reader;
import com.manning.readinglist.service.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/readingList/registration")
public class RegistrationController {
  private final RegistrationService registrationService;

  public RegistrationController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @GetMapping
  public String showRegistrationForm(WebRequest webRequest, Model model) {
    return registrationService.showRegistrationForm(webRequest, model);
  }

  @PostMapping
  public String registration(Reader newReader) {
    return registrationService.register(newReader);
  }
}
