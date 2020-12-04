package com.manning.readinglist.apiTest;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.manning.readinglist.entity.Book;
import com.manning.readinglist.properties.AmazonProperties;
import com.manning.readinglist.repository.ReadingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ReadingListApiTest {
  @Autowired
  MockMvc mockMvc;

  @Autowired
  AmazonProperties amazonProperties;

  @Autowired
  ReadingListRepository readingListRepository;

  @BeforeEach
  public void init() {
    readingListRepository.deleteAll();
  }

  @Test
  void should_get_reader_reading_list_successfully() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/readingList/Nelson"))
        .andExpect(status().isOk())
        .andExpect(view().name("readingList"))
        .andExpect(model().attributeDoesNotExist("books"))
        .andExpect(model().attributeDoesNotExist("reader"))
        .andExpect(model().attributeDoesNotExist("amazonID"));
  }

  @Test
  void should_post_a_book_on_reading_list_successfully() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/readingList/Nelson")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("title", "BOOK TITLE")
        .param("author","BOOK AUTHOR")
        .param("isbn","1234567890")
        .param("description","DESCRIPTION"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location","/readingList/Nelson"));

    Book expectedBook = Book.builder()
        .id(1L)
        .reader("Nelson")
        .title("BOOK TITLE")
        .author("BOOK AUTHOR")
        .isbn("1234567890")
        .description("DESCRIPTION")
        .build();

    mockMvc.perform(MockMvcRequestBuilders.get("/readingList/Nelson"))
        .andExpect(status().isOk())
        .andExpect(view().name("readingList"))
        .andExpect(model().attributeExists("books"))
        .andExpect(model().attributeExists("reader"))
        .andExpect(model().attribute("books", hasSize(1)))
        .andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))))
        .andExpect(model().attribute("reader","Nelson"))
        .andExpect(model().attributeExists("amazonID"))
        .andExpect(model().attribute("amazonID",amazonProperties.getAssociateId()));
  }
}
