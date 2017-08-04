package br.edu.ifmt.cba.alphalab.gui.javafx.controller.laboratorio;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.alphalab.business.Reserva;
import br.edu.ifmt.cba.alphalab.business.Software;
import br.edu.ifmt.cba.alphalab.dao.DAOFactory;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.DepartamentoEntity;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.Horario;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.RequisitoEntity;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.ReservaEntity;
import br.edu.ifmt.cba.alphalab.entity.software.SoftwareEntity;
import br.edu.ifmt.cba.alphalab.gui.javafx.controller.exemplo.FrmPrincipal;
import br.edu.ifmt.cba.alphalab.gui.javafx.controller.laboratorio.adapter.SoftwareCheckTableView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * 
 * @author St�villis Sousa
 *
 */

public class FrmSolicitarReservaHorarioPorRequisito implements Initializable {
	ResourceBundle resources = ResourceBundle.getBundle(FrmPrincipal.LINGUA_PORTUGUES);

	// Lista de bot�es contidos na TableColumn tbcDiaSemana
	private List<ToggleButton> listaBotoes = new ArrayList<>();

	// Lista dos bot�es pressionados na TableColumn tbcDiaSemana
	private List<ToggleButton> listaSelecionados = new ArrayList<>();

	private Software software = new Software(DAOFactory.getDAOFactory().getSoftwareDAO());

	private Date dtSolicitacaoReserva = null;
	private List<RequisitoEntity> listaRequisitos = new ArrayList<>();
	private List<SoftwareEntity> listaSoftwaresSelecionados = new ArrayList<>();
	private Integer numMaxAlunos = 0, idSoftwares = 100;

	@FXML
	private TabPane tabPaneDados;

	@FXML
	private Tab tabRequisitos;

	@FXML
	private DatePicker dtpData;

	@FXML
	private TableView<Horario> tblHorarioRequisitos;

	@FXML
	private TableColumn<Horario, String> tbcHorario;

	@FXML
	private TableColumn<Horario, ToggleButton> tbcDiaSemana;

	@FXML
	private TextField txtNomeSoftware;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableView<SoftwareCheckTableView> tblRequisitos;

	@FXML
	private TableColumn<SoftwareCheckTableView, Boolean> tbcSelecionado;

	@FXML
	private TableColumn<SoftwareCheckTableView, String> tbcNome;

	@FXML
	private TableColumn<SoftwareCheckTableView, String> tbcTipo;

	@FXML
	private TextField txtNumMaxAlunos;

	@FXML
	private Button btnProximoRequisitos;

	@FXML
	private Tab tabPreencherDados;

	@FXML
	private Text texRequisitos;

	@FXML
	private HBox hbxHorarios;

	@FXML
	private TextField txtDisciplina;

	@FXML
	private ComboBox<DepartamentoEntity> cmbDepartamento;

	@FXML
	private TextField txtTurma;

	@FXML
	private TextArea txaObservacao;

	@FXML
	private CheckBox ckbFixo;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnConfirmar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillColumnHorario();

		// Adiciona a coluna Nome do Software
		tbcNome.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<SoftwareCheckTableView, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<SoftwareCheckTableView, String> param) {

						return new ReadOnlyObjectWrapper<String>(param.getValue().getDescricao());
					}
				});

		// Adiciona a coluna Tipo do Software
		tbcTipo.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<SoftwareCheckTableView, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<SoftwareCheckTableView, String> param) {
						return new ReadOnlyObjectWrapper<String>("Editor de Texto");
					}
				});

		// tbcSelecionado = new TableColumn<SoftwareEntity, Boolean>();
		tbcSelecionado.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<SoftwareCheckTableView, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<SoftwareCheckTableView, Boolean> param) {
						return new ReadOnlyObjectWrapper<Boolean>(param.getValue().getSelecionado());
					}
				});

		buscarSoftwares();
	}

	/**
	 * Atualiza o TableView de Softwares
	 */
	private void buscarSoftwares() {
		List<SoftwareEntity> listaSoftwareEntity = null;
		listaSoftwareEntity = (List<SoftwareEntity>) software.getByNome(txtNomeSoftware.getText());

		tblRequisitos.setItems(FXCollections.observableArrayList(SoftwareCheckTableView.convert(listaSoftwareEntity)));
	}

	/**
	 * Id relacionado � cada software selecionado na lista de tblRequisitos.
	 * 
	 * @return um ID do objeto selecionado.
	 */
	private Integer getIdSoftwares() {
		idSoftwares++;
		return idSoftwares;
	}

	/**
	 * Mostra uma caixa de di�logo perguntando se o usu�rio realmente deseja
	 * cancelar a solicita��o de reserva de hor�rio.
	 */
	private void recusarPedidoReserva() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType sim = new ButtonType("Sim");
		ButtonType nao = new ButtonType("N�o");
		alerta.setTitle("AlphaLab");
		alerta.setHeaderText("Cancelar pedido de Reserva de Hor�rio");
		alerta.setContentText("Deseja cancelar o pedido de Reserva de Hor�rio?");
		alerta.getButtonTypes().setAll(sim, nao);

		alerta.showAndWait().ifPresent(opcao -> {
			if (opcao == sim) {
				// this.tblRequisitos.getSelectionModel().getSelectedItem().setStatus(Enum.CANCELADA);
			}
			if (opcao == nao) {
			}
		});
	}

	// Limpa os campos da tela.
	private void limparCampos() {
		dtpData.setValue(null);
		tblHorarioRequisitos.setItems(null);
		txtNomeSoftware.setText("");
		tblRequisitos.setItems(null);
		txtNumMaxAlunos.setText("");
		texRequisitos.setText("");
		// hbxHorarios
		txtDisciplina.setText("");
		cmbDepartamento.setValue(null);
		txtTurma.setText("");
		txaObservacao.setText("");
		ckbFixo.setSelected(false);
	}

	// Comp�e os dados da solicita��o de Reserva de Hor�rio
	private void comporDados() {

	}

	/**
	 * Guarda os dados selecionados pelo usu�rio na Tab tabRequisitos.<br>
	 * <b>Dados:</b> Data, Hor�rio, Softwares e N�mero M�ximo de Alunos.
	 */
	private void getDadosTabRequisitos() {
		dtSolicitacaoReserva = Date.from(dtpData.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		// lista de horar�rios selecionados
		// lista de softwares selecionados
		numMaxAlunos = Integer.parseInt(txtNumMaxAlunos.getText());
		tabRequisitos.setDisable(true);
		tabPaneDados.getSelectionModel().select(tabPreencherDados);
		// Preencher requisitos e hor�rios selecionados na Tab TabPreencherDados
	}

	private ReservaEntity getDadosTabPreencherDados() {
		ReservaEntity reservaEntity = new ReservaEntity();

		reservaEntity.setDataSolicitacao(dtSolicitacaoReserva);
		// Hor�rios da reserva
		// Requisitos da reserva
		// Num m�x. de alunos
		reservaEntity.setDisciplina(txtDisciplina.getText());
		reservaEntity.setDepartamentoAula(cmbDepartamento.getSelectionModel().getSelectedItem());
		reservaEntity.setTurma(txtTurma.getText());
		reservaEntity.setObservacao(txaObservacao.getText());
		reservaEntity.setFixo(ckbFixo.isPressed());

		return reservaEntity;
	}

	@FXML
	void btnBuscar_onAction(ActionEvent event) {
		buscarSoftwares();
	}

	@FXML
	void btnBuscar_onKeyPressed(KeyEvent event) {
		buscarSoftwares();
	}

	@FXML
	void btnBuscar_onMouseClicked(MouseEvent event) {
		buscarSoftwares();
	}

	@FXML
	void btnCancelar_onAction(ActionEvent event) {
		buscarSoftwares();
		tabPaneDados.getSelectionModel().select(tabRequisitos);
	}

	@FXML
	void btnCancelar_onKeyPressed(KeyEvent event) {
		buscarSoftwares();
		tabPaneDados.getSelectionModel().select(tabRequisitos);
	}

	@FXML
	void btnCancelar_onMouseClicked(MouseEvent event) {
		buscarSoftwares();
		tabPaneDados.getSelectionModel().select(tabRequisitos);
	}

	@FXML
	void btnConfirmar_onAction(ActionEvent event) {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType sim = new ButtonType("Sim");
		ButtonType nao = new ButtonType("N�o");
		alerta.setTitle("AlphaLab");
		alerta.setHeaderText("Confirmar Reserva de Hor�rio");
		alerta.setContentText("Deseja confirmar a Reserva de Hor�rio?");
		alerta.getButtonTypes().setAll(sim, nao);

		alerta.showAndWait().ifPresent(option -> {
			if (option == sim) {
				ReservaEntity reserva = this.getDadosTabPreencherDados();
				if (reserva.validar() == null) {
					Reserva salvarReserva = new Reserva(DAOFactory.getDAOFactory().getReservaDAO());
					salvarReserva.save(reserva);
				} else {
					Alert alertaDadosInvalidos = new Alert(Alert.AlertType.INFORMATION);
					alertaDadosInvalidos.setTitle("AlphaLab");
					alertaDadosInvalidos.setHeaderText("Dados inconsistentes!");
					alertaDadosInvalidos.setContentText(reserva.validar().getMessage());

					alertaDadosInvalidos.show();
				}

			} else {
				alerta.close();
			}
		});
	}

	@FXML
	void btnConfirmar_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void btnConfirmar_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void btnProximoRequisitos_onAction(ActionEvent event) {
		getDadosTabRequisitos();
	}

	@FXML
	void btnProximoRequisitos_onKeyPressed(KeyEvent event) {
		getDadosTabRequisitos();
	}

	@FXML
	void btnProximoRequisitos_onMouseClicked(MouseEvent event) {
		getDadosTabRequisitos();
	}

	@FXML
	void ckbFixo_onAction(MouseEvent event) {
	}

	@FXML
	void ckbFixo_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void cmbDepartamento_onAction(ActionEvent event) {
		txtTurma.requestFocus();
	}

	@FXML
	void dtpData_onAction(ActionEvent event) {
		String diaSemana = new String("column.");
		LocalDate date = dtpData.getValue();
		switch (date.getDayOfWeek()) {
		case MONDAY:
			diaSemana += "segunda";
			break;
		case TUESDAY:
			diaSemana += "terca";
			break;
		case WEDNESDAY:
			diaSemana += "quarta";
			break;
		case THURSDAY:
			diaSemana += "quinta";
			break;
		case FRIDAY:
			diaSemana += "sexta";
			break;
		case SATURDAY:
			diaSemana += "sabado";
			break;
		case SUNDAY:
			diaSemana += "domingo";
		}
		tbcDiaSemana.setText(resources.getString(diaSemana));
	}

	@FXML
	void dtpData_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void tblHorarioRequisitos_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void tblHorarioRequisitos_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void tblRequisitos_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void tblRequisitos_onMouseClicked(MouseEvent event) {
		if (event.getClickCount() >= 2) {
			if (tblRequisitos.getSelectionModel().getSelectedItem() == null) {
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("AlphaLab");
				alerta.setHeaderText("Sele��o de softwares");
				alerta.setContentText("Selecione um software na lista de requisitos!");
				alerta.show();
			} else {
				SoftwareCheckTableView softwareSelecionado = this.tblRequisitos.getSelectionModel().getSelectedItem();
				SoftwareEntity softwareEntity = new SoftwareEntity();

				softwareEntity.setId(softwareSelecionado.getSoftwareEntity().getId());
				softwareEntity.setDescricao(softwareSelecionado.getSoftwareEntity().getDescricao());
				softwareEntity.setTipo(softwareSelecionado.getSoftwareEntity().getTipo());
				softwareEntity.setConcluinte(softwareSelecionado.getSoftwareEntity().getConcluinte());
				softwareEntity.setLink(softwareSelecionado.getSoftwareEntity().getLink());
				softwareEntity
						.setObservacao_Instalacao(softwareSelecionado.getSoftwareEntity().getObservacao_Instalacao());
				softwareEntity.setSolicitante(softwareSelecionado.getSoftwareEntity().getSolicitante());
				softwareEntity.setVersao(softwareSelecionado.getSoftwareEntity().getVersao());

				if (listaSoftwaresSelecionados.contains(softwareEntity)) {
					listaSoftwaresSelecionados.remove(listaSoftwaresSelecionados.indexOf(softwareEntity));
				} else {
					listaSoftwaresSelecionados.add(softwareSelecionado.getSoftwareEntity());
				}
			}
		}

		for (SoftwareEntity lista : listaSoftwaresSelecionados) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("AlphaLab");
			alerta.setHeaderText("Softwares selecionados");
			alerta.setContentText(lista.getId() + "\n" + lista.getDescricao() + "\n" + lista.getTipo() + "\n"
					+ lista.getConcluinte() + "\n" + lista.getLink() + "\n" + lista.getObservacao_Instalacao() + "\n"
					+ lista.getSolicitante() + "\n" + lista.getVersao());

			alerta.show();
		}
	}

	@FXML
	void txaObservacao_onKeyPressed(KeyEvent event) {
		// ckbFixo.requestFocus();
	}

	@FXML
	void txtDisciplina_onKeyPressed(KeyEvent event) {
		cmbDepartamento.requestFocus();
	}

	@FXML
	void txtNomeSoftware_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void txtNumMaxAlunos_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void txtTurma_onKeyPressed(KeyEvent event) {
		txaObservacao.requestFocus();

	}

	private void fillColumnHorario() {
		tbcHorario.setCellValueFactory(conteudo -> new SimpleStringProperty(conteudo.getValue().getEstampa()));
		tbcDiaSemana.setCellValueFactory(new PropertyValueFactory<>(""));

		// Adiciona bot�es �s celulas da coluna
		tbcDiaSemana.setCellFactory(col -> new TableCell<Horario, ToggleButton>() {
			@Override
			protected void updateItem(ToggleButton btn, boolean empty) {
				super.updateItem(btn, empty);

				if (empty) {
					setGraphic(null);
				} else {
					btn = new ToggleButton(resources.getString("button.selecionar"));
					listaBotoes.add(btn);

					// Evento que adiciona/retira bot�es selecionados na lista
					// listaSelecionados
					btn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							ToggleButton tgbtn = (ToggleButton) event.getSource();
							if (tgbtn.isSelected()) {
								listaSelecionados.add(tgbtn);
							} else {
								listaSelecionados.remove(tgbtn);
							}
						}
					});
					setGraphic(btn);
				}
			}
		});

		tblHorarioRequisitos.getItems().addAll(Horario.values());
	}
}