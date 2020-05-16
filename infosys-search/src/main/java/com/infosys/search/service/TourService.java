package com.infosys.search.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import com.infosys.search.domain.Tour;
import com.infosys.search.exceptions.ResourceNotFoundException;
import com.infosys.search.repository.TourRepository;

@Service
public class TourService extends AbstractBaseService<Tour, String>{

	private final TourRepository tourRepository;
	private final ElasticsearchOperations operations;
	
	public TourService(TourRepository tourRepository,ElasticsearchOperations operations) {
		this.tourRepository=tourRepository;
		this.operations=operations;
		setRepository(tourRepository);
	}
	
	public Tour findById(String id) {
		return tourRepository.findOneById(id).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException("Tour", "Not Found");
		});
	}
	
	public List<Tour> findByDestination(String destination){
		SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(matchQuery("destination", destination)).build();
		return operations.queryForList(searchQuery, Tour.class);
	}
	
	public List<Tour> findByLandmark(String landmark){
		SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(matchQuery("landmarks", landmark)).build();
		return operations.queryForList(searchQuery, Tour.class);
	}
	
	public List<Tour> findByGroup(String group){
		SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(matchQuery("group", group)).build();
		return operations.queryForList(searchQuery, Tour.class);
	}
	
	public List<Tour> findByDestinationLandmarksGroup(String destination,String landmark,String group){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
				  .must(QueryBuilders
						  .matchQuery("destination", destination))
						 
				  .must(QueryBuilders
						  .matchQuery("landmarks", landmark)
						 )
				  .must(QueryBuilders
						  .matchQuery("group", group)
						  );
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return operations.queryForList(searchQuery, Tour.class);
	}
	
	public List<Tour> findByDestinationIdLandmarksGroup(String destinationId,String landmark,String group){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
				  .must(QueryBuilders
						  .matchQuery("destinationId", destinationId))
						 
				  .must(QueryBuilders
						  .matchQuery("landmarks", landmark)
						 )
				  .must(QueryBuilders
						  .matchQuery("group", group)
						  );
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return operations.queryForList(searchQuery, Tour.class);
	}

	public long count() {
		return tourRepository.count();
	}
	
	
	
}
