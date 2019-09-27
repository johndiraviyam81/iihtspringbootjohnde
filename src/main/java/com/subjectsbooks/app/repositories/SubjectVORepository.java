package com.subjectsbooks.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.subjectsbooks.app.entity.SubjectVO;


@Transactional
@Repository("subjectVORepository")
public interface SubjectVORepository extends JpaRepository<SubjectVO, Integer> {

	SubjectVO findBySubjectId(long subjectId);

	List<SubjectVO> findAllBySubTitle(String title);

}
