package DAO;


import Domain.Cliente;
import java.util.Collection;


public interface IClienteDAO {

    public String cadastrarCliente(String nome, String cpf, String telefone, String email, String endereco);

    public String alterarCliente(String nome, String cpf, String telefone, String email, String endereco);

    public String excluirCliente(String cpf);

    public Collection listarCliente();

    public Cliente buscarCliente(String cpf);


}
