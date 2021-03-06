
package br.edu.ifmt.cba.alphalab.business;

import br.edu.ifmt.cba.alphalab.dao.IDAO;
import br.edu.ifmt.cba.alphalab.dao.IEmprestimoDAO;
import br.edu.ifmt.cba.alphalab.entity.equipamentos.EmprestimoEntity;
import br.edu.ifmt.cba.alphalab.entity.exception.EmprestimoException;

public class Emprestimo extends ABusiness<EmprestimoEntity, EmprestimoException, IEmprestimoDAO>  {
    
    public Emprestimo(IDAO<EmprestimoEntity> dao) {
        super(dao);
    }
    
}
