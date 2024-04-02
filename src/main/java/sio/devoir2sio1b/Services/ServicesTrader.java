package sio.devoir2sio1b.Services;

import sio.devoir2sio1b.Model.Action;
import sio.devoir2sio1b.Model.Trader;
import sio.devoir2sio1b.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesTrader
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesTrader()
    {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Trader> getAllTraders() throws SQLException {
        ArrayList<Trader> lesTraders = new ArrayList<>();
        ps = cnx.prepareStatement("select trader.idTrader, trader.nomTrader from trader");
        rs = ps.executeQuery();
        while (rs.next())
        {
            Trader trader = new Trader(rs.getInt(1), rs.getString(2));
            lesTraders.add(trader);
        }
        ps.close();
        rs.close();
        return lesTraders;
    }

    public double getMontantTotalPortefeuille(int idTrader) throws SQLException {
        double montant = 0;

        ps = cnx.prepareStatement("SELECT acheter.numTrader, SUM(acheter.montantAchat * acheter.quantite)\n" +
                "FROM acheter\n" +
                "WHERE acheter.numTrader=?\n" +
                "GROUP BY acheter.numTrader");
        ps.setInt(1, idTrader);
        rs = ps.executeQuery();
        rs.next();
        montant=rs.getDouble(2);
        return montant;
    }
}
