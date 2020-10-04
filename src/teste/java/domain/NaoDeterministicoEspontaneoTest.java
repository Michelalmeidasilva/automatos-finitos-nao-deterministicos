package teste.java.domain;
import main.java.domain.NaoDeterministicoEspontaneo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NaoDeterministicoEspontaneoTest {

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
  public void shouldBeCorrectAlgorithmENFADecimals()   {

    int [][][] transicao = {
//alf|estados   +   -    .     0     1       2      3      4       5       6      7      8      9
    /*q0*/    {{1},{1}, {},   {},    {},     {}   ,{},     {},     {},     {}    ,{}    ,{}    ,{}   },
    /*q1*/    {{}, {},  {2},  {1,4}, {1,4},  {1,4},{1,4},  {1,4},  {1,4},  {1,4} ,{1,4} ,{1,4} ,{1,4}},
    /*q2*/    {{}, {},  {},   {3},   {3},    {3},  {3},    {3},    {3},    {3}   ,{3}   ,{3}   ,{3}  },
    /*q3*/    {{}, {},  {},   {3},   {3},    {3},  {3},    {3},    {3},    {3}   ,{3}   ,{3}   ,{3}  },
    /*q4*/    {{}, {},  {3},  {},    {},     {},   {},     {},     {},     {}    ,{}    ,{}    ,{}   },
    /*q5*/    {{}, {},  {},   {},    {},     {},   {},     {},     {},     {}    ,{}    ,{}    ,{}}  };
    String  entrada = "5.6";
    //transições espontaneas     0     1       2    3     4     5
    int [][] transicaoVazia = {{0,1}, {1}, {2}, {3,5}, {4}, {5}};
    int aceitacao[] = {5};

    NaoDeterministicoEspontaneo afnd = new NaoDeterministicoEspontaneo(transicao, aceitacao, transicaoVazia);

    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no automato", true, afnd.execucao(entrada));

  }

  @Test
  public void shouldBeCorrectAlgorithmENFA()   {

    //alfabeto por coluna
    //0  1  2
    int [][][]  transicao =   {{{0},{},{}},
                              {{0}, {2},{2}},
                              {{}, {},{}}};

    //estado 0 com a transição vazia vai pra lugar nenhum
    //estado 1 com transição vazia vai pro 0
    //estado 2 com transição vazia estado 1
    int [][] transicaoVazia = {{}, {0}, {1}};

    int aceitacao[] = {2};
    String[] alfabeto = {"0", "1"};
    String  entrada = "00001";
    NaoDeterministicoEspontaneo afnd = new NaoDeterministicoEspontaneo(transicao, aceitacao, transicaoVazia);
    afnd.execucao(entrada);
  }

}
