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
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(automato);
    try (FileWriter writer = new FileWriter("/home/michel/IdeaProjects/NonDeterministics/AFND.json")) {
      gson.toJson(automato, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   */
  public Automato readJson() {
    Gson gson = new Gson();
    try (Reader reader = new FileReader("/home/michel/IdeaProjects/NonDeterministics/AFND.json")) {
      // Convert JSON File to Java Object
      Automato automato = gson.fromJson(reader, Automato.class);
      System.out.println(automato.getEstadoInicial());
      return automato;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}