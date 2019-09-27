package com.subjectsbooks.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.subjectsbooks.app.entity.BookReferenceVO;
import com.subjectsbooks.app.entity.BookVO;
import com.subjectsbooks.app.entity.SubjectVO;

@Transactional
@Repository("bookReferenceVORepository")
public interface BookReferenceVORepository extends JpaRepository<BookReferenceVO, Integer> {

	boolean deleteByBookVO(BookVO bookVO);

	boolean deleteBySubjectVO(SubjectVO subjectVOid);

	List<BookReferenceVO> findAllBySubjectVO(SubjectVO subjectvo);

}
