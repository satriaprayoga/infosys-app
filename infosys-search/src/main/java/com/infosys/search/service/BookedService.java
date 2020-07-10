package com.infosys.search.service;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.infosys.search.domain.Booked;
import com.infosys.search.repository.BookedRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookedService extends AbstractBaseService<Booked, String>{

	private final BookedRepository bookedRepository;
	private final ElasticsearchOperations elasticsearchOperations;
	private RestHighLevelClient elasticsearchClient;
	
	public BookedService(BookedRepository bookedRepository,ElasticsearchOperations elasticsearchOperations,RestHighLevelClient elasticsearchClient) {
		setRepository(bookedRepository);
		this.bookedRepository=bookedRepository;
		this.elasticsearchOperations=elasticsearchOperations;
		this.elasticsearchClient=elasticsearchClient;
		setRepository(bookedRepository);
		
	}
	
	public Booked insert(Booked booked) throws IOException {
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String dateString=dateFormat.format(booked.getBookDate());
		//Map<String, Object> json=new HashMap<String, Object>();
		XContentBuilder builder = jsonBuilder()
			    .startObject()
			        .field("packageId", booked.getPackageId())
			        .field("bookDate", dateString)
			        .field("currentCapacity", booked.getCurrentCapacity())
			    .endObject();
		String source=Strings.toString(builder);
		log.info("######### data source: "+source);
		IndexRequest indexRequest = new IndexRequest("booked", "booked", UUID.randomUUID().toString())
                .source(source, XContentType.JSON);
	
		elasticsearchClient.index(indexRequest, RequestOptions.DEFAULT);
		return booked;
		
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
