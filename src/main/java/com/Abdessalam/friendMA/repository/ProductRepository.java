package com.Abdessalam.friendMA.repository;

import com.Abdessalam.friendMA.dto.model.ProductDto;
import com.Abdessalam.friendMA.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value= "(SELECT 'offer' as TYPE,id,price,user_id,city, availability,available_from, created_date FROM offer) UNION (SELECT 'demand',id,budget,user_id, city,availability,available_from,created_date FROM demand) ORDER BY created_date DESC", nativeQuery = true)
    Page<Product> findAllProducts(Pageable pageable);

    @Query(value= "SELECT * FROM ((SELECT 'offer' as TYPE,id,price,user_id,city, availability,available_from, created_date FROM offer)" +
            "UNION" +
            "(SELECT 'demand',id,budget,user_id, city,availability,available_from,created_date FROM demand))" +
            "AS u WHERE u.city = :city ORDER BY created_date DESC", nativeQuery = true)
    Page<Product> findAllProductsByCity(Pageable pageable,@Param("city") String city);
}
