package com.infosys.search.service;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.infosys.search.domain.Hotel;
import com.infosys.search.exceptions.ResourceNotFoundException;
import com.infosys.search.repository.HotelRepository;

@Service
public class HotelService extends AbstractBaseService<Hotel, String>{

	private final HotelRepository hotelRepository;
	private final ElasticsearchOperations operations;
	
	public HotelService(HotelRepository hotelRepository,ElasticsearchOperations operations) {
		this.hotelRepository=hotelRepository;
		this.operations=operations;
	}
	
	public Hotel findById(String id) {
		return hotelRepository.findOneById(id).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException("Hotel", "Not Found");
		});
	}
	
	public List<Hotel> findByDestination(String destination){
		SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(matchQuery("destination", destination)).build();
		return operations.queryForList(searchQuery, Hotel.class);
	}
	
	public List<Hotel> findByLocation(String location){
		SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(matchQuery("location", location)).build();
		return operations.queryForList(searchQuery, Hotel.class);
	}
	
	public List<Hotel> findByDestinationOrLocation(String query){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
				  .should(QueryBuilders
						  .matchQuery("destination", query)
						  .fuzziness(Fuzziness.TWO))
				  .should(QueryBuilders
						  .matchQuery("location", query)
						  .fuzziness(Fuzziness.TWO));
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return operations.queryForList(searchQuery, Hotel.class);
	}
	
	public List<Hotel> findByDestinationGroup(String destination,String group){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
				  .must(QueryBuilders
						  .matchQuery("destination", destination))
						 
				  .must(QueryBuilders
						  .matchQuery("group", group)
						  );
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return operations.queryForList(searchQuery, Hotel.class);
	}
	
	public List<Hotel> findByDestinationIdGroup(String destinationId,String landmark,String group){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
				  .must(QueryBuilders
						  .matchQuery("destinationId", destinationId))
				  .must(QueryBuilders
						  .matchQuery("group", group)
						  );
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return operations.queryForList(searchQuery, Hotel.class);
	}
	
	public long count() {
		return hotelRepository.count();
	}
}
