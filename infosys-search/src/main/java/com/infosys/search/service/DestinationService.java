package com.infosys.search.service;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.infosys.search.domain.Destination;
import com.infosys.search.exceptions.ResourceNotFoundException;
import com.infosys.search.repository.DestinationRepository;

@Service
public class DestinationService extends AbstractBaseService<Destination, String>{

	private final DestinationRepository destinationRepository;
	private final ElasticsearchOperations elasticsearchOperations;
	
	public DestinationService(DestinationRepository destinationRepository,ElasticsearchOperations elasticsearchOperations) {
		setRepository(destinationRepository);
		this.destinationRepository=destinationRepository;
		this.elasticsearchOperations=elasticsearchOperations;
	}
	
	public Destination findOneById(String id) {
		return destinationRepository.findOneById(id).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException("Destination", "Not Found");
		});
	}
	
	public Destination findOneByCode(String code) {
		return destinationRepository.findOneByCode(code).<ResourceNotFoundException>orElseThrow(()->{
			throw new ResourceNotFoundException("Destination", "Not Found");
		});
	}
	
	public List<Destination> findByDestinationOrLocation(String query){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
				  .should(QueryBuilders
						  .matchQuery("destination", query)
						  .fuzziness(Fuzziness.TWO))
				  .should(QueryBuilders
						  .matchQuery("location", query)
						  .fuzziness(Fuzziness.TWO));
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return elasticsearchOperations.queryForList(searchQuery, Destination.class);
	}
	
	public long count() {
		return destinationRepository.count();
	}

	
	
}
