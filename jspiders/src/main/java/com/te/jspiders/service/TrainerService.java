package com.te.jspiders.service;

import com.te.jspiders.dto.TrainerDTO;

import java.util.Optional;

public interface TrainerService {
    Optional<String> registerTrainer(TrainerDTO trainerDTO);
}
