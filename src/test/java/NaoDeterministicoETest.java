package teste.java.domain;

import main.java.domain.NaoDeterministicoE;
import main.java.exceptions.IsNotBelongOnLanguage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NaoDeterministicoETest {
  @Test
  public void shouldBeCorrectInputEnfa() throws IsNotBelongOnLanguage   {
    String  entrada = "02020101aaaaa";
    String alfabeto = "012a";
    int [][][]  transicao =
       {{{1},   {},    {},  {}},
        {{},    {1},   {1}, {1}},
        {{},    {},    {}}, {}} ;
    //estado 0 transição espontanea nao existe
    //estado 1 transição espontanea vai pro 0
    //estado 2 transição espontanea vai pro 0
    int [][] transicaoVazia = {{}, {0}, {0}};
    int estadoInicial = 0;
    int aceitacao[] = {0};
    NaoDeterministicoE naoDeterministicoE = new NaoDeterministicoE(aceitacao,estadoInicial, transicao,  transicaoVazia, alfabeto);
    naoDeterministicoE.setDebug(true);
    naoDeterministicoE.executar(entrada);
  }

  @Test
  public void shouldBeCorrectAlgorithmENFABasic() throws IsNotBelongOnLanguage   {
    ///
    //0  1  2
    int [][][]  transicao = {
      // 0       1
  /*q0*/{{0,1},{0,2}},
  /*q1*/{{3},  {}},
  /*q2*/{{},   {3}},
  /*q3*/{{3},  {3}}
      };
    //estado 0 transição espontanea
    //estado 1 transição espontanea
    //estado 2 transição espontanea
    //estado 3 transição espontanea
    int [][] transicaoVazia = {{}, {}, {}, {}};
    String alfabeto = "01";
    String  entrada = "00000000";
    int aceitacao[] = {3};
    int estadoInicial = 0;
    NaoDeterministicoE naoDeterministicoE = new NaoDeterministicoE(aceitacao,estadoInicial,  transicao, transicaoVazia, alfabeto);
    naoDeterministicoE.setDebug(true);
    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no automato", true, naoDeterministicoE.executar(entrada));

  }

  @Test
  public void shouldBeCorrectAlgorithmENFA() throws IsNotBelongOnLanguage {
    //alfabeto por coluna
    //estados por linha 0  1  2
    int [][][]  transicao =   {
      {{1},{},{}},
      {{1}, {2},{2}},
      {{}, {},{}}};
    int [][] transicaoVazia = {{}, {}, {}};
    int estadoInicial = 0;
    int aceitacao[] = {1};
    String alfabeto = "abc";
    String  entrada = "aaaa";
    NaoDeterministicoE naoDeterministicoE = new NaoDeterministicoE( aceitacao, estadoInicial , transicao, transicaoVazia, alfabeto);
    naoDeterministicoE.setDebug(true);
    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no automato", true, naoDeterministicoE.executar(entrada));

  }

  @Test
  public void shouldBeCorrectAlgorithmENFADecimals() throws IsNotBelongOnLanguage {
    int [][][] transicao = {
//estados|alfabeto   +   -    .     0       1        2      3         4       5       6      7      8      9
          /*q0*/    {{1},{1}, {},   {},     {},      {}   , {},      {},     {},     {}     ,{}    ,{}    ,{}},
          /*q1*/    {{}, {},  {},   {2,4},  {1,4},   {1,4}, {1,4},   {1,4},  {1,4},  {1,4}  ,{1,4} ,{1,4} ,{1,4}},
          /*q2*/    {{}, {},  {},   {3,4},  {3},     {3},   {3},     {3},    {3},    {3}    ,{3}   ,{3}   ,{3}},
          /*q3*/    {{}, {},  {},   {4},    {3},     {3},   {3},     {3},    {3},    {3}    ,{3}   ,{3}   ,{3}},
         /*q4*/     {{}, {},  {8},  {},     {},      {},    {},      {},     {},     {}     ,{}    ,{}    ,{}},
         /*q5*/     {{}, {},  {},   {6},    {6},     {6},   {6},     {6},    {6},    {6}    ,{6}   ,{6}   ,{6}},
         /*q6*/     {{}, {},  {},   {7},    {7},     {7},   {7},     {7},    {7},    {7}    ,{7}   ,{7}   ,{7}},
         /*q7*/     {{}, {},  {},   {4},    {4},     {4},   {4},     {4},    {4},    {4}    ,{4}   ,{4}   ,{4}},
         /*q8*/     {{}, {},  {},   {9},    {9},     {9},   {9},     {9},    {9},    {9}    ,{9}   ,{9}   ,{9}},
         /*q9*/     {{}, {},  {},   {9},    {9},     {9},   {9},     {9},    {9},    {9}    ,{9}   ,{9}   ,{9}}};
    String  entrada = "55";
    //transições espontaneas    0   1    2   3   4   5  6   7   8   9   10  11  12
    int [][] transicaoVazia = {{1}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
    int aceitacao[] = {4,9};
    int estadoInicial = 0;
    String alfabeto = "+-.0123456789";
    NaoDeterministicoE naoDeterministicoE = new NaoDeterministicoE(aceitacao,estadoInicial, transicao,  transicaoVazia, alfabeto);
    naoDeterministicoE.setDebug(true);
    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no automato", true, naoDeterministicoE.executar(entrada));

  }

}
