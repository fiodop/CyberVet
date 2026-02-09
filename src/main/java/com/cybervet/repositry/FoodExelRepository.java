package com.cybervet.repositry;

import com.cybervet.model.FoodExel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodExelRepository extends JpaRepository<FoodExel, Integer> {

}
