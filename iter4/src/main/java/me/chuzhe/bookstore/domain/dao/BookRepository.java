package me.chuzhe.bookstore.domain.dao;

import me.chuzhe.bookstore.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by tang on 2017/5/21.
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    Page<Book> findAllByForSaleIsTrue(Pageable pageable);

    Page<Book> findByBookTitleContaining(String keyword, Pageable pageable);
}
