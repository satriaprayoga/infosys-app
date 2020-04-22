package com.infosys.data.spec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

	private String key;
    private Object value;
    private SearchOperation operation;
}
