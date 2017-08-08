package br.edu.ifmt.cba.alphalab.entity.laboratorio;

/**
 * 
 * @author St�villis
 *
 */
public enum EnumDisciplina {
	ALGORITMOS_I("Algoritmos 1"),
	CALCULO_I("C�lculo 1"),
	FISICA_I("F�sica 1"),
	VETORES_E_GEOMETRIA_ANALITICA("Vetores e Geometria Anal�tica"),
	COMUNICACAO_E_EXPRESSAO("Comunica��o e Express�o");

	private String descricao;

	private EnumDisciplina(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}