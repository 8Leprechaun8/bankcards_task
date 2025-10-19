package com.example.bankcards.specification;

import com.example.bankcards.entity.Card;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CardSpecification implements Specification<Card> {

    private SearchCriteria criteria;

    public CardSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Card> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase("equals")) {
            if (criteria.getKey().equalsIgnoreCase("userId")) {
                return criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("user").get("id"), criteria.getValue()),
                        criteriaBuilder.isFalse(root.get("isArchived"))
                );
            }
        } else if (criteria.getOperation().equalsIgnoreCase("")) {
            return criteriaBuilder.isFalse(root.get("isArchived"));
        }
        return null;
    }
}
