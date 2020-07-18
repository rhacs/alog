package cl.rhacs.alog.repositorios.interfaces;

import java.util.List;

public interface IRepositorio<T> {

    public boolean agregarRegistro(T registro);

    public T buscarPorId(int id);

    public List<T> buscarTodos();

    public boolean actualizarRegistro(T registro);

    public boolean eliminarRegistro(T registro);

}
