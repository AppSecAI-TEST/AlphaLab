package br.edu.ifmt.cba.alphalab.dao;

import br.edu.ifmt.cba.alphalab.entity.equipamentos.EquipamentoEntity;

/**
 * 
 * @author St�villis Sousa
 *
 */

public interface IEquipamentoDAO extends IDAO<EquipamentoEntity> {
	EquipamentoEntity getByNome(String nome);
}