package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entities.Status_Chave;

/**
 *
 * @author raylson
 */
public class Status_ChaveDao implements DAO {
	public void salvar(Object object) {
		
        //Status_Chave status_Chave = (Status_Chave)object;
		Status_Chave status_Chave = new Status_Chave();
		status_Chave.setDescricao("Disponível");
        System.out.println(status_Chave.getId());
        System.out.println(status_Chave.getDescricao());
        Connection conn = new Connection();
        conn.getInstance().persist(status_Chave);
        conn.getInstance().getTransaction().begin();
        conn.getInstance().getTransaction().commit();
        conn.close();
    }
   
	@Override
	public Object buscarPorID(Long id) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Status_Chave x WHERE x.id = :id";
        TypedQuery<Status_Chave> consulta = conn.getInstance().createQuery(jpql, Status_Chave.class);
        consulta.setParameter("id", id);
        return consulta.getSingleResult();
	}

	@Override
	public List<Status_Chave> listar() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Status_Chave x";
        TypedQuery<Status_Chave> consulta = conn.getInstance().createQuery(jpql, Status_Chave.class);
        List<Status_Chave> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}

	@Override
	public void atualizar(Object object) {
		Status_Chave Status_Chave = (Status_Chave) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().merge(Status_Chave);
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

	@Override
	public void deletar(Object object) {
		Status_Chave Status_Chave = (Status_Chave) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().remove(conn.getInstance().getReference(Status_Chave.class, Status_Chave.getId()));
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

}
