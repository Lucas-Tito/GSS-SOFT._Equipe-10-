package dao;

import conexao.conexao;
import java.sql.*;
import java.util.ArrayList;
import modelo.produto;

public class produtoDAO {
    Connection con = null;
    public produtoDAO(){
        con = new conexao().conectar();
    }
    
    public String inserir(produto p){
        String status = "Produto inserido com sucesso!";
        String sql = "INSERT INTO produto (nome, valor, categoria, peso) values (?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getValor());
            ps.setInt(3, p.getCategoria());
            ps.setDouble(4, p.getPeso());
            
            int n = ps.executeUpdate();
            if(n == 0){
                status = "Erro ao inserir";
            }
        }catch(Exception e){
            status = e.getMessage();
        }
        return status;
    }

    public ArrayList<produto> listar(){
        ArrayList<produto> produtos = new ArrayList();
        try{
            String sql = "SELECT * FROM produto";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                produto p = new produto();
                p.setCodigo(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setValor(rs.getDouble(3));
                p.setCategoria(rs.getInt(4));
                p.setPeso(rs.getDouble(5));
                produtos.add(p);
            }
            return produtos;
        }catch(Exception e){
            return null;
        }
    }
    public String atualizar(produto p){
        String status = "Produto atualizado com sucesso!";
        String sql = "UPDATE produto SET nome = ?, valor = ?, categoria = ?, peso = ? WHERE codigo = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getValor());
            ps.setInt(3, p.getCategoria());
            ps.setDouble(4, p.getPeso());
            ps.setInt(5, p.getCodigo());
            ps.executeUpdate();
            
        }catch(Exception e){
            status = e.getMessage();
        }
        return status;
    }
    
    public String excluir(produto p){
        String status = "Produto excluido com sucesso!";
        try{
            String sql = "DELETE FROM produto WHERE codigo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getCodigo());
            ps.executeUpdate();
        }catch(Exception e){
            status = e.getMessage();
        }
        return status;
    }
}
