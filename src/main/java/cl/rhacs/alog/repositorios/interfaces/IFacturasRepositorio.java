package cl.rhacs.alog.repositorios.interfaces;

import java.sql.Date;
import java.util.List;

import cl.rhacs.alog.modelos.Factura;

public interface IFacturasRepositorio extends IRepositorio<Factura> {

    public List<Factura> buscarPorFecha(Date fecha);
    
    public Factura buscarUltima();

}
