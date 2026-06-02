package com.Equipo.Futbol.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Equipo.Futbol.dto.request.ResultRequestDTO;
import com.Equipo.Futbol.dto.response.HttpGlobalResponseDTO;
import com.Equipo.Futbol.dto.response.MessageResponseDTO;
import com.Equipo.Futbol.dto.response.ResultResponseDTO;
import com.Equipo.Futbol.entity.Player;
import com.Equipo.Futbol.entity.Result;
import com.Equipo.Futbol.entity.Training;
import com.Equipo.Futbol.repository.PlayerRepository;
import com.Equipo.Futbol.repository.ResultRepository;
import com.Equipo.Futbol.repository.TrainingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final PlayerRepository playerRepository;
    private final TrainingRepository trainingRepository;

    public MessageResponseDTO createResult(ResultRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        
        Player player = playerRepository.findById(request.getIdPlayer())
            .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        
        Training training = trainingRepository.findById(request.getIdTraining())
            .orElseThrow(() -> new RuntimeException("Entrenamiento no encontrado"));
        /**
         * aqui manejamos la logica para sacar el promedio de los 
         * jugadores para el equipo titular.
         */
        Double resultado = 
        (request.getShootingPower() * 0.20) +
        (request.getSpeedPlayer() * 0.30) +
        (request.getEffectivePasses() * 0.50);
        
        Result result = new Result();
        
        result.setPlayer(player);
        result.setTraining(training);
        result.setShootingPower(request.getShootingPower());
        result.setSpeedPlayer(request.getSpeedPlayer());
        result.setEffectivePasses(request.getEffectivePasses());
        result.setResultScore(request.getResultScore());// Guardamos el resultado calculado en la entidad Result
        
        resultRepository.save(result);
        response.setMessage("Resultado guardado correctamente");
        return response;
    }

    public List<ResultResponseDTO> getResults() {
        List<Result> results = resultRepository.findAll();
        List<ResultResponseDTO> response = new ArrayList<>();
        
        for (Result result : results) {
            ResultResponseDTO dto = new ResultResponseDTO();
            dto.setIdResult(result.getId());
            dto.setIdPlayer(result.getPlayer().getId());
            dto.setIdTraining(result.getTraining().getId());
            dto.setShootingPower(result.getShootingPower());
            dto.setSpeedPlayer(result.getSpeedPlayer());
            dto.setEffectivePasses(result.getEffectivePasses());
            response.add(dto);
        }
        return response;
    }

    public HttpGlobalResponseDTO<ResultResponseDTO> getResult(Long id) {
        HttpGlobalResponseDTO<ResultResponseDTO> response = new HttpGlobalResponseDTO<>();
        
        Result result = resultRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
        
        ResultResponseDTO dto = new ResultResponseDTO();
        dto.setIdResult(result.getId());
        dto.setIdPlayer(result.getPlayer().getId());
        dto.setIdTraining(result.getTraining().getId());
        dto.setShootingPower(result.getShootingPower());
        dto.setSpeedPlayer(result.getSpeedPlayer());
        dto.setEffectivePasses(result.getEffectivePasses());
        
        response.setMessage("Resultado encontrado");
        response.setData(dto);
        return response;
    }

    public ResultResponseDTO updateResult(Long id, ResultRequestDTO request) {
        Result result = resultRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
        
        Player player = playerRepository.findById(request.getIdPlayer())
            .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        
        Training training = trainingRepository.findById(request.getIdTraining())
            .orElseThrow(() -> new RuntimeException("Entrenamiento no encontrado"));
        /**
         * actualizamos el resultado de los jugadores para el 
         * equpo titular.
         */
        Double resultScore =
            (request.getShootingPower() * 0.20) +
            (request.getSpeedPlayer() * 0.30) + 
            (request.getEffectivePasses() * 0.50);

        result.setPlayer(player);
        result.setTraining(training);
        result.setShootingPower(request.getShootingPower());
        result.setSpeedPlayer(request.getSpeedPlayer());
        result.setEffectivePasses(request.getEffectivePasses());

        result.setResultScore(resultScore);// Actualizamos el resultado calculado en la entidad Result
        
        resultRepository.save(result);
        
        ResultResponseDTO dto = new ResultResponseDTO();
        dto.setIdResult(result.getId());
        dto.setIdPlayer(result.getPlayer().getId());
        dto.setIdTraining(result.getTraining().getId());
        dto.setShootingPower(result.getShootingPower());
        dto.setSpeedPlayer(result.getSpeedPlayer());
        dto.setEffectivePasses(result.getEffectivePasses());
        dto.setResultScore(result.getResultScore());
        
        return dto;
    }

    public MessageResponseDTO deleteResult(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();
        
        Result result = resultRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
        
        resultRepository.delete(result);
        response.setMessage("Resultado eliminado correctamente");
        return response;
    }
}
