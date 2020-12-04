package com.manning.readinglist.service;

import com.manning.readinglist.entity.Book;
import com.manning.readinglist.properties.AmazonProperties;
import com.manning.readinglist.repository.ReadingListRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class  ReadingListService {
  private final ReadingListRepository readingListRepository;
  private final AmazonProperties amazonProperties;

  public ReadingListService(ReadingListRepository readingListRepository,
                            AmazonProperties amazonProperties) {
    this.readingListRepository = readingListRepository;
    this.amazonProperties = amazonProperties;
  }

  public String readersBooks(String reader, Model model) {
    Optional<List<Book>> readingList = readingListRepository.findByReader(reader);
    if(readingList.isPresent()) {
      model.addAttribute("books", readingList.get());
      model.addAttribute("reader", reader);
      model.addAttribute("amazonID", amazonProperties.getAssociateId());
    }
    return "readingList";
  }

  public String addToReaderList(String reader, Book book) {
    book.setReader(reader);
    readingListRepository.save(book);
    return "redirect:/readingList/{reader}";
  }
}
