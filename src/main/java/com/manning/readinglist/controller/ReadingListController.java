package com.manning.readinglist.controller;

import com.manning.readinglist.entity.Book;
import com.manning.readinglist.service.ReadingListService;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/readingList")
@Data
public class ReadingListController {
  private final ReadingListService readingListService;

  private String associateId;

  public ReadingListController(ReadingListService readingListService) {
    this.readingListService = readingListService;
  }

  @GetMapping("/{reader}")
  public String readersBooks(@PathVariable("reader") String reader, Model model){
    return readingListService.readersBooks(reader,model);
  }

  @PostMapping("/{reader}")
  public String addToReaderList(@PathVariable("reader") String reader, Book book) {
    return readingListService.addToReaderList(reader, book);
  }


}
