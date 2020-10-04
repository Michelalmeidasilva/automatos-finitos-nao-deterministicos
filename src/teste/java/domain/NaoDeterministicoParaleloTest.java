package teste.java.domain;

import main.java.domain.NaoDeterministicoParalelo;
import org.junit.Test;

public class NaoDeterministicoParaleloTest {


  @Test
  public void shouldBeCorrectAlgorithmNFA()   {
    //alf|estados                  0     1
    int [][][]  transicao ={
      /*q0*/             {{0,1},{0,2}},
      /*q1*/             {{3}, {}},
      /*q2*/               {{}, {3}},
      /*q3*/              {{3}, {3}}};
    int aceitacao[] = {3};
    String  entrada = "011";
    NaoDeterministicoParalelo afnd = new NaoDeterministicoParalelo(transicao, aceitacao);
    afnd.execucao(entrada);
  }
  @Test
  public void shouldBeCorrectAlgorithmNFABasic()   {
    //alf|estados                  0     1
    int [][][]  transicao ={
      /*q0*/             {{0,1},{0,2}},
      /*q1*/             {{3}, {}},
      /*q2*/               {{}, {3}},
      /*q3*/              {{3}, {3}}};
    int aceitacao[] = {3};
    String  entrada = "011";
    NaoDeterministicoParalelo afnd = new NaoDeterministicoParalelo(transicao, aceitacao);
    afnd.execucao(entrada);
  }
}


