package com.infosys.search.service;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
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
		setRepository(hotelRepository);
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
	
	public List<Hotel> findByDestinationId(String destinationId){
		SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(matchQuery("destinationId", destinationId)).build();
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
	
	public List<Hotel> findByDestinationGroup(String destination,String hotelGroup){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
				  .must(QueryBuilders
						  .matchQuery("destination", destination))
						 
				  .must(QueryBuilders
						  .matchQuery("hotelGroup", hotelGroup)
						  );
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return operations.queryForList(searchQuery, Hotel.class);
	}
	
	public List<Hotel> findByDestinationIdGroup(String destinationId,String hotelGroup){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
				  .must(QueryBuilders
						  .matchQuery("destinationId", destinationId))
						 
				  .must(QueryBuilders
						  .matchQuery("hotelGroup", hotelGroup)
						  );
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return operations.queryForList(searchQuery, Hotel.class);
	}
	
	public List<Hotel> findByDestinationIdGroup(String destinationId,String landmark,String hotelGroup){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
				  .must(QueryBuilders
						  .matchQuery("destinationId", destinationId))
				  .must(QueryBuilders
						  .matchQuery("hotelGroup", hotelGroup)
						  );
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return operations.queryForList(searchQuery, Hotel.class);
	}
	
	public Map<String, List<Hotel>> aggregateFilter(String destinationId){
	
		TermsAggregationBuilder aggregationBuilder=AggregationBuilders.terms("hotelGroup_count").field("hotelGroup");
		NativeSearchQueryBuilder nativeQueryBuilder=new NativeSearchQueryBuilder().addAggregation(aggregationBuilder);
		
		NativeSearchQuery query=nativeQueryBuilder.build();
		//List<Hotel> hotels=operations.queryForList(query, Hotel.class);
		Aggregations aggregations=operations.query(query, new ResultsExtractor<Aggregations>() {

			@Override
			public Aggregations extract(SearchResponse response) {
				return response.getAggregations();
			}
		});
		Map<String, Aggregation> aggregationMap = aggregations.asMap();
		ParsedStringTerms topTags = (ParsedStringTerms) aggregationMap.get("hotelGroup_count");
		Map<String,List<Hotel>> result=new HashMap<String, List<Hotel>>();
		List<String> keys=topTags.getBuckets().stream().map(k->k.getKeyAsString()).collect(Collectors.toList());
		keys.forEach((k)->{
			result.put(k, findByDestinationIdGroup(destinationId, k));
		});
		return result;
	}
	
	public long count() {
		return hotelRepository.count();
	}
}
