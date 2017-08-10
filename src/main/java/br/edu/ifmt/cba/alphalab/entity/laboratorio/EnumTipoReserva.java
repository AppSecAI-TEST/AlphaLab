package br.edu.ifmt.cba.alphalab.entity.laboratorio;

/**
 * 
 * @author St�villis Sousa
 *
 */

public enum EnumTipoReserva {
	SEMESTRAL("Semestral"), UNICA("�nica");

	private final String descricao;

	private EnumTipoReserva(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}