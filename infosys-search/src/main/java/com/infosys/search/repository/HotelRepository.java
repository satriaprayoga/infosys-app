package com.infosys.search.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.infosys.search.domain.Hotel;

@Repository
public interface HotelRepository extends ElasticsearchRepository<Hotel, String>{

	Optional<Hotel> findOneById(String id);
}
