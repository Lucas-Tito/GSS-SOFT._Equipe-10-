
package dao;

import conexao.conexao;
import java.sql.*;
import java.util.ArrayList;
import modelo.cliente;

public class clienteDAO {
    Connection con = null;
    public clienteDAO(){
        con = new conexao().conectar();
    }
    
    public String inserir(cliente c){
        String status = "Cliente inserido com sucesso!";
        String sql = "INSERT INTO cliente (cpf, nome, telefone, email) values (?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCpf());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getTelefone());
            ps.setString(4, c.getEmail());
            
            int n = ps.executeUpdate();
            if(n == 0){
                status = "Erro ao inserir";
            }
        }catch(Exception e){
            status = "CPF já cadastrado!";
        }
        return status;
    }

    public ArrayList<cliente> listar(){
        ArrayList<cliente> clientes = new ArrayList();
        try{
            String sql = "SELECT * FROM cliente";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                cliente c = new cliente();
                c.setCpf(rs.getString(1));
                c.setNome(rs.getString(2));
                c.setTelefone(rs.getString(3));
                c.setEmail(rs.getString(4));
                
                clientes.add(c);
            }
            return clientes;
        }catch(Exception e){
            return null;
        }
    }
    public String atualizar(cliente c){
        String status = "Cliente atualizado com sucesso!";
        String sql = "UPDATE cliente SET nome = ?, telefone = ?, email = ? WHERE cpf = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getTelefone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getCpf());
            ps.executeUpdate();
            
        }catch(Exception e){
            status = "Erro ao atualizar o cliente";
        }
        return status;
    }
    
    public String excluir(cliente c){
        String status = "Cliente excluido com sucesso!";
        try{
            String sql = "DELETE FROM cliente WHERE cpf = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCpf());
            ps.executeUpdate();
        }catch(Exception e){
            status = e.getMessage();
        }
        return status;
    }
}
