package com.library.library.repository;

import com.library.library.models.Shelf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepo extends CrudRepository<Shelf, Long> {
}
