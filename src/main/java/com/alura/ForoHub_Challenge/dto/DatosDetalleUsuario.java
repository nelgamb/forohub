package com.alura.ForoHub_Challenge.dto;

import com.alura.ForoHub_Challenge.model.Curso;
import com.alura.ForoHub_Challenge.model.Topico;

import java.time.LocalDateTime;


public record DatosDetalleUsuario(
        String nombre,
        String password
) {


}