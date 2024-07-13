package com.alura.ForoHub_Challenge.dto;

import jakarta.validation.constraints.NotBlank;


public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
