package com.Equipo.Futbol.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Equipo.Futbol.dto.request.TrainingsRequestDTO;
import com.Equipo.Futbol.dto.response.HttpGlobalResponseDTO;
import com.Equipo.Futbol.dto.response.MessageResponseDTO;
import com.Equipo.Futbol.dto.response.TrainingResponseDTO;
import com.Equipo.Futbol.service.TrainingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createTraining(
        @RequestBody TrainingsRequestDTO request) {
        /**
         * Crear entrenamiento con código 201
         */
        try {
            MessageResponseDTO response = trainingService.createTraining(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<TrainingResponseDTO>> getTrainings() {
        try {
            List<TrainingResponseDTO> trainings = trainingService.getTraining();
            return ResponseEntity.status(HttpStatus.OK).body(trainings);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpGlobalResponseDTO<TrainingResponseDTO>> getTraining(
        @PathVariable Long id) {
        try {
            HttpGlobalResponseDTO<TrainingResponseDTO> response = trainingService.getTraining(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            HttpGlobalResponseDTO<TrainingResponseDTO> error = new HttpGlobalResponseDTO<>();
            error.setMessage("Entrenamiento no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpGlobalResponseDTO<TrainingResponseDTO>> updateTraining(
        @PathVariable Long id,
        @RequestBody TrainingsRequestDTO request) {
        try {
            TrainingResponseDTO updateTraining = trainingService.updateTraining(id, request);
            HttpGlobalResponseDTO<TrainingResponseDTO> response = new HttpGlobalResponseDTO<>();
            response.setMessage("Entrenamiento actualizado correctamente");
            response.setData(updateTraining);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            HttpGlobalResponseDTO<TrainingResponseDTO> error = new HttpGlobalResponseDTO<>();
            error.setMessage("Error al actualizar el entrenamiento");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteTraining(@PathVariable Long id) {
        try {
            MessageResponseDTO response = trainingService.deleteTraining(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
