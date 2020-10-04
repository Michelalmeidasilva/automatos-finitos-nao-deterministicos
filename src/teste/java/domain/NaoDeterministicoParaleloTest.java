package teste.java.domain;

import main.java.domain.NaoDeterministicoParalelo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NaoDeterministicoParaleloTest {

  @Test
  /// Automato base https://ibb.co/zxRc4q3
  public void shouldBeCorrectAlgorithmNFABasic()   {
    int [][][]  tabelaTransicao ={
      //alf|estados         0     1
      /*q0*/             {{0,1},{0,2}},
      /*q1*/             {{3}, {}},
      /*q2*/               {{}, {3}},
      /*q3*/              {{3}, {3}}};
    int estadosAceitacao[] = {3};
    String  fitaDeEntrada = "011";
    NaoDeterministicoParalelo afnd = new NaoDeterministicoParalelo(tabelaTransicao, estadosAceitacao);
    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no automato", true,  afnd.execucao(fitaDeEntrada));

  }
}


