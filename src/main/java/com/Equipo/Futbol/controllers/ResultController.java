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

import com.Equipo.Futbol.Security.RequiresRole;
import com.Equipo.Futbol.dto.request.ResultRequestDTO;
import com.Equipo.Futbol.dto.response.HttpGlobalResponseDTO;
import com.Equipo.Futbol.dto.response.MessageResponseDTO;
import com.Equipo.Futbol.dto.response.ResultResponseDTO;
import com.Equipo.Futbol.service.ResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {
    /**
     * Servicio de resultados que maneja la lógica de negocio relacionada con la creación, obtención, actualización y eliminación de resultados de partidos.
     */
    private final ResultService resultService;
    /**
     * Maneja la solicitud de creación de un nuevo resultado, recibiendo los datos del resultado en el cuerpo de la solicitud y delegando la lógica de negocio al servicio correspondiente. Si la creación es exitosa, devuelve una respuesta con el mensaje de éxito; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
     * @param request
     * @return
     */
    @RequiresRole("ROLE_CLIENTE")
    @PostMapping
    public ResponseEntity<MessageResponseDTO> createResult(
        @RequestBody ResultRequestDTO request) {
        try {
            MessageResponseDTO response = resultService.createResult(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    /**
     * Maneja la solicitud de obtención de todos los resultados, delegando la lógica de negocio al servicio correspondiente. Si la obtención es exitosa, devuelve una lista de resultados en la respuesta; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ResultResponseDTO>> getResults() {
        try {
            List<ResultResponseDTO> results = resultService.getResults();
            return ResponseEntity.status(HttpStatus.OK).body(results);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    /**
     *  Maneja la solicitud de obtención de un resultado específico por su ID, recibiendo el ID del resultado como parte de la URL y delegando la lógica de negocio al servicio correspondiente. Si la obtención es exitosa, devuelve el resultado en la respuesta; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<HttpGlobalResponseDTO<ResultResponseDTO>> getResult(
        @PathVariable Long id) {
        try {
            HttpGlobalResponseDTO<ResultResponseDTO> response = resultService.getResult(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            HttpGlobalResponseDTO<ResultResponseDTO> error = new HttpGlobalResponseDTO<>();
            error.setMessage("Resultado no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    /**
     *  Maneja la solicitud de actualización de un resultado específico por su ID, recibiendo el ID del resultado como parte de la URL y los nuevos datos del resultado en el cuerpo de la solicitud. Delegando la lógica de negocio al servicio correspondiente, si la actualización es exitosa, devuelve el resultado actualizado en la respuesta; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<HttpGlobalResponseDTO<ResultResponseDTO>> updateResult(
        @PathVariable Long id,
        @RequestBody ResultRequestDTO request) {
        try {
            ResultResponseDTO updateResult = resultService.updateResult(id, request);
            HttpGlobalResponseDTO<ResultResponseDTO> response = new HttpGlobalResponseDTO<>();
            response.setMessage("Resultado actualizado correctamente");
            response.setData(updateResult);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            HttpGlobalResponseDTO<ResultResponseDTO> error = new HttpGlobalResponseDTO<>();
            error.setMessage("Error al actualizar el resultado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    /**
     *  Maneja la solicitud de eliminación de un resultado específico por su ID, recibiendo el ID del resultado como parte de la URL y delegando la lógica de negocio al servicio correspondiente. Si la eliminación es exitosa, devuelve una respuesta con el mensaje de éxito; si ocurre algún error, se captura la excepción y se devuelve una respuesta de error adecuada.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteResult(@PathVariable Long id) {
        try {
            MessageResponseDTO response = resultService.deleteResult(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
