package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Automato;
import domain.NaoDeterministicoE;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class JsonFormatter {
  NaoDeterministicoE naoDeterministicoE;

  public JsonFormatter(NaoDeterministicoE naoDeterministicoE) {
    this.naoDeterministicoE = naoDeterministicoE;
  }

  public JsonFormatter() {
  }

  /** Recebe um objeto instanciado de automato
   * e escreve em um arquivo JSON
   * @param automato
   */
  public void writeObject(Automato automato) {
    String userDirectory =  System.getProperty("user.dir") + "/";
    String nomeDoArquivo = "afnd.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(automato);
    try (FileWriter writer = new FileWriter(userDirectory + nomeDoArquivo)) {
      gson.toJson(automato, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Automato readJson() {
    String userDirectory =  System.getProperty("user.dir") + "/";
    String nomeDoArquivo = "afnd.json";
    Gson gson = new Gson();
    System.out.println(System.getProperty("user.dir"));
    try (Reader reader = new FileReader(userDirectory + nomeDoArquivo )) {
      // Convert JSON File to Java Object
      Automato automato = gson.fromJson(reader, Automato.class);
      return automato;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}