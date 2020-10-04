package teste.java.domain;

import main.java.inactive.NaoDeterministicoParalelo;
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
    String alfabeto = "01";
    String  fitaDeEntrada = "010";
    NaoDeterministicoParalelo afnd = new NaoDeterministicoParalelo(tabelaTransicao, estadosAceitacao,estadoInicial, alfabeto);
      assertEquals("Deve retornar false para  ser uma palavra invalida, ou seja, não pertence ao automato", false,  afnd.execucao(fitaDeEntrada, true));
  }




  @Test
  /// Automato base https://ibb.co/dKMfc1H
  public void shouldBeConvertUseChar()   {
    char [][][]  tabelaTransicao ={
      //alf|estados         0     1      2   3   4  5   6   7  8  9  10
      /*q0*/               {{'0','1'},{'0','2'}},
      /*q1*/               {{'3'}, {}},
      /*q2*/               {{}, {'3'}},
      /*q3*/               {{'3'}, {'3'}}};
    char estadosAceitacao[] = {'0', '1'};
    char estadoInicial  =0;
    String alfabeto = "0123";
    String  fitaDeEntrada = "0001a";
    NaoDeterministicoParalelo afnd = new NaoDeterministicoParalelo(tabelaTransicao, estadosAceitacao, estadoInicial, alfabeto);
    assertEquals("Deve retornar true para  ser uma palavra valida, logo pertence ao automato", true,  afnd.execucao(fitaDeEntrada, true));

  }


  @Test
  /// Automato base https://ibb.co/zxRc4q3
  public void   shouldBeAcceptWord()   {
    int [][][]  tabelaTransicao ={
      //alf|estados         0     1
      /*q0*/             {{0,1},{0,2}},
      /*q1*/             {{3}, {}},
      /*q2*/               {{}, {3}},
      /*q3*/              {{3}, {3}}};
    int estadosAceitacao[] = {3};
    int estadoInicial = 0;
    String  fitaDeEntrada = "000";
    String alfabeto = "01";
    NaoDeterministicoParalelo afnd = new NaoDeterministicoParalelo(tabelaTransicao, estadosAceitacao, estadoInicial, alfabeto);
    assertEquals("Deve retornar false para  ser uma palavra invalida, logo pertence ao automato", true,  afnd.execucao(fitaDeEntrada, true));
  }
}

