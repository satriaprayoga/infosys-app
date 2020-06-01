package com.infosys.search.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.infosys.search.domain.Booked;

@Repository
public interface BookedRepository extends ElasticsearchRepository<Booked, String>{

	List<Booked> findByPackageIdAndBookDate(String packageId,Date date);
	
	List<Booked> findByBookDate(Date date);

}
