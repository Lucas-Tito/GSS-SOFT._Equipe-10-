package dao;

import conexao.conexao;
import java.sql.*;
import java.util.ArrayList;
import modelo.funcio;

public class gerenteDAO {

    Connection con = null;

    public gerenteDAO() {
        con = new conexao().conectar();
    }

    public String inserir(funcio f) {
        String status = "Produto inserido com sucesso!";
        String sql = "INSERT INTO funcionario (nome, endereco, cargo) values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getEndereco());
            ps.setString(3, f.getCargo());

            int n = ps.executeUpdate();
            if (n == 0) {
                status = "Erro ao inserir";
            }
        } catch (Exception e) {
            status = e.getMessage();
        }
        return status;
    }

    public ArrayList<funcio> listar() {
        ArrayList<funcio> funcios = new ArrayList();
        try {
            String sql = "SELECT * FROM funcionario";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                funcio f = new funcio();
                f.setId(rs.getInt(1));
                f.setNome(rs.getString(2));
                f.setEndereco(rs.getString(3));
                f.setCargo(rs.getString(4));
                f.setCriado(rs.getString(5));
                f.setSituacao(rs.getString(6));
                funcios.add(f);
            }
            return funcios;
        } catch (Exception e) {
            return null;
        }
    }

    public funcio consultar(int id) {
        funcio f = new funcio();
        try {
            String sql = "SELECT * FROM funcionario WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f.setId(rs.getInt(1));
                f.setNome(rs.getString(2));
                f.setEndereco(rs.getString(3));
                f.setCargo(rs.getString(4));
                f.setCriado(rs.getString(5));
                f.setSituacao(rs.getString(6));
            }
            return f;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public String atualizar(funcio f) {
        String status = "Produto atualizado com sucesso!";
        String sql = "UPDATE funcionario SET nome = ?, endereco = ?, cargo = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getEndereco());
            ps.setString(3, f.getCargo());
            ps.setInt(4, f.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            status = e.getMessage();
        }
        return status;
    }

    public String excluir(funcio f) {
        String status = "Produto excluido com sucesso!";
        try {
            String sql = "DELETE FROM funcionario WHERE codigo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, f.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            status = e.getMessage();
        }
        return status;
    }
}
