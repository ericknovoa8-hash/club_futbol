package com.Equipo.Futbol.controllers;



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
import com.Equipo.Futbol.dto.request.PlayerRequestDTO;
import com.Equipo.Futbol.dto.response.HttpGlobalResponseDTO;
import com.Equipo.Futbol.dto.response.MessageResponseDTO;
import com.Equipo.Futbol.dto.response.PlayerResponseDTO;
import com.Equipo.Futbol.service.PlayerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
/**
 * En esta clase se implementan los endpoints para crear, obtener y actualizar jugadores.
 */
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    /**
     * Este método se encarga de crear un nuevo jugador a partir de los datos recibidos en el request y devuelve una respuesta con el mensaje correspondiente.
     * @param request
     * @return
     */

    @RequiresRole("ADMIN")
    public ResponseEntity<MessageResponseDTO> createPlayer(
        @RequestBody PlayerRequestDTO request){
        /**
         * crear los jugadores con los codigos 201
         */
        try{
            MessageResponseDTO response = playerService.createPlayer(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }
    @GetMapping("/{id}")
    /**
     * Este método se encarga de obtener un jugador específico a partir de su id y devuelve una respuesta con el mensaje correspondiente y los datos del jugador.
     * @param id
     * @return
     */
    
    public ResponseEntity<HttpGlobalResponseDTO<PlayerResponseDTO>>getPlayer(@PathVariable Long id) {
        try {
            HttpGlobalResponseDTO<PlayerResponseDTO> response = playerService.getPlayer(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch(Exception e) {
            HttpGlobalResponseDTO<PlayerResponseDTO>error = new HttpGlobalResponseDTO<>();
            error.setMessage("Jugador no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PutMapping("/{id}")
    /**
     * Este método se encarga de actualizar la información de un jugador específico a partir de su id y los datos 
     * recibidos en el request, y devuelve una respuesta con el mensaje correspondiente y los datos del jugador actualizado.
     * @param id
     * @param request
     * @return
     */
    public ResponseEntity<HttpGlobalResponseDTO<PlayerResponseDTO>>updatePlayer(
        @PathVariable Long id, 
        @RequestBody PlayerRequestDTO request){

        try{
            PlayerResponseDTO updatePlayer = playerService.updatePlayer(id, request);
            HttpGlobalResponseDTO<PlayerResponseDTO> response = new HttpGlobalResponseDTO<>();
            response.setMessage("Se actualiza el jugador");
            response.setData(updatePlayer);
            return ResponseEntity.ok(response);
            
        } catch (Exception e){
            HttpGlobalResponseDTO<PlayerResponseDTO> error = new HttpGlobalResponseDTO<>();
            error.setMessage("Error al actualizar el Jugador");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deletePlayer(@PathVariable Long id) {
    try {
        // Llama al servicio de eliminar que creamos hace unos pasos
        MessageResponseDTO response = playerService.deletePlayer(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
        
        } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
