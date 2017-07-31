package br.edu.ifmt.cba.alphalab.gui.javafx.controller.laboratorio;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.alphalab.gui.javafx.Horario;
import br.edu.ifmt.cba.alphalab.gui.javafx.controller.exemplo.FrmPrincipal;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * 
 * @author St�villis Sousa
 *
 */

public class FrmSolicitarReservaHorarioPorRequisito implements Initializable{
	ResourceBundle resources = ResourceBundle.getBundle(FrmPrincipal.LINGUA_PORTUGUES);
	
	//Lista de bot�es contidos na TableColumn tbcDiaSemana
	private List<ToggleButton> listaBotoes = new ArrayList<>();
	
	//Lista dos bot�es pressionados na TableColumn tbcDiaSemana
	private List<ToggleButton> listaSelecionados = new ArrayList<>();
	
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
	private TableView<?> tblRequisitos;

	@FXML
	private TableColumn<?, ?> tbcSelecionado;

	@FXML
	private TableColumn<?, ?> tbcNome;

	@FXML
	private TableColumn<?, ?> tbcTipo;

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
	private ComboBox<?> cmbDepartamento;

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

	@FXML
	void btnBuscar_onAction(ActionEvent event) {
		
	}

	@FXML
	void btnBuscar_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void btnBuscar_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void btnCancelar_onAction(ActionEvent event) {

	}

	@FXML
	void btnCancelar_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void btnCancelar_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void btnConfirmar_onAction(ActionEvent event) {

	}

	@FXML
	void btnConfirmar_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void btnConfirmar_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void btnProximoRequisitos_onAction(ActionEvent event) {

	}

	@FXML
	void btnProximoRequisitos_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void btnProximoRequisitos_onMouseClicked(MouseEvent event) {

	}

	@FXML
	void ckbFixo_onAction(MouseEvent event) {

	}

	@FXML
	void cmbDepartamento_onAction(ActionEvent event) {

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

	}

	@FXML
	void txaObservacao_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void txtDisciplina_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void txtNomeSoftware_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void txtNumMaxAlunos_onKeyPressed(KeyEvent event) {

	}

	@FXML
	void txtTurma_onKeyPressed(KeyEvent event) {

	}
	
	private void fillColumnHorario() {
		tbcHorario.setCellValueFactory(conteudo -> new SimpleStringProperty(conteudo.getValue().getEstampa()));
		tbcDiaSemana.setCellValueFactory(new PropertyValueFactory<>(""));
		
		//Adiciona bot�es �s celulas da coluna
		tbcDiaSemana.setCellFactory(col -> new TableCell<Horario, ToggleButton>() {
		    @Override 
		    protected void updateItem(ToggleButton btn, boolean empty) {
		        super.updateItem(btn, empty);
		        
		        if (empty) {
		            setGraphic(null);
		        } else {
		        	btn = new ToggleButton(resources.getString("button.selecionar"));
		        	listaBotoes.add(btn);
		        	
		        	//Evento que adiciona/retira bot�es selecionados na lista listaSelecionados
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillColumnHorario();
	}
}