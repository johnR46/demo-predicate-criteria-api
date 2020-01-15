package com.stream.functional.demo.reposistory;

import com.stream.functional.demo.domain.Item;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ItemReposistory extends CrudRepository<Item, Long>, JpaSpecificationExecutor<Item> {


}
