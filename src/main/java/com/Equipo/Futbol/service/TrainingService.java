package com.Equipo.Futbol.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Equipo.Futbol.dto.request.TrainingsRequestDTO;
import com.Equipo.Futbol.dto.response.HttpGlobalResponseDTO;
import com.Equipo.Futbol.dto.response.MessageResponseDTO;
import com.Equipo.Futbol.dto.response.TrainingResponseDTO;
import com.Equipo.Futbol.entity.Training;
import com.Equipo.Futbol.repository.TrainingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public MessageResponseDTO createTraining(TrainingsRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Training training = new Training();
        training.setTrainingDate(request.getTrainingDate());
        training.setDescriptionTraining(request.getDescriptionTraining());
        trainingRepository.save(training);
        response.setMessage("Entrenamiento guardado correctamente");
        return response;

    }
    public List<TrainingResponseDTO> getTraining(){
        List<Training>trainings = trainingRepository.findAll();
        List<TrainingResponseDTO> response = new ArrayList<>();
        for (Training training: trainings){
            TrainingResponseDTO dto = new TrainingResponseDTO();
            dto.setIdTraining(training.getId());
            dto.setTrainingDate(training.getTrainingDate());
            dto.setDescriptionTraining(training.getDescriptionTraining());
            response.add(dto);
        }
        return response;
    }
    public HttpGlobalResponseDTO<TrainingResponseDTO> getTraining(Long id){
        HttpGlobalResponseDTO<TrainingResponseDTO> response = new HttpGlobalResponseDTO<>();
        Training training = trainingRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Entrenamiento no encontrado"));
        TrainingResponseDTO dto = new TrainingResponseDTO();
        dto.setIdTraining(training.getId());
        dto.setTrainingDate(training.getTrainingDate());
        dto.setDescriptionTraining(training.getDescriptionTraining());
        response.setMessage("Entrenamiento encontrado");
        response.setData(dto);
        return response;
    }
    public TrainingResponseDTO updateTraining(Long id, TrainingsRequestDTO request){
        Training training = trainingRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Entrenamiento no encontrado"));
        training.setTrainingDate(request.getTrainingDate());
        training.setDescriptionTraining(request.getDescriptionTraining());
        trainingRepository.save(training);
        TrainingResponseDTO dto = new TrainingResponseDTO();
        dto.setIdTraining(training.getId());
        dto.setTrainingDate(training.getTrainingDate());
        dto.setDescriptionTraining(training.getDescriptionTraining());
        return dto;
    }

    public MessageResponseDTO deleteTraining(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();
        Training training = trainingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrenamiento no encontrado"));
        trainingRepository.delete(training);
        response.setMessage("Entrenamiento eliminado correctamente");
        return response;
    }
}
