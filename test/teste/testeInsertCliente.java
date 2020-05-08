package teste;

import dao.clienteDAO;
import modelo.cliente;

public class testeInsertCliente {
    public static void main(String[] args) {
        cliente c = new cliente();
        c.setCpf("8953984");
        c.setNome("Boris");
        c.setTelefone("(85) 789654123");
        c.setEmail("motherrussia@gmail.com");
        
        String msg = new clienteDAO().inserir(c);
        System.out.println(msg);
        
    }
}
