package com.pofolio.web.development.project.NovaMarket.repository;


import com.pofolio.web.development.project.NovaMarket.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
