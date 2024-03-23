package com.java.student.fee.management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.student.fee.management.entity.FeeEntity;

@Repository
public interface FeeRepo extends JpaRepository<FeeEntity, Long>{

	
	@Query("SELECT COUNT(*) FROM FeeEntity fe WHERE fe.referenceNo = :referenceNo")
	Integer countRefernceNo(String referenceNo);
	
	@Query("SELECT fe FROM FeeEntity fe WHERE fe.referenceNo = :referenceNo")
	List<FeeEntity> findByReferenceNo(String referenceNo);
}
