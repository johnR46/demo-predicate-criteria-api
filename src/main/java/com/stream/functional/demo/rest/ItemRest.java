package com.stream.functional.demo.rest;

import com.stream.functional.demo.domain.Item;
import com.stream.functional.demo.reposistory.ItemReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/criteria-api")
public class ItemRest {


//    @PersistenceContext
//    private EntityManager em;


    @Autowired
    private ItemReposistory itemReposistory;

//    @GetMapping("/find-all")
//    public ResponseEntity<?> findAll() {
//
//        // # SetupQuery
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
//        Root<Item> itemRoot = criteriaQuery.from(Item.class);
//
//        //# build a Predicate to find items
//        Predicate predicateForBlueColor = criteriaBuilder.equal(itemRoot.get("color"), "blue");
//        Predicate predicateForRedColor = criteriaBuilder.equal(itemRoot.get("color"), "red");
//        Predicate predicateForColor = criteriaBuilder.or(predicateForBlueColor, predicateForRedColor);
//
//        // Predicate to find items of grade A or B:
//        Predicate predicateForGradeA = criteriaBuilder.equal(itemRoot.get("grade"), "A");
//        Predicate predicateForGradeB = criteriaBuilder.equal(itemRoot.get("grade"), "B");
//        Predicate predicateForGrade = criteriaBuilder.or(predicateForGradeA, predicateForGradeB);
//
//        Predicate finalPredicate = criteriaBuilder.and(predicateForColor, predicateForGrade);
//        criteriaQuery.where(finalPredicate);
//
//        List<Item> items = em.createQuery(criteriaQuery).getResultList();
//
//        return ResponseEntity.ok().body(items);
//
//
//    }

    public List<Item> findByCriteria(String grade, String color) {
        return this.itemReposistory.findAll(new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                // JPA dynamic with equal and like (
                /*
                select * from item where
                 color like '%'a'%'  and grade = 'a'
                 */

//                if (color != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("color"), "%" + color + "%")));
//                }
//                if (grade != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("grade"), grade)));
//                }

                //JPA dynamic like for multiple fields or field

                  /*
                select * from item where
                 color like '%'a'%'  or grade = '%a%'
                 */
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("grade"), "%" + grade + "%"),
                        criteriaBuilder.like(root.get("color"), "%" + color + "%")
                ));


                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findall() {
        // null to find all
        return ResponseEntity.ok().body(findByCriteria("", ""));
    }

}
