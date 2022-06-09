package apsoo.model;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;

    public Pessoa(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf.replace("[^0-9]", "");
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf.replace("[^0-9]", "");
    }

    /**
     * Retorna o cpf com a máscara xxx.xxx.xxx-xx caso seja válido
     * @return String - cpf formatado com uma máscara
     */
    public String getCpfFormatado(){
        if(validar()){ return null; }

        char[] cpfOriginal = cpf.toCharArray();
        char[] cpfFormatado = new char[15];

        cpfFormatado[0]     = cpfOriginal[0];
        cpfFormatado[1]     = cpfOriginal[1];
        cpfFormatado[2]     = cpfOriginal[2];
        cpfFormatado[3]     = '.';
        cpfFormatado[4]     = cpfOriginal[3];
        cpfFormatado[5]     = cpfOriginal[4];
        cpfFormatado[6]     = cpfOriginal[5];
        cpfFormatado[7]     = '.';
        cpfFormatado[8]     = cpfOriginal[6];
        cpfFormatado[9]     = cpfOriginal[7];
        cpfFormatado[10]    = cpfOriginal[8];
        cpfFormatado[11]    = '-';
        cpfFormatado[12]    = cpfOriginal[9];
        cpfFormatado[13]    = cpfOriginal[10];
        
        return cpfFormatado.toString();
    }

    public void setCpf(String cpf){
        this.cpf = cpf.replace("[^0-9]", "");
    }

    /**
     * Verifica se o cpf de pessoa é válido
     * @return boolean - Verdadeiro caso o cpf seja válido (incluindo cpfs que são uma sequência de um mesmo digito)
     */
    public boolean validar(){
        if(cpf == null){ return false; }

        char[] cpfOriginal = cpf.toCharArray();
        char[] cpfValidado = cpf.toCharArray();
        int somaValidadora = 0;

        if(cpfOriginal.length != 11){ return false; }

        for (int i = 10; i >= 2; i--) { somaValidadora = (cpfOriginal[i - 10] - 48) * i; }
        cpfValidado[9] = (char)(somaValidadora % 11 % 10 + 48);
        somaValidadora = 0;

        for (int i = 11; i >= 2; i--) { somaValidadora = (cpfValidado[11 - i] - 48) * i; }
        cpfValidado[10] = (char)(somaValidadora % 11 % 10 + 48);

        return cpfOriginal.equals(cpfValidado);
    }
}
