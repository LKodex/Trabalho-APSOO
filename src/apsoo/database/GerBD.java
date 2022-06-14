package apsoo.database;

import java.sql.ResultSet;
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

    /**
     * Busca um cliente no banco de dados a partir de um CPF
     * @param cpf - CPF do cliente já cadastrado
     * @return Cliente - Caso seja encontrado retorna um cliente, caso contrário retorna null
     */
    public Cliente buscarCliente(String cpf){
        ResultSet clienteResultSet = null;
        ResultSet pessoaResultSet = null;
        Cliente cliente = null;
        cpf = cpf.replaceAll("[^0-9]", "");

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

    /**
     * Busca um funcionário no banco de dados a partir de um CPF
     * @param cpf - CPF do funcionário já cadastrado
     * @return funcionário - Caso seja encontrado retorna um funcionário, caso contrário retorna null
     */
    public Funcionario buscarFuncionario(String cpf) {
        ResultSet funcionarioResultSet = null;
        ResultSet pessoaResultSet = null;
        Funcionario funcionario = null;
        cpf = cpf.replaceAll("[^0-9]", "");

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

    /**
     * Busca e retorna a lista de todos os artigos cadastrados no banco de dados
     * @return List<Artigo> - Lista de artigos cadastrados
     */
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
    
    /**
     * Insere uma nova locação no banco de dados incluindo o pagamento associado
     * @param locacao - Instância de locação com todos atributos preenchidos
     */
    public void realizarLocacao(Locacao locacao){
        if(locacao.getInicio().after(locacao.getFim())){ return; }

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

        try {
            ResultSet locIdRS = conexao.select(String.format("SELECT id FROM Locacao WHERE cpfCliente LIKE '%s' AND cpfFuncionario LIKE '%s' AND dataReservada = '%s' AND endereco LIKE '%s'",
                locacao.getCliente().getCpf(),
                locacao.getFuncionario().getCpf(),
                locacao.getDataReservada(),
                locacao.getEndereco()
            ));

            if(locIdRS.next()){
                locacao.setId(locIdRS.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        inserirPagamento(locacao.getId(), pagamento);

        for (ArtigoLocado artigoLocado : artigoLocados) {
            inserirArtigoLocado(locacao.getId(), artigoLocado);
        }
    }

    /**
     * Insere um novo artigo locado no banco de dados associado a uma locação já cadastrada
     * @param locacaoId - Id da locação associada
     * @param artigoLocado - Artigo locado a ser registrado
     */
    public void inserirArtigoLocado(int locacaoId, ArtigoLocado artigoLocado){
        conexao.insert(String.format("INSERT INTO ArtigoLocado (id, codigo, quantidade, valorCotado, valorTotal) VALUES ('%d', '%d', '%d', '%.2f', '%.2f')",
            locacaoId,
            artigoLocado.getCodigo(),
            artigoLocado.getQuantidade(),
            artigoLocado.getValorDiaria(),
            artigoLocado.getValorTotal()
        ));
        atualizarQuantidade(artigoLocado);
    }

    /**
     * Insere um novo pagamento no banco de dados associado a uma locação já cadastrada
     * @param locacaoId - Id da locação associada
     * @param pagamento - Pagamento a ser registrado
     */
    public void inserirPagamento(int locacaoId, Pagamento pagamento){
        conexao.insert(String.format("INSERT INTO Pagamento (id, locId, formaPagamento, info) VALUES ('%s', '%d', '%s', '%s')",
            pagamento.getId(),
            locacaoId,
            pagamento.getMetodo(),
            pagamento.getObservacao()
        ));
    }

    /**
     * Atualiza a quantidade do artigo relacionado ao artigo locado, diminuindo sua quantidade no banco de dados pela quantidade locada em artigo locado
     * @param artigoLocado - Artigo locado relacionado ao artigo a ter a quantidade atualizada
     */
    public void atualizarQuantidade(ArtigoLocado artigoLocado){
        int quantidadeAtual;
        try {
            ResultSet artigoResultSet = conexao.select(String.format("SELECT estoqueTotal FROM Artigo WHERE codigo = '%d'", artigoLocado.getCodigo()));
            quantidadeAtual = artigoResultSet.getInt("estoqueTotal");

            // Atualiza o artigo relacionado ao artigo locado subtraindo a quantidade locada pela quantidade atual no banco de dados
            conexao.update(String.format("UPDATE Artigo SET estoqueTotal = '%d' WHERE codigo ='%d'",
                quantidadeAtual - artigoLocado.getQuantidade(),
                artigoLocado.getCodigo()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    public String inserirDevolução(Devolucao devolucao){
        try {
            ResultSet result;
            result=con.select("SELECT * FROM Devolucao WHERE id = "+devolucao.getID());
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public String atualizarQuantidade(Artigo artigo){
        String resultado;
        resultado=con.update("Update artigo SET estoqueTotal = '"+artigo.getEstoqueTotal()+"' WHERE codigo ='"+artigo.getCodigo()+"'");
        return resultado;
    }
}
