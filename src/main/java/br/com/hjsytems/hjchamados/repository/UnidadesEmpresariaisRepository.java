/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hjsytems.hjchamados.repository;

import br.com.hjsytems.hjchamados.entity.UnidadesEmpresariais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Heugenio
 */
@Repository
public interface UnidadesEmpresariaisRepository extends JpaRepository<UnidadesEmpresariais, Integer> {
    
}
