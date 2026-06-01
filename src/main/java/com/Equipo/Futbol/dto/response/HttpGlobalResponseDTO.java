package com.Equipo.Futbol.dto.response;

import lombok.Data;

@Data
/**
 * DTO para enviar una respuesta HTTP global que incluye un mensaje y datos genéricos.
 * @param <T> El tipo de datos que se incluirá en la respuesta.
 */
public class HttpGlobalResponseDTO<T> {
    private String message;
    private T data;
}

    