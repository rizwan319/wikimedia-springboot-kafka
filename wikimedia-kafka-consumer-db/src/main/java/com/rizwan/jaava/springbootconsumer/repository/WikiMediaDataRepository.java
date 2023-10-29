package com.rizwan.jaava.springbootconsumer.repository;

import com.rizwan.jaava.springbootconsumer.entity.WikiMediaData;
import org.springframework.data.jpa.repository.JpaRepository;

//Give CRUD Method for given entity
public interface WikiMediaDataRepository extends JpaRepository< WikiMediaData, Long> {

}
