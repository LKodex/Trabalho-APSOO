package apsoo.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import apsoo.model.Artigo;
import apsoo.model.ArtigoLocado;
import apsoo.model.Cliente;
import apsoo.model.Funcionario;
import apsoo.model.Locacao;
import apsoo.model.Pagamento;

public class GerBD {
    private static GerBD instance;
    private Conexao conexao;

    private GerBD(){
        conexao = Conexao.getInstance();
    }

    public static GerBD getInstance(){
        if(instance == null) instance = new GerBD();
        return instance;
    }

    public Cliente buscarCliente(String cpf){
        ResultSet clienteResultSet = null;
        ResultSet pessoaResultSet = null;
        Cliente cliente = null;

        try {
            clienteResultSet = conexao.select(String.format("SELECT * FROM Cliente WHERE cpf = '%s'", cpf));
            pessoaResultSet = conexao.select(String.format("SELECT * FROM Pessoa WHERE cpf = '%s'", cpf));

            if(clienteResultSet.next() && pessoaResultSet.next()){
                cliente = new Cliente(pessoaResultSet.getString("nome"), cpf, clienteResultSet.getString("celular"));
            }
        } catch (Exception e) {
            System.out.println("Não foi possível recuperar um cliente ou pessoa do banco de dados! Retornando null");
        }
        return cliente;
    }

    public Funcionario buscarFuncionario(String cpf) {
        ResultSet funcionarioResultSet = null;
        ResultSet pessoaResultSet = null;
        Funcionario funcionario = null;

        try {
            funcionarioResultSet = conexao.select(String.format("SELECT * FROM Funcionario WHERE cpf = '%s'", cpf));
            pessoaResultSet = conexao.select(String.format("SELECT * FROM Pessoa WHERE cpf = '%s'", cpf));

            if(funcionarioResultSet.next() && pessoaResultSet.next()){
                funcionario = new Funcionario(pessoaResultSet.getString("nome"), cpf, funcionarioResultSet.getString("senha"), 0.0);
            }
        } catch (Exception e) {
            System.out.println("Não foi possível recuperar um funcionario ou pessoa do banco de dados! Retornando null");
        }
        return funcionario;
    }

    public List<Artigo> buscarArtigos(){
        List<Artigo> listaArtigos = new ArrayList<Artigo>();
        try {
            ResultSet resultSet = conexao.select("SELECT * FROM Artigo");
            while(resultSet.next()){
                Artigo artigo = new Artigo(
                    Integer.parseInt(resultSet.getString("codigo")),
                    resultSet.getString("nomeArtigo"),
                    Double.parseDouble(resultSet.getString("valorDiario")),
                    Integer.parseInt(resultSet.getString("estoqueTotal"))
                );

                listaArtigos.add(artigo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao recuperar artigos!");
        }
        return listaArtigos;
    }
    
    // Persiste uma instância de locação no banco de dados e retorna o id da locacao inserida
    public int realizarLocacao(Locacao locacao){
        int locacaoId = -1;
        if(locacao.getInicio().after(locacao.getFim())){ return locacaoId; }

        List<ArtigoLocado> artigoLocados = locacao.getArtigoLocados();
        Pagamento pagamento = locacao.getPagamento();

        conexao.insert(String.format("INSERT INTO Locacao (cpfCliente, cpfFuncionario, inicioPrevisto, fimPrevisto, dataReservada, endereco) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
            locacao.getCliente().getCpf(),
            locacao.getFuncionario().getCpf(),
            locacao.getInicio(),
            locacao.getFim(),
            locacao.getDataReservada(),
            locacao.getEndereco()
        ));

        ResultSet loc = conexao.select(String.format("SELECT (id) FROM Locacao WHERE cpfCliente='%s' AND cpfFuncionario='%s' AND dataReservada = '%s'",
            locacao.getCliente().getCpf(),
            locacao.getFuncionario().getCpf(),
            locacao.getDataReservada()
        ));

        try {
            if(loc.next()){
                locacaoId = Integer.parseInt(loc.getString("id"));
            }
        } catch (Exception e) {
            System.out.println("Deu erro ao recuperar id da locação cadastrada");
        }
        
        inserirPagamento(locacaoId, pagamento);

        for (ArtigoLocado artigoLocado : artigoLocados) {
            inserirArtigoLocado(locacaoId, artigoLocado);
        }
        
        return locacaoId;
    }

    // Persiste uma instância de artigoLocado no banco de dados
    public boolean inserirArtigoLocado(int locacaoId, ArtigoLocado artigoLocado){
        int resultado = conexao.insert(String.format("INSERT INTO ArtigoLocado (id, codigo, quantidade, valorCotado, valorTotal) VALUES ('%d', '%d', '%d', '%.2f', '%.2f')",
            locacaoId,
            artigoLocado.getCodigo(),
            artigoLocado.getQuantidade(),
            artigoLocado.getValorDiaria(),
            artigoLocado.getValorTotal()
        ));
        atualizarQuantidade(artigoLocado);
        return resultado > 0;
    }

    // Persiste uma instância de pagamento no banco de dados
    public boolean inserirPagamento(int locacaoId, Pagamento pagamento){
        int resultado = conexao.insert(String.format("INSERT INTO Pagamento (id, locId, formaPagamento, info) VALUES ('%s', '%d', '%s', '%s')",
            pagamento.getId(),
            locacaoId,
            pagamento.getMetodo(),
            pagamento.getObservacao()
        ));
        return resultado > 0;
    }

    public int atualizarQuantidade(ArtigoLocado artigoLocado){
        int quantidadeAtual;
        try {
            quantidadeAtual = Integer.parseInt(conexao.select(String.format("SELECT estoqueTotal FROM Artigo WHERE codigo = '%d'",
                artigoLocado.getCodigo()
            )).getString("estoqueTotal"));

            return conexao.update(String.format("UPDATE Artigo SET estoqueTotal = '%d' WHERE codigo ='%d'",
                quantidadeAtual - artigoLocado.getQuantidade(),
                artigoLocado.getCodigo()
            ));
        } catch (Exception e) {
            System.out.println("Erro ao recuperar a quantidade de estoque de artigo!");
            return -1;
        }
    }
}
