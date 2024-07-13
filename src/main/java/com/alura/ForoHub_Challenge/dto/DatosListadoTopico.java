package com.alura.ForoHub_Challenge.dto;

import com.alura.ForoHub_Challenge.model.Curso;
import com.alura.ForoHub_Challenge.model.Topico;
import com.alura.ForoHub_Challenge.model.Usuario;

import java.time.LocalDateTime;


public record DatosListadoTopico(
        Long id,
        Curso nombreCurso,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        String estado
) {


    public DatosListadoTopico(Topico topico){
        this(
                topico.getId(),
                topico.getNombreCurso(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getAutor().getNombre(),
                topico.getEstado() ? "Abierto" : "Cerrado"
        );
    }

}