package com.vtw.pulsar.pss.search;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("serial")
public class DefaultSpecification<T> implements Specification<T> {
 
    private SearchCriteria criteria;
 
    public DefaultSpecification() {
	}
    
    public DefaultSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
    public Predicate toPredicate
      (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Predicate predicate = insertPredicate(criteria, root, query, builder);
		if (predicate != null) return predicate;
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
	
	protected Predicate insertPredicate(SearchCriteria criteria, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return null;
	}
}