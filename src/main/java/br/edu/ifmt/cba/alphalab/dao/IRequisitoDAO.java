package br.edu.ifmt.cba.alphalab.dao;

import java.util.List;

import br.edu.ifmt.cba.alphalab.entity.laboratorio.RequisitoEntity;

/**
 * 
 * @author St�villis Sousa
 *
 */

public interface IRequisitoDAO extends IDAO<RequisitoEntity> {
	public List<RequisitoEntity> buscarTodos();
}