package teste.java.domain;

import main.java.domain.ENFA;
import main.java.inactive.NaoDeterministicoEspontaneo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ENFATest {
  @Test
  public void shouldBeCorrectInputEnfa()   {
    String  entrada = "02020101aaaaa";
    String alfabeto = "012a";
    int [][][]  transicao =
      {{{1},  {},    {} , {}},
        {{},    {1},   {1}, {1}},
        {{},    {},    {}}, {}} ;
    //estado 0 transição espontanea nao existe
    //estado 1 transição espontanea vai pro 0
    //estado 2 transição espontanea vai pro 0
    int [][] transicaoVazia = {{}, {0}, {0}};
    int estadoInicial = 0;
    int aceitacao[] = {0};
    ENFA enfa = new ENFA(aceitacao,estadoInicial, transicao,  transicaoVazia, alfabeto, true);
    enfa.executar(entrada);
  }

  @Test
  public void shouldBeCorrectAlgorithmENFABasic()   {
    String  entrada = "02020101";
    ///
    //0  1  2
    int [][][]  transicao =   {{{1},{},{}},
      {{}, {2},{2}},
      {{}, {},{}}};
    //estado 0 transição espontanea nao existe
    //estado 1 transição espontanea vai pro 0
    //estado 2 transição espontanea vai pro 0
    int [][] transicaoVazia = {{}, {0}, {0}};
    int aceitacao[] = {0};
    NaoDeterministicoEspontaneo afnd = new NaoDeterministicoEspontaneo(transicao, aceitacao, transicaoVazia);
    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no automato", true, afnd.execucao(entrada));

  }

  @Test
  public void shouldBeCorrectAlgorithmENFA()   {
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
    ENFA enfa = new ENFA( aceitacao, estadoInicial , transicao, transicaoVazia, alfabeto, false);
    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no automato", true, enfa.executar(entrada));

  }

  @Test
  public void shouldBeCorrectAlgorithmENFADecimals()   {
    int [][][] transicao = {
//estados|alfabeto   +   -    .     0       1        2      3         4       5       6      7      8      9
          /*q0*/    {{1},{1}, {},   {},     {},      {}   , {},      {},     {},     {}     ,{}    ,{}    ,{}},
          /*q1*/    {{}, {},  {},   {2,4},  {1,4},   {1,4}, {1,4},   {1,4},  {1,4},  {1,4}  ,{1,4} ,{1,4} ,{1,4}},
          /*q2*/    {{}, {},  {},   {3,4},  {3},     {3},   {3},     {3},    {3},    {3}    ,{3}   ,{3}   ,{3}},
          /*q3*/    {{}, {},  {},   {4},    {3},     {3},   {3},     {3},    {3},    {3}    ,{3}   ,{3}   ,{3}},
         /*q4*/     {{}, {},  {8},   {},     {},      {},    {},      {},     {},     {}     ,{}    ,{}    ,{}},
         /*q5*/     {{}, {},  {},   {6},    {6},     {6},   {6},     {6},    {6},    {6}    ,{6}   ,{6}   ,{6}},
         /*q6*/     {{}, {},  {},   {7},    {7},     {7},   {7},     {7},    {7},    {7}    ,{7}   ,{7}   ,{7}},
         /*q7*/     {{}, {},  {},   {4},    {4},     {4},   {4},     {4},    {4},    {4}    ,{4}   ,{4}   ,{4}},
         /*q8*/     {{}, {},  {},   {9},    {9},     {9},   {9},     {9},    {9},    {9}    ,{9}   ,{9}   ,{9}},
         /*q9*/     {{}, {},  {},   {9},    {9},     {9},   {9},     {9},    {9},    {9}    ,{9}   ,{9}   ,{9}}};
    String  entrada = "000.6";
    //transições espontaneas    0   1    2   3   4   5  6   7   8   9   10  11  12
    int [][] transicaoVazia = {{1}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
    int aceitacao[] = {4,9};
    int estadoInicial = 0;
    String alfabeto = "+-.0123456789";
    ENFA enfa = new ENFA(aceitacao,estadoInicial, transicao,  transicaoVazia, alfabeto, true);
    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no automato", true, enfa.executar(entrada));

  }

}
