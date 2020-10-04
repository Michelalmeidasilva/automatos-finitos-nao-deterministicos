package main.java.utils;

import main.java.exceptions.InvalidFormatException;

public class IOValidator {
  public static boolean input(String fitaDeEntrada, String[] alfabeto) throws InvalidFormatException {
    boolean validar = false;
    for (int i = 0; i < fitaDeEntrada.length(); i ++) {
      validar = false;
      String letra = fitaDeEntrada.charAt(i) + "";
      for (int j = 0; j < alfabeto.length ; j++){
        if(letra.contains(alfabeto[j])){
          validar = true;
        }
      }
      if(validar == false) throw new InvalidFormatException("Alfabeto não esta coerente com a Entrada ['" + fitaDeEntrada + "']");
    }
    return validar;
  }
}
