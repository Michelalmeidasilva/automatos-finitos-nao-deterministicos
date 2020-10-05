import domain.Automato;
import domain.NaoDeterministicoE;
import org.junit.Test;
import utils.JsonFormatter;

public class JsonFormatterTest {
  JsonFormatter formatter = new JsonFormatter();

  @Test
  public void shouldBeCreatedAJsonFile(){
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
    formatter.writeObject( naoDeterministicoE );
  }

  @Test
  public void shouldBeRead() {
    Automato automato = formatter.readJson();
  }
}
