package com.manning.readinglist.repository;

import com.manning.readinglist.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingListRepository extends JpaRepository<Book, Long> {
  Optional<List<Book>> findByReader(String reader);

}
