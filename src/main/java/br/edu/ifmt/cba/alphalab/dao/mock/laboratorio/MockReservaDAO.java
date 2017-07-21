package br.edu.ifmt.cba.alphalab.dao.mock.laboratorio;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifmt.cba.alphalab.dao.IReservaDAO;
import br.edu.ifmt.cba.alphalab.entity.equipamentos.EquipamentoEntity;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.DepartamentoEntity;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.EnumReserva;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.LaboratorioEntity;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.ReservaEntity;
import br.edu.ifmt.cba.alphalab.entity.pessoa.ServidorEntity;
import br.edu.ifmt.cba.alphalab.entity.software.SoftwareEntity;

/**
 * 
 * @author St�villis Sousa
 *
 */

public class MockReservaDAO implements IReservaDAO {
	private static List<ReservaEntity> reservas = new ArrayList<>();
	private static MockReservaDAO singleton = null;

	/*private static final ReservaEntity reserva1 = new ReservaEntity();	

	private static final ServidorEntity servidor1 = new ServidorEntity();	

	private static final DepartamentoEntity departamento1 = new DepartamentoEntity();	
	
	private static final LaboratorioEntity laboratorio1 = new LaboratorioEntity();
	
	private static final EquipamentoEntity equipamento1 = new EquipamentoEntity(1L, "Projetor");
	
	private static final SoftwareEntity software1 = new SoftwareEntity();

	static {
		software1.setId(1L);
		
		
		departamento1.setId(1L);
		departamento1.setSigla("DAI");
		departamento1.setNome("Departamento da �rea de Inform�tica");
		departamento1.setObservacao("Inform�tica");

		laboratorio1.setId(1L);
		laboratorio1.setNome("Laborat�rio 1");
		laboratorio1.setSituacao(EnumSituacaoLaboratorio.RESERVADO);
		laboratorio1.setCapacidade(30);
		laboratorio1.setObservacao("3 computadores com defeito.");
		laboratorio1.setDepartamento(departamento1);
		laboratorio1.setEquipamento(equipamento1);
		laboratorio1.setSoftware(software);
		
		servidor1.setId(1L);
		servidor1.setNome("Augusto C�sar de Oliveira");
		servidor1.setEmail("augustocesar.oliveira@cba.ifmt.edu.br");
		servidor1.setTelefone("(65) 9 8443-3466");
		servidor1.setLogin("augustocesar.oliveira");
		servidor1.setSenha("93ajbw@4");
		servidor1.setTipo(EnumTipoServidor.ESTAGIARIO);
		servidor1.setDepartamento(departamento1);

		reserva1.setId(1L);
		reserva1.setStatus(EnumReserva.Recusado);
		try {
			reserva1.setDataSolicitacao(new SimpleDateFormat("dd/MM/yyyy").parse("10/05/2017"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reserva1.setDisciplina("Algoritmos I");
		reserva1.setTurma("7844-1");
		reserva1.setObservacao("Extrema urg�ncia!");
		reserva1.setFixo(true);
		try {
			reserva1.setDataInicio(new SimpleDateFormat("dd/MM/yyyy").parse("13/05/2017"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			reserva1.setDataFim(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2017"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			reserva1.setDataAprovacaoRecusa(new SimpleDateFormat("dd/MM/yyyy").parse("11/05/2017"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reserva1.setJustificativa("N�o h� laborat�rio dispon�vel para extrema urg�ncia!");
		reserva1.setLaboratorio(laboratorio);
	
	}
	*/

	private MockReservaDAO() {

	}

	public static MockReservaDAO getInstance() {
		if (singleton == null)
			singleton = new MockReservaDAO();

		return singleton;
	}

	@Override
	public void save(ReservaEntity entity) {
		// Verifica se a reserva j� foi realizada.
		if (reservas.indexOf(entity) < 0) {
			reservas.add(entity);
		}
	}

	@Override
	public void delete(ReservaEntity entity) {
		reservas.remove(entity);
	}

	@Override
	public ReservaEntity getById(Long id) {
		// Percorre a lista � procura de uma Reserva.
		for (ReservaEntity vo : reservas)
			if (vo.getId().equals(id))
				return vo;
		return null;
	}

	@Override
	public List<ReservaEntity> buscarTodos() {
		return reservas;
	}
}
