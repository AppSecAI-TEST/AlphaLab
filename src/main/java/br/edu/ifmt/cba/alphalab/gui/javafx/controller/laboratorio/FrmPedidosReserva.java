package br.edu.ifmt.cba.alphalab.gui.javafx.controller.laboratorio;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.alphalab.business.Reserva;
import br.edu.ifmt.cba.alphalab.business.Servidor;
import br.edu.ifmt.cba.alphalab.dao.DAOFactory;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.EnumTipoReserva;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.Horario;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.LaboratorioEntity;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.RequisitoEntity;
import br.edu.ifmt.cba.alphalab.entity.laboratorio.ReservaEntity;
import br.edu.ifmt.cba.alphalab.entity.pessoa.EnumTipoServidor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * 
 * @author St�villis Sousa
 *
 */

public class FrmPedidosReserva implements Initializable {
	private List<RequisitoEntity> listaRequisitos = new ArrayList<>();
	private Reserva reserva = new Reserva(DAOFactory.getDAOFactory().getReservaDAO());
	private Servidor servidor = new Servidor(DAOFactory.getDAOFactory().getServidorDAO());

	@FXML
	private TabPane tbpDados;

	@FXML
	private Tab tabPedidos;

	@FXML
	private DatePicker dtpData;

	@FXML
	private ComboBox<EnumTipoReserva> cmbTipo;

	@FXML
	private ComboBox<EnumTipoServidor> cmbProfessor;

	@FXML
	private TableView<ReservaEntity> tblPedidos;

	@FXML
	private TableColumn<ReservaEntity, Long> tbcID;

	@FXML
	private TableColumn<ReservaEntity, Date> tbcHorario;

	@FXML
	private TableColumn<ReservaEntity, Date> tbcData;

	@FXML
	private TableColumn<ReservaEntity, Boolean> tbcTipo;

	@FXML
	private TableColumn<ReservaEntity, Boolean> tbcFixo;

	@FXML
	private TableColumn<ReservaEntity, String> tbcDados;

	@FXML
	private TableColumn<ReservaEntity, String> tbcDescricao;

	@FXML
	private Tab tabDados;

	@FXML
	private Text texID;

	@FXML
	private Text txtDataPedido;

	@FXML
	private ComboBox<LaboratorioEntity> cmbLaboratorio;

	@FXML
	private HBox hbxRequisitos;

	@FXML
	private Text texProfessor;

	@FXML
	private Text texDepartamento;

	@FXML
	private Text texTurma;

	@FXML
	private Text texDescricao;

	@FXML
	private Text texDisciplina;

	@FXML
	private HBox hbxHorarios;

	@FXML
	private CheckBox ckbFixo;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnNegar;

	@FXML
	private Button btnPermitir;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblPedidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		limparDados();
		dtpData.requestFocus();

		tabDados.setDisable(true);

		/*
		 * TODO Sugest�o: Como esse campo n�o existe mais, voc� pode transformar
		 * ele em SEMESTRAL e UNICA (al�m de tirar esse atributo da entidade) e
		 * a� filtra pelas reservas que tem mesmo dia de come�o e fim (isso d�
		 * pra fazer em uma linha usando LocalDate).
		 */
		ObservableList<EnumTipoReserva> comboTipo = FXCollections.observableArrayList(EnumTipoReserva.UNICA,
				EnumTipoReserva.SEMESTRAL);
		cmbTipo.setItems(comboTipo);

		ObservableList<EnumTipoServidor> servidores = FXCollections.observableArrayList(EnumTipoServidor.ESTAGIARIO,
				EnumTipoServidor.PROFESSOR, EnumTipoServidor.TEC_ADM, EnumTipoServidor.TEC_LABORATORIO);
		cmbProfessor.setItems(servidores);

		preencherDadosTblPedidos(reserva.buscarTodasReservas());
	}

	/**
	 * Limpa o formul�rio da tela.
	 */
	private void limparDados() {
		dtpData.setValue(null);
		dtpData.setPromptText("Data");
		cmbTipo.getSelectionModel().select(null);
		cmbTipo.setPromptText("Tipo");
		cmbProfessor.getSelectionModel().select(null);
		cmbProfessor.setPromptText("Professor");
		tblPedidos.setItems(null);
		texID.setText("");
		txtDataPedido.setText("");
		cmbLaboratorio.getSelectionModel().select(null);
		cmbLaboratorio.setPromptText("Laborat�rio");
		hbxRequisitos.getChildren().remove(1, hbxRequisitos.getChildren().size());
		texProfessor.setText("");
		texDisciplina.setText("");
		texDepartamento.setText("");
		texTurma.setText("");
		texDescricao.setText("");
		hbxHorarios.getChildren().remove(1, hbxHorarios.getChildren().size());
		ckbFixo.setSelected(false);
	}

	/**
	 * Cancela a opera��o de Gerenciar Pedidos de Reserva e\n retorna � aba
	 * inicial.
	 */
	private void cancelarGerenciarPedido() {
		limparDados();
		tabDados.setDisable(true);
		tabPedidos.setDisable(false);
		tbpDados.getSelectionModel().select(tabPedidos);
		preencherDadosTblPedidos(reserva.buscarTodasReservas());
	}

	/**
	 * Preenche o formul�rio da aba Visualizar Dados com os dados\n do pedido
	 * selecionado na aba Pedidos.
	 */
	private void preencherVisualizarDados(ReservaEntity reservaEntity) {
		limparDados();

		tabPedidos.setDisable(true);
		tabDados.setDisable(false);

		cmbLaboratorio.getItems().setAll(DAOFactory.getDAOFactory().getLaboratorioDAO().buscarTodos());

		texID.setText(reservaEntity.getId().toString());
		txtDataPedido.setText(reservaEntity.getDataSolicitacao().toString());
		if (hbxRequisitos.getChildren().size() > 1)
			hbxRequisitos.getChildren().remove(1, hbxRequisitos.getChildren().size());
		hbxRequisitos.getChildren().add(buildBoxRequisitos(reservaEntity.getRequisitos()));
		texProfessor.setText(reservaEntity.getSolicitante().getNome());
		texDisciplina.setText(reservaEntity.getDisciplina().toString());
		texDepartamento.setText(reservaEntity.getDepartamentoAula().getNome());
		texTurma.setText(reservaEntity.getTurma());
		texDescricao.setText(reservaEntity.getObservacao());
		if (hbxHorarios.getChildren().size() > 1)
			hbxHorarios.getChildren().remove(1, hbxHorarios.getChildren().size());
		hbxHorarios.getChildren().add(buildBoxHorarios(reservaEntity.getHorarios()));
		ckbFixo.setSelected(false);
	}

	private void preencherDadosTblPedidos(List<ReservaEntity> listaReserva) {

		tbcID.setCellValueFactory(new PropertyValueFactory<>("id"));
		// Em andamento: lista de hor�rios solicitados para a reserva
		tbcHorario.setCellValueFactory(new PropertyValueFactory<>("dataSolicitacao"));
		tbcData.setCellValueFactory(new PropertyValueFactory<>("dataSolicitacao"));
		// Em andamento: dedicir o que colocar aqui
		tbcTipo.setCellValueFactory(new PropertyValueFactory<>("fixo"));
		tbcFixo.setCellValueFactory(new PropertyValueFactory<>("fixo"));
		tbcDados.setCellValueFactory(new PropertyValueFactory<>("solicitante"));
		tbcDescricao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

		tblPedidos.setItems(FXCollections.observableArrayList(listaReserva));
		tblPedidos.refresh();
	}

	private void atualizaTableViewPorProfessor(EnumTipoServidor servidor) {

	}

	private StackPane buildBoxRequisitos(RequisitoEntity requisito) {
		VBox vbox = new VBox(7);
		vbox.setAlignment(Pos.CENTER);

		vbox.getChildren().add(new Text(requisito.toString()));

		StackPane caixa = new StackPane(vbox);
		return caixa;
	}

	private StackPane buildBoxHorarios(List<Horario> horarios) {
		VBox vbox = new VBox(7);
		vbox.setAlignment(Pos.CENTER);

		Rectangle rect = new Rectangle(150, 100);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(2);
		rect.setFill(Color.TRANSPARENT);

		for (Horario horario : horarios) {
			vbox.getChildren().add(new Text(horario.getEstampa()));
		}

		StackPane caixa = new StackPane(rect, vbox);
		return caixa;
	}

	@FXML
	void btnCancelar_onAction(ActionEvent event) {
	}

	@FXML
	void btnCancelar_onKeyPressed(KeyEvent event) {
		cancelarGerenciarPedido();

	}

	@FXML
	void btnCancelar_onMouseClicked(MouseEvent event) {
		cancelarGerenciarPedido();

	}

	@FXML
	void btnNegar_onAction(ActionEvent event) {

	}

	@FXML
	void btnNegar_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void btnNegar_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void btnPermitir_onAction(ActionEvent event) {

	}

	@FXML
	void btnPermitir_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void btnPermitir_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void cbkFixo_onAction(ActionEvent event) {

	}

	@FXML
	void ckbFixo_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void cmbLaboratorio_onAction(ActionEvent event) {

	}

	@FXML
	void cmbLaboratorio_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void cmbProfessor_onAction(ActionEvent event) {
		if (cmbProfessor.getSelectionModel().getSelectedItem() != null)
			atualizaTableViewPorProfessor(cmbProfessor.getSelectionModel().getSelectedItem());
	}

	@FXML
	void cmbTipo_onAction(ActionEvent event) {

	}

	@FXML
	void cmdLaboratorio_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void dtpData_onAction(ActionEvent event) {
		if (dtpData.getValue() != null) {
			preencherDadosTblPedidos(reserva.getByData(java.sql.Date.valueOf(dtpData.getValue())));
		}
	}

	@FXML
	void dtpData_onKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER && dtpData.getValue() != null) {
			preencherDadosTblPedidos(reserva.getByData(java.sql.Date.valueOf(dtpData.getValue())));
		}
	}

	@FXML
	void tblPedidos_onKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (tblPedidos.getSelectionModel().getSelectedItem() != null) {
				preencherVisualizarDados(tblPedidos.getSelectionModel().getSelectedItem());
				tbpDados.getSelectionModel().select(tabDados);
			}
		}
	}

	@FXML
	void tblPedidos_onMouseClicked(MouseEvent event) {
		if (event.getClickCount() >= 2) {
			if (tblPedidos.getSelectionModel().getSelectedItem() != null) {
				preencherVisualizarDados(tblPedidos.getSelectionModel().getSelectedItem());

				tbpDados.getSelectionModel().select(tabDados);
			}
		}
	}
}