package br.edu.ifmt.cba.alphalab.dao;

import java.util.List;

import br.edu.ifmt.cba.alphalab.entity.PedidosReservaEntity;


/**
 * 
 * @author St�villis Sousa
 *
 */
public interface IPedidosReservaDAO extends IDAO<PedidosReservaEntity> {
	public List<PedidosReservaEntity> buscarTodos();
}
