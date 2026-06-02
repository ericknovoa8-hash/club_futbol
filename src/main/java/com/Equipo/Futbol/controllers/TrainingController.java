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
    /**
     * Maneja la solicitud de creación de un nuevo entrenamiento, recibiendo los datos del entrenamiento en el cuerpo de la solicitud y delegando la lógica de negocio al servicio correspondiente. Si la creación es exitosa, devuelve una respuesta con el mensaje de éxito; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
     * @param request
     * @return
     */
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
    /**
     * Maneja la solicitud de obtención de todos los entrenamientos, delegando la lógica de negocio al servicio correspondiente. Si la obtención es exitosa, devuelve una lista de entrenamientos en la respuesta; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
      * @return
     */
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
    /**
     *  Maneja la solicitud de obtención de un entrenamiento específico por su ID, recibiendo el ID del entrenamiento como parte de la URL y delegando la lógica de negocio al servicio correspondiente. Si la obtención es exitosa, devuelve el entrenamiento en la respuesta; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
     */
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
    /**
     *  Maneja la solicitud de actualización de un entrenamiento específico por su ID, recibiendo el ID del entrenamiento como parte de la URL y los nuevos datos del entrenamiento en el cuerpo de la solicitud. Delegando la lógica de negocio al servicio correspondiente, si la actualización es exitosa, devuelve el entrenamiento actualizado en la respuesta; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
     */
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
    /**
     * Maneja la solicitud de eliminación de un entrenamiento específico por su ID, recibiendo el ID del entrenamiento como parte de la URL y delegando la lógica de negocio al servicio correspondiente. Si la eliminación es exitosa, devuelve una respuesta con el mensaje de éxito; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
     */
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
