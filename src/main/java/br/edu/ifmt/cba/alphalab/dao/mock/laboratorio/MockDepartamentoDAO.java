package br.edu.ifmt.cba.alphalab.dao.mock.laboratorio;

import br.edu.ifmt.cba.alphalab.dao.IDepartamentoDAO;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.DepartamentoEntity;
import java.util.ArrayList;
import java.util.List;

public class MockDepartamentoDAO implements IDepartamentoDAO {
	private static ArrayList<DepartamentoEntity> listaDepartamentos = new ArrayList();
	private static MockDepartamentoDAO singleton = null;
	private static DepartamentoEntity departamento1 = new DepartamentoEntity();

	static {
		departamento1.setId(1L);
		departamento1.setSigla("DAI");
		departamento1.setNome("Departamento da �rea de Inform�tica");
		departamento1.setObservacao("Inform�tica");
		
	}

	public MockDepartamentoDAO() {
	}

	public static MockDepartamentoDAO getInstance() {
		if (singleton == null)
			singleton = new MockDepartamentoDAO();

		return singleton;
	}

	@Override
	public List<DepartamentoEntity> buscarTodos() {
		return listaDepartamentos;
	}

	@Override
	public void save(DepartamentoEntity entity) {
		if (listaDepartamentos.indexOf(entity) < 0)
			listaDepartamentos.add(entity);
	}

	@Override
	public void delete(DepartamentoEntity entity) {
		listaDepartamentos.remove(entity);
	}

	@Override
	public DepartamentoEntity getById(Long id) {
		for (DepartamentoEntity vo : listaDepartamentos)
			if (vo.getId().equals(id))
				return vo;
		return null;
	}
}
