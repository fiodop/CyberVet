package com.cybervet.service.model;

import com.cybervet.model.AppUser;
import com.cybervet.model.Pet;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.repositry.PetRepository;
import com.cybervet.repositry.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    @Transactional
    public void save(PetDto petDto) {
        Pet pet = new Pet(petDto);


        AppUser user = userRepository.getAppUserByChatId(petDto.getChatId());
        List<Pet> pets = user.getPetList();
        pets.add(pet);
        user.setPetList(pets);
        userRepository.save(user);
    }

    public ArrayList<ResponseDto> getPets(ArrayList<Pet> pets) {
        ArrayList<ResponseDto> responses = new ArrayList<>();
        for (Pet pet : pets) {
            ResponseDto responseDto = new ResponseDto();
            if(pet.getDiet() == null) {
                responseDto.setInlineKeyboardMarkup();
            }
        }
    }
}
