package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Automovel;

public class AutomovelDAO {
     public void create(Automovel p) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO automovel (placa,nome,preco,ano,modelo)VALUES(?,?,?,?,?)");
            
            stmt.setString(1, p.getPlaca());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getPreco());
            stmt.setString(4, p.getAno());
            stmt.setString(5, p.getModelo());
                        
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Placa já Existe!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
         public List<Automovel> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Automovel> automoveis = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM automovel");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Automovel automovel = new Automovel();

                automovel.setPlaca(rs.getString("placa"));
                automovel.setNome(rs.getString("nome"));
                automovel.setPreco(rs.getString("preco"));
                automovel.setAno(rs.getString("ano"));
                automovel.setModelo(rs.getString("modelo"));
                automoveis.add(automovel);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Listar Automoveis: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return automoveis;

    }

     public void update(Automovel p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE automovel SET nome = ? ,preco = ?,ano = ?, modelo = ? WHERE placa = ?");
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getPreco());
            stmt.setString(3, p.getAno());
            stmt.setString(4, p.getModelo());
            stmt.setString(5, p.getPlaca());
        
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados:(Nome,Preço,Ano,Modelo) Atualizados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
          public void delete(Automovel p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM automovel WHERE placa = ?");           
            stmt.setString(1, p.getPlaca());
        
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
