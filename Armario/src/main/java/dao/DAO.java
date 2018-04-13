package dao;

import java.util.List;

public interface DAO {
    public Object buscarPorID(Long id);
    public List<?> listar();
    public void salvar(Object object);
    public void atualizar(Object object);
    public void deletar(Object object);
}
