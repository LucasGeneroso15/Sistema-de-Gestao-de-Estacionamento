package model.dao;

import db.DB;
import model.dao.impl.TicketDaoJDBC;
import model.dao.impl.VagaDaoJDBC;
import model.dao.impl.VeiculoDaoJDBC;


public class DaoFactory {
    public static VagaDao createVagaDao(){
        return new VagaDaoJDBC(DB.getConnection());
    }

    public static TicketDao createTicketDao(){
        return new TicketDaoJDBC(DB.getConnection());
    }

    public static VeiculoDao createVeiculoDao(){
        return new VeiculoDaoJDBC(DB.getConnection());
    }

}
