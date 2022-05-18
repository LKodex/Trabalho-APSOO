package apsoo.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe Singleton para carregar variáveis de ambiente
 */
public class ConfigLoader {
    private static ConfigLoader configs;
    private String configFileName = "config.properties";
    private Properties properties;

    private ConfigLoader() {
        setDefaultConfigProperties();
        readConfigFile();
    }

    /**
     * @return ConfigLoader - Retorna a instancia Singleton
     */
    public static ConfigLoader getIstance(){
        if(configs == null) configs = new ConfigLoader();
        return configs;
    }

    /**
     * Lê o arquivo config.properties e salva nas properties
     */
    private void readConfigFile(){
        try {
            FileReader reader = new FileReader(configFileName);
            properties.load(reader);
            properties.store(System.out, "config.properties");
        } catch (FileNotFoundException e) {
            System.out.println(String.format("Não foi possível encontrar o arquivo %s", configFileName));
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(String.format("Não foi possível ler o arquivo %s", configFileName));
            System.out.println(e.getMessage());
        }
    }

    /**
     * Define as propriedades padrões
     */
    private void setDefaultConfigProperties(){
        properties = new Properties();

        properties.setProperty("DB_USER", "postgre");
        properties.setProperty("DB_PASS", "postgre");
        properties.setProperty("DB_HOST", "localhost");
        properties.setProperty("DB_PORT", "5432");
        properties.setProperty("DB_NAME", "G10_APSOO");
    }

    /**
     * @param configKeyName String - Nome da configuração que deseja recuperar
     * @return String - Valor da configuração recuperada
     */
    public String get(String configKeyName){
        // Provávelmente está dando erro se o atributo não existir
        return properties.getProperty(configKeyName);
    }
}