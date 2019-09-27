package com.subjectsbooks.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.subjectsbooks.app.entity.BookVO;

@Transactional
@Repository("bookVORepository")
public interface BookVORepository extends JpaRepository<BookVO, Integer> {

	BookVO findByBookId(long bookId);

	void deleteByBookId(long bookId);

	List<BookVO> findAllByTitle(String title);
	

}
