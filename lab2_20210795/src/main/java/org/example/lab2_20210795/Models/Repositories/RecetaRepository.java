package org.example.lab2_20210795.Models.Repositories;

import org.example.lab2_20210795.Models.Entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
}
