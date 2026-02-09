package com.cybervet.service.model;

import com.cybervet.model.FoodExel;
import com.cybervet.repositry.FoodExelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodExelService {
    private final FoodExelRepository foodExelRepository;

    @Transactional
    public void save(FoodExel dto) {
        foodExelRepository.save(dto);
    }
}
