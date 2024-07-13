package com.alura.ForoHub_Challenge.controller;

import com.alura.ForoHub_Challenge.dto.DatosActualizarTopico;
import com.alura.ForoHub_Challenge.dto.DatosDetalleTopico;
import com.alura.ForoHub_Challenge.dto.DatosListadoTopico;
import com.alura.ForoHub_Challenge.dto.DatosRegistroTopico;
import com.alura.ForoHub_Challenge.model.Topico;
import com.alura.ForoHub_Challenge.repository.TopicoRepository;
import com.alura.ForoHub_Challenge.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;


    @PostMapping
    public ResponseEntity<DatosDetalleTopico> RegistrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                              UriComponentsBuilder uriComponentsBuilder){
        var topico = topicoService.crearTopico(datosRegistroTopico);
        DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico.getId(), topico.getNombreCurso(),
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado() ? "Abierto" : "Cerrado");
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetalleTopico);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> retornaDatosTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosDetalleTopico(topico.getId(), topico.getNombreCurso(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado() ? "Abierto" : "Cerrado");
        return ResponseEntity.ok(datosTopico);
    }



    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok(new DatosDetalleTopico(topico.getId(), topico.getNombreCurso(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado() ? "Abierto" : "Cerrado"));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoService.validarExistencia(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 3) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

}