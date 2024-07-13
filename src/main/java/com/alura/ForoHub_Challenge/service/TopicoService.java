package com.alura.ForoHub_Challenge.service;

import com.alura.ForoHub_Challenge.dto.DatosRegistroTopico;
import com.alura.ForoHub_Challenge.model.Topico;
import com.alura.ForoHub_Challenge.repository.TopicoRepository;
import com.alura.ForoHub_Challenge.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TopicoService {
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;


    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {
        var autor = usuarioRepository.getReferenceById(datosRegistroTopico.idUsuario());
        var topico = new Topico(
                datosRegistroTopico.nombreCurso(),
                datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje(),
                autor
        );
        return topicoRepository.save(topico);
    }



    public Topico validarExistencia(Long id) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            return topico;
        }
        throw new EntityNotFoundException();
    }


}