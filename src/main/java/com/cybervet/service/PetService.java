package com.cybervet.service;

import com.cybervet.model.Pet;
import com.cybervet.model.dto.PetDto;
import com.cybervet.repositry.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public void save(PetDto petDto) {
        Pet pet = new Pet();
        pet.setName(petDto.getName());
        pet.setAge(petDto.getAge());
        pet.setActivityLevel(petDto.getActivityLevel());
        pet.setBreed(petDto.getBreed());
        pet.setType(petDto.getType());
        pet.setCreatedAt(LocalDateTime.now());

    }
}
