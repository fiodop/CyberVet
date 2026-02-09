package com.cybervet.service.model;

import com.cybervet.model.User;
import com.cybervet.model.Pet;
import com.cybervet.model.dto.PetDto;
import com.cybervet.model.dto.ResponseDto;
import com.cybervet.repositry.PetRepository;
import com.cybervet.repositry.UserRepository;
import com.cybervet.service.keyboard.InlineKeyboardService;
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
    private final InlineKeyboardService inlineKeyboardService;
    @Transactional
    public void save(PetDto petDto) {
        Pet pet = new Pet(petDto);


        User user = userRepository.getAppUserByChatId(petDto.getChatId());
        List<Pet> pets = user.getPetList();
        pets.add(pet);
        user.setPetList(pets);
        userRepository.save(user);
    }

    @Transactional
    public ArrayList<Pet> getPets(long chatId) {


        return new ArrayList<>(petRepository.findAllByOwner_ChatId(chatId));
    }
}
