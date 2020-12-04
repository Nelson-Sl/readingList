package com.manning.readinglist.repository;

import com.manning.readinglist.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
  Reader findByUsername(String username);
}
