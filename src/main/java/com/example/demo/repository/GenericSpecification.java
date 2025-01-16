package com.example.demo.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification<E>{

    public static <E> Specification<E> equalTo(String field, Object value)
    {
        return (Root<E> root,CriteriaQuery<?> query, CriteriaBuilder builder)->{
            if (value == null)
            {
                return null;
            }
            return builder.equal(root.get(field), value);
        };
    }

    public static <E> Specification<E> like(String field, String value){
        return (Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder)->
        {
            if(value == null || value.trim().isEmpty())
            {
                return null;
            }
            return builder.like(root.get(field), "%" + value + "%");
        };
    }

    public static <E> Specification<E> and(Specification<E> spec1, Specification<E> spec2) {
        return spec1.and(spec2);
    }

    public static <E> Specification<E> or(Specification<E> spec1, Specification<E> spec2) {
        return spec1.or(spec2);
    }
}
