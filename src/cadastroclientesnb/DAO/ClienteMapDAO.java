package DAO;
import Util.ValidacaoUtil;
import Domain.Cliente;
import java.util.*;

public class ClienteMapDAO implements IClienteDAO {

    private ValidacaoUtil validacaoUtil = new ValidacaoUtil();
    private final Map<Long, Cliente> clientes = new HashMap<>();

    @Override
    public String cadastrarCliente(String nome, String cpf, String telefone, String email, String endereco) {
        
        if(!validacaoUtil.isValidCpf(cpf) || !validacaoUtil.isValidEmail(email)){
            return "Invalido";
        }
            Cliente construtorCliente = new Cliente(nome,validacaoUtil.converterCpf(cpf),telefone,email,endereco);

            if(clientes.containsKey(validacaoUtil.converterCpf(cpf))){

                return "existente";
            }else{
                clientes.put(validacaoUtil.converterCpf(cpf),construtorCliente);
                return "cadastrado";
            }



    }



    @Override
    public String alterarCliente(String nome, String cpf, String telefone, String email, String endereco) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return "cpfInvalid";
        }else if(!validacaoUtil.isValidEmail(email)){
            return "emailInvalid";
        }
        
        Long longCpf = validacaoUtil.converterCpf(cpf);
        
        if(clientes.get(longCpf) == null){
            return "notFound";
        }
                
        clientes.get(longCpf).setNome(nome);
        clientes.get(longCpf).setTelefone(telefone);
        clientes.get(longCpf).setEmail(email);
        clientes.get(longCpf).setEndereco(endereco);
                
               


        return "ATT";
    }

    @Override
    public String excluirCliente(String cpf) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return "CPF invalido.";
        }
        Long longCPF = validacaoUtil.converterCpf(cpf);
        if(clientes.containsKey(longCPF)){
            clientes.remove(longCPF);
            return "Cadastro excluido com sucesso!";
        }else{
            return "Cliente já excluido ou não está cadastrado";
        }






    }

    @Override
    public Collection listarCliente() {        
        
        return  clientes.values();
    }
    

    @Override
    public Cliente buscarCliente(String cpf) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return null;
        }
        Long longCPF = validacaoUtil.converterCpf(cpf);
        if(clientes.containsKey(longCPF)){
            return clientes.get(longCPF) ;

        }else {
            return null ;
        }
    }


}
