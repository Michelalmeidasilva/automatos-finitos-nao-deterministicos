package teste.java.domain;

import main.java.exceptions.InvalidFormatException;
import main.java.utils.IOValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputValidatorTest {
  @Test
  public void shouldBeCorrectInput() throws InvalidFormatException {
    String  entrada = "011012";
    String[] alfabeto = {"0", "1", "2"};
    assertEquals("Deve retornar true para  ser uma entrada valida, baseada no alfabeto", true, IOValidator.input(entrada, alfabeto));
  }

}
