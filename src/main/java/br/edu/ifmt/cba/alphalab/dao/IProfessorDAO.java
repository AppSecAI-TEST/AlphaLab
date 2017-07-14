package br.edu.ifmt.cba.alphalab.dao;

import java.util.List;

import br.edu.ifmt.cba.alphalab.entity.ProfessorEntity;

/**
 * 
 * @author St�villis Sousa
 *
 */

public interface IProfessorDAO extends IDAO<ProfessorEntity> {
	List<ProfessorEntity> getByNome(String nome);
}
