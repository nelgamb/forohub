package com.alura.ForoHub_Challenge.model;

import com.alura.ForoHub_Challenge.dto.DatosActualizarTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Curso nombreCurso;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean estado;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Usuario autor;


    public Topico(Curso nombreCurso, String titulo, String mensaje, Usuario autor){
        this.nombreCurso = nombreCurso;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = true;
        this.autor = autor;
    }


    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico){
        if (datosActualizarTopico.titulo() != null){
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
    }
}





/*
Mas informacion sobre la dependencia Flyway para las migraciones:
Importante: Detener la autoejectucion si se tiene activada con la dependencia devtools, para poder
escribir el codigo completo dentro de nuestro archivo.
El archivo sql es leido por flyway y lo ejecuta en la BD que ya se tiene conexion. Para crear este
archivo y que sea reconocido por flyway, se tiene que seguir cierta nomeclatura:
V1 por ser la primera migracion, version 1, seguida de dos guiones bajos, luego con un nombre autoexplicativo
de lo que contiene o hace el codigo y finalmente la extension .sql
Ejemplo V1__create-table-topicos.sql
En caso de error en el codigo de migrations, sera necesario acceder a la BD de la aplicacion y ejecutar
delete from flyway_schema_history where success = 0;
para eliminar de la tabla Flyway todas las migraciones cuya ejecución falló.
Después de eso, simplemente corregir el código de migración y ejecutar el proyecto nuevamente.
*/