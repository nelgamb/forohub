package com.alura.ForoHub_Challenge.repository;

import com.alura.ForoHub_Challenge.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
