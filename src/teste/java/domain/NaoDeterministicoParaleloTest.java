package teste.java.domain;

import main.java.domain.NaoDeterministicoParalelo;
import main.java.exceptions.IsNotBelongOnLanguage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NaoDeterministicoParaleloTest {

  @Test
  /// Automato base https://ibb.co/zxRc4q3
  public void shouldBeRejectWord()   {
    int [][][]  tabelaTransicao ={
      //alf|estados         0     1
      /*q0*/               {{0,1},{0,2}},
      /*q1*/               {{3}, {}},
      /*q2*/               {{}, {3}},
      /*q3*/               {{3}, {3}}};
    int estadosAceitacao[] = {3};
    int estadoInicial = 0;
    String  fitaDeEntrada = "010";
    NaoDeterministicoParalelo afnd = new NaoDeterministicoParalelo(tabelaTransicao, estadosAceitacao,estadoInicial);
      assertEquals("Deve retornar false para  ser uma entrada invalida, baseada no automato", false,  afnd.execucao(fitaDeEntrada, true));
  }

  @Test
  /// Automato base https://ibb.co/zxRc4q3
  public void shouldBeAcceptWord()   {
    int [][][]  tabelaTransicao ={
      //alf|estados         0     1
      /*q0*/             {{0,1},{0,2}},
      /*q1*/             {{3}, {}},
      /*q2*/               {{}, {3}},
      /*q3*/              {{3}, {3}}};
    int estadosAceitacao[] = {3};
    int estadoInicial = 0;
    String  fitaDeEntrada = "011";
    NaoDeterministicoParalelo afnd = new NaoDeterministicoParalelo(tabelaTransicao, estadosAceitacao, estadoInicial);
    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no automato", true,  afnd.execucao(fitaDeEntrada, true));
  }
}


