package br.edu.ifmt.cba.alphalab.dao;

import java.util.List;

import br.edu.ifmt.cba.alphalab.entity.laboratorio.LaboratorioEntity;

/**
 * 
 * @author StÚvillis Sousa
 *
 */

public interface ILaboratorioDAO extends IDAO<LaboratorioEntity> {
	LaboratorioEntity getbySigla(String sigla);
}