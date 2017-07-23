package br.edu.ifmt.cba.alphalab.dao.mock.laboratorio;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifmt.cba.alphalab.dao.IRequisitoDAO;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.RequisitoEntity;

/**
 * 
 * @author StÚvillis Sousa
 *
 */

public class MockRequisitoDAO implements IRequisitoDAO {

	private static List<RequisitoEntity> requisitos = new ArrayList<>();
	private static MockRequisitoDAO singleton = null;

	public static MockRequisitoDAO getInstance() {
		if (singleton == null)
			singleton = new MockRequisitoDAO();

		return singleton;
	}

	@Override
	public void save(RequisitoEntity entity) {
		if (requisitos.indexOf(entity) < 0) {
			requisitos.add(entity);
		}
	}

	@Override
	public void delete(RequisitoEntity entity) {
		requisitos.remove(entity);
	}

	@Override
	public RequisitoEntity getById(Long id) {
		for (RequisitoEntity vo : requisitos)
			if (vo.getId().equals(id))
				return vo;
		return null;
	}

	@Override
	public List<RequisitoEntity> buscarTodos() {
		return requisitos;
	}
}