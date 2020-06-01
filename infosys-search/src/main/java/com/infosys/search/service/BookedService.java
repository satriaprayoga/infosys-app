package com.infosys.search.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.infosys.search.domain.Booked;
import com.infosys.search.repository.BookedRepository;

@Service
public class BookedService extends AbstractBaseService<Booked, String>{

	private final BookedRepository bookedRepository;
	private final ElasticsearchOperations elasticsearchOperations;
	
	public BookedService(BookedRepository bookedRepository,ElasticsearchOperations elasticsearchOperations) {
		setRepository(bookedRepository);
		this.bookedRepository=bookedRepository;
		this.elasticsearchOperations=elasticsearchOperations;
		setRepository(bookedRepository);
	}
	
	public Boolean isAvailable(String packageId, String bookDate, int max,int order) {
		List<Booked> bookeds=this.searchByBookDate(packageId, bookDate);
		if(bookeds.isEmpty()) {
			return true;
		}
		Booked b=bookeds.get(bookeds.size()-1);
		int exceed=b.getCurrentCapacity()+order;
		if(exceed>max) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	public List<Booked> searchByBookDate(String packageId,String bookDate){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery()
					.must(QueryBuilders
						  .matchQuery("packageId", packageId))
				  .must(QueryBuilders
						  .matchQuery("bookDate", bookDate));
		NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
					  .withQuery(queryBuilder).build();
		return elasticsearchOperations.queryForList(searchQuery, Booked.class);
	}
}
