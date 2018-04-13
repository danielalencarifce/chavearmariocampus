package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import beans.CadastrarArmarioBean;
import entities.Armario;
import entities.Chave;;

/**
 *
 * @author raylson
 */
public class ArmarioDao implements DAO {
	public void salvar(Object object) {
        CadastrarArmarioBean armarioBean = (CadastrarArmarioBean) object;
        Armario armario = new Armario();
        armario.setNumero_armario(armarioBean.getNumero_armario());
        armario.setBloco(armarioBean.getBloco());
        
        ChaveDao chaveDao = new ChaveDao();
        List<Chave> chaves = new ArrayList<>();
        Chave chavePrincipal = new Chave();
        chavePrincipal.setChave("Chave Principal");
        chavePrincipal.setStatus("Disponível");
        chaveDao.salvar(chavePrincipal);
        chaves.add(chavePrincipal);
        Chave chaveReserva = new Chave();
        chaveReserva.setChave("Chave Reserva");
        chaveReserva.setStatus("Disponível");
    	chaveDao.salvar(chaveReserva);
        chaves.add(chaveReserva);
        
        armario.setChaves(chaves);
        armario.setCoordenacao(armarioBean.getCoordenacao());
        armario.setEstante(armarioBean.getEstante());
        armario.setDisponivel(true);
               
        Connection conn = new Connection();
        conn.getInstance().persist(armario);
        conn.getInstance().getTransaction().begin();
        conn.getInstance().getTransaction().commit();
        conn.close();
    }
   
	@Override
	public Object buscarPorID(Long id) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Armario x WHERE x.id = :id";
        TypedQuery<Armario> consulta = conn.getInstance().createQuery(jpql, Armario.class);
        consulta.setParameter("id", id);
        return consulta.getSingleResult();
	}

	@Override
	public List<Armario> listar() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Armario x";
        TypedQuery<Armario> consulta = conn.getInstance().createQuery(jpql, Armario.class);
        List<Armario> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}

	@Override
	public void atualizar(Object object) {
		Armario Armario = (Armario) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().merge(Armario);
        conn.getInstance().getTransaction().commit();
        conn.close();
	}

	@Override
	public void deletar(Object object) {
		Armario Armario = (Armario) object;
        Connection conn = new Connection();
        conn.getInstance().getTransaction().begin();
        conn.getInstance().remove(conn.getInstance().getReference(Armario.class, Armario.getId()));
        conn.getInstance().getTransaction().commit();
        conn.close();
	}
	
	public Object buscarPorNumero(String NumeroArmario) {
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Armario x WHERE x.numero_armario = :NumeroArmario";
        try {
        	TypedQuery<Armario> consulta = conn.getInstance().createQuery(jpql, Armario.class);
            consulta.setParameter("NumeroArmario", NumeroArmario);
            Armario armario = consulta.getSingleResult();
            conn.close();
            return armario;
        }catch (NoResultException nre) {
        	return null;
		}
	}
	
	public List<Armario> listarArmariosDisponiveis() { 
		Connection conn = new Connection();
        String jpql = "SELECT x FROM Armario x WHERE x.disponivel = true";
        TypedQuery<Armario> consulta = conn.getInstance().createQuery(jpql, Armario.class);
        List<Armario> resultado = consulta.getResultList();
        conn.close();
        return resultado;
	}
}
