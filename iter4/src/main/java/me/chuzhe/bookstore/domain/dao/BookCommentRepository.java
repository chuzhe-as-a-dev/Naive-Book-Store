package me.chuzhe.bookstore.domain.dao;

import me.chuzhe.bookstore.domain.entity.BookComment;
import me.chuzhe.bookstore.domain.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tang on 2017/5/21.
 */
public interface BookCommentRepository extends CrudRepository<BookComment, Integer> {
    List<BookComment> findAllByBookId(int bookId, Sort sort);

    List<BookComment> findAllByUserByUserId(User user);

    BookComment findByUserByUserIdAndBookId(User user, int bookId);
}
