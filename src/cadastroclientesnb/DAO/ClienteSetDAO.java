package DAO;
import Util.ValidacaoUtil;
import Domain.Cliente;
import java.util.*;

public class ClienteSetDAO implements IClienteDAO {
    private ValidacaoUtil validacaoUtil = new ValidacaoUtil();
    private Set<Cliente> clientes = new HashSet<>();


    @Override
    public String cadastrarCliente(String nome, String cpf, String telefone, String email, String endereco) {
        
        if(!validacaoUtil.isValidCpf(cpf) || !validacaoUtil.isValidEmail(email)){
            return "CPF Ou Email Invalido";
        }
        Optional<Cliente> optionalCliente = clientes.stream().filter( p -> p.getCpf().equals(validacaoUtil.converterCpf(cpf))).findFirst();
        
        Cliente construtorCliente = new Cliente(nome,validacaoUtil.converterCpf(cpf),telefone,email,endereco);
        
        if(optionalCliente.isPresent()){
            return "Cliente já está cadastrado";
        }else{
            clientes.add(construtorCliente);
            return "Cliente cadastrado com sucesso!";
        }


    }

    @Override
    public String alterarCliente(String nome, String cpf, String telefone, String email, String endereco) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return "cpfInvalid";
        }else if(!validacaoUtil.isValidEmail(email)){
            return "emailInvalid";
        }else{        

        
            Optional<Cliente> optionalCliente = clientes.stream().filter(p -> p.getCpf().equals(validacaoUtil.converterCpf(cpf))).findFirst();
            
            if(optionalCliente.isPresent()){
                Cliente clienteEdicao = optionalCliente.get();
                clientes.remove(clienteEdicao);
                   
                clienteEdicao.setNome(nome);
                clienteEdicao.setTelefone(telefone);
                clienteEdicao.setEmail(email);
                clienteEdicao.setEndereco(endereco);

                clientes.add(clienteEdicao);
                
            }else{
                return "notFound";
            }

            return "ATT";
        }

    }

    @Override
    public String excluirCliente(String cpf) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return "CPF invalido";
        }
        Optional<Cliente> optionalCliente = clientes.stream().filter(p -> p.getCpf().equals(validacaoUtil.converterCpf(cpf))).findFirst();
        if(optionalCliente.isPresent()){
            clientes.remove(optionalCliente.get());
        }else {
            return "Cliente não encontrado ou já excluido";
        }
        return "Cliente excluido com sucesso!";
    }

    @Override
    public Collection listarCliente() {
        return  clientes;
    }

    @Override
    public Cliente buscarCliente(String cpf) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return null;
        }
        Optional<Cliente> optionalCliente = clientes.stream().filter(p -> p.getCpf().equals(validacaoUtil.converterCpf(cpf))).findFirst();
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            return cliente;
        }else {
            return null;
        }

    }
}