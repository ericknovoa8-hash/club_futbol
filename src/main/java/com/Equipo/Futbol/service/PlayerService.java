package com.Equipo.Futbol.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Equipo.Futbol.dto.request.PlayerRequestDTO;
import com.Equipo.Futbol.dto.response.HttpGlobalResponseDTO;
import com.Equipo.Futbol.dto.response.MessageResponseDTO;
import com.Equipo.Futbol.dto.response.PlayerResponseDTO;
import com.Equipo.Futbol.entity.Player;
import com.Equipo.Futbol.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
/**
 * En esta clase se implementan los métodos para crear, obtener y actualizar jugadores.
 */
public class PlayerService {

    private final PlayerRepository playerRepository;
    /**
     * Este método se encarga de crear un nuevo jugador a partir de los datos recibidos en el request.
     * @param request
     * @return
     */
    public MessageResponseDTO createPlayer(PlayerRequestDTO request){
        
        MessageResponseDTO response = new MessageResponseDTO();
        Player player = new Player();
        player.setName(request.getName());
        player.setPositionPlayer(request.getPositionPlayer());
        player.setAge(request.getAge());
        playerRepository.save(player);
        response.setMessage("Jugador creado correctamente");
        return response;
    }
    /**
     * Este método se encarga de obtener la lista de jugadores registrados en la base de datos.
     * @return
     */
    public List<PlayerResponseDTO> getPlayers(){
        List<Player> players = playerRepository.findAll();
        List<PlayerResponseDTO> response = new ArrayList<>();
        for (Player player: players){
            PlayerResponseDTO dto = new PlayerResponseDTO();
            dto.setIdPlayer(player.getId());
            dto.setName(player.getName());
            dto.setPositionPlayer(player.getPositionPlayer());
            dto.setAge(player.getAge());
            response.add(dto);

        }
        return response;

    }
    /**
     * Este método se encarga de obtener un jugador específico a partir de su id.
     * @param id
     * @return
     */
    public HttpGlobalResponseDTO<PlayerResponseDTO> getPlayer(Long id){

        HttpGlobalResponseDTO<PlayerResponseDTO> response = new HttpGlobalResponseDTO<>();
        Player player = playerRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Jugador no encontrado"));
            PlayerResponseDTO responsePlayer = new PlayerResponseDTO();
            responsePlayer.setIdPlayer(player.getId());
            responsePlayer.setName(player.getName());
            responsePlayer.setPositionPlayer(player.getPositionPlayer());
            responsePlayer.setAge(player.getAge());
            response.setData(responsePlayer);
            response.setMessage("Jugador encontrado");
            return response;
    }

    /**
     * Este método se encarga de actualizar la información de un jugador específico a partir de su id y los datos recibidos en el request.
     * @param id
     * @param request
     * @return
     */
    public PlayerResponseDTO updatePlayer(Long id, PlayerRequestDTO request){
        Player player = playerRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Jugador no encontrado"));
        player.setName(request.getName());
        player.setPositionPlayer(request.getPositionPlayer());
        player.setAge(request.getAge());
        playerRepository.save(player);
        
        PlayerResponseDTO responsePlayer = new PlayerResponseDTO();
        responsePlayer.setIdPlayer(player.getId());
        responsePlayer.setName(player.getName());
        responsePlayer.setPositionPlayer(player.getPositionPlayer());
        responsePlayer.setAge(player.getAge());
        return responsePlayer;
    }

    /**
     * Este método se encarga de eliminar un jugador específico a partir de su id.
     * @param id
     * @return
     */
    public MessageResponseDTO deletePlayer(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();
        Player player = playerRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Jugador no encontrado"));
        playerRepository.delete(player);
        response.setMessage("Jugador eliminado correctamente");
        return response;
    }
}
        
    


