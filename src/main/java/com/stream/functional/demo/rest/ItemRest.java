package com.stream.functional.demo.rest;

import com.stream.functional.demo.domain.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping(value = "/criteria-api")
public class ItemRest {


    @PersistenceContext
    private EntityManager em;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {

        // # SetupQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = criteriaQuery.from(Item.class);

        //# build a Predicate to find items
        Predicate predicateForBlueColor = criteriaBuilder.equal(itemRoot.get("color"), "blue");
        Predicate predicateForRedColor = criteriaBuilder.equal(itemRoot.get("color"), "red");
        Predicate predicateForColor = criteriaBuilder.or(predicateForBlueColor, predicateForRedColor);

        // Predicate to find items of grade A or B:
        Predicate predicateForGradeA = criteriaBuilder.equal(itemRoot.get("grade"), "A");
        Predicate predicateForGradeB = criteriaBuilder.equal(itemRoot.get("grade"), "B");
        Predicate predicateForGrade = criteriaBuilder.or(predicateForGradeA, predicateForGradeB);

        Predicate finalPredicate = criteriaBuilder.and(predicateForColor, predicateForGrade);
        criteriaQuery.where(finalPredicate);
        List<Item> items = em.createQuery(criteriaQuery).getResultList();

        return ResponseEntity.ok().body(items);


    }

}
