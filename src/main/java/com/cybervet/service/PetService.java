package com.cybervet.service;

import com.cybervet.model.AppUser;
import com.cybervet.model.Pet;
import com.cybervet.model.dto.PetDto;
import com.cybervet.repositry.PetRepository;
import com.cybervet.repositry.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public void save(PetDto petDto) {
        Pet pet = new Pet(petDto);


        AppUser user = userRepository.getAppUserByChatId(petDto.getChatId());
        List<Pet> pets = user.getPetList();
        pets.add(pet);
        user.setPetList(pets);
        userRepository.save(user);
    }
}
