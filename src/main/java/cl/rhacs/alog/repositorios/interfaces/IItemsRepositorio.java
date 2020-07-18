package cl.rhacs.alog.repositorios.interfaces;

import java.util.List;

import cl.rhacs.alog.modelos.Item;

public interface IItemsRepositorio extends IRepositorio<Item> {

    public Item buscarPorDescripcion(String descripcion);

    public List<Item> buscarPorPrecio(double precio);

}
