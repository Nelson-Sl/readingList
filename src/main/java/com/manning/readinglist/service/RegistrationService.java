package com.manning.readinglist.service;

import com.manning.readinglist.entity.Reader;
import com.manning.readinglist.repository.ReaderRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

@Service
public class RegistrationService {
  private final PasswordEncoder passwordEncoder;
  private final ReaderRepository readerRepository;

  public RegistrationService(PasswordEncoder passwordEncoder,
                             ReaderRepository readerRepository) {
    this.passwordEncoder = passwordEncoder;
    this.readerRepository = readerRepository;
  }

  public String showRegistrationForm(WebRequest webRequest, Model model) {
    Reader newReader = new Reader();
    model.addAttribute("reader",newReader);
    return "registration";
  }

  public String register(Reader newReader) {
    newReader.setPassword(passwordEncoder.encode(newReader.getPassword()));
    readerRepository.save(newReader);
    return "redirect:/login";
  }
}
