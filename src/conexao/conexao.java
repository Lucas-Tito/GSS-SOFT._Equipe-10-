package conexao;
import java.sql.*;

public class conexao {
    static String status = "Conexão realizada com sucesso!";
    public static Connection conectar(){
        Connection con = null;
        try{
           Class.forName("com.mysql.jdbc.Driver");
           String url = "jdbc:mysql://localhost/gss";
           con = DriverManager.getConnection(url,"root","");
           System.out.println(status);
          
        }catch(Exception e){
            System.out.println(e.getMessage());
            
        }
        return con;
    }
}
