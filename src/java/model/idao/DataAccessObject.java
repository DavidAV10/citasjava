package model.idao;

import java.util.List;

public interface DataAccessObject<T> {
    
    public void insertar(T obj);

    public void eliminar(T obj);

    public void modificar(T obj);
    
    public List<T> getLista();
    
    public List<T> filtrar(String campo, String criterio);

    public int getNumeroRegistros();

}
