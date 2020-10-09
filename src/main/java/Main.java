import domain.NaoDeterministicoE;
import exceptions.IsNotBelongOnLanguage;

public class Main {
  // BASEADO NO AUTOMATO https://ibb.co/VQJZF6R
  public static void main(String[] args) {
    String alfabeto = "abc";
    int [][][]  transicao =
                              {{{0,1},   {0,2},  },
                               {{3},     {},   },
                               {{},      {3}    } ,
                               {{3},    {3} } };
                               
    int [][] transicaoVazia = {{}, {}, {}, {}};
    int estadoInicial = 0;
    int aceitacao[] = {3};
    NaoDeterministicoE automato= new NaoDeterministicoE(aceitacao, estadoInicial,transicao,transicaoVazia, alfabeto);
    try {
      if(automato.executar("abab")) System.out.println("aceita");
      else System.out.println("Nao aceitou");
    } catch (IsNotBelongOnLanguage isNotBelongOnLanguage) {
      isNotBelongOnLanguage.printStackTrace();
    }
  }
}
