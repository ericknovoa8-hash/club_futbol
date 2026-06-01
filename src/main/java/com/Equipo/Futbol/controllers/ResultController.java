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

    private final ResultService resultService;

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
