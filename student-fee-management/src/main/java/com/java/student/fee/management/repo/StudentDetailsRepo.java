package com.java.student.fee.management.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.student.fee.management.entity.StudentDetailsEntity;

@Repository
public interface StudentDetailsRepo extends JpaRepository<StudentDetailsEntity, Long> {
	
	@Query("SELECT COUNT(*) FROM StudentDetailsEntity sde "
			+ "WHERE sde.mobileNumber = :mobileNumber AND sde.name = :name")
	Integer countMobileNumber(String mobileNumber, String name);

	
}
