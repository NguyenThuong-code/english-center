package com.example.centerenglish.specification;

import com.example.centerenglish.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserRequestSpecification {

    public static Specification<User> nameLike(String nameFilter) {
        if (nameFilter == null || nameFilter.isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + nameFilter + "%");
    }
}
