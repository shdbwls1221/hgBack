package com.vtw.pulsar.pss.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class SearchBuilder<T> {
     
    private final List<SearchCriteria> params;
 
    private DefaultSpecification<T> spec;
    
    public SearchBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
    
    public SearchBuilder(DefaultSpecification<T> spec) {
    	this();
    	this.spec = spec;
    }
 
    private SearchBuilder<T> with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
 
	public Specification<T> build(String search) {
		params.clear();
		SearchCriteria.getSearches(search).forEach(s -> with(s.getKey(), s.getOperation(), s.getValue()));
		return build();
	}
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Specification<T> build() {
        if (params.size() == 0) {
            return null;
        }
       
        List<Specification<T>> specs = params.stream().map(param -> {
			@SuppressWarnings("serial")
			DefaultSpecification<T> defaultSpec = new DefaultSpecification<T>(param) {
				@Override
				protected Predicate insertPredicate(SearchCriteria criteria, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
					if (spec == null) return null;
					return spec.insertPredicate(criteria, root, query, builder);
				}
			};
			return defaultSpec;
		}).collect(Collectors.toList());
        
        Specification result = specs.get(0);
 
        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result)
                  .and(specs.get(i));
        }       
        return result;
    }
}