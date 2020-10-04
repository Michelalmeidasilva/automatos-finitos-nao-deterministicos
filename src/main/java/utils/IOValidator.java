package main.java.utils;

import main.java.exceptions.InvalidFormatException;

public class IOValidator {
  private  boolean debug;
  private int linha ;
  private int coluna = 0;
  private int  profundidade = 0 ;

  public  static boolean input(String fitaDeEntrada, String[] alfabeto) throws InvalidFormatException {
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

  public  int [][][] convertMatriz3DCharToInt(char[][][] matriz){
    sizeOfMatriz3D(matriz);
    int [][][] tabelaTransicaoInt  = new int[linha][coluna][profundidade];
    for (int i = 0; i < matriz.length; i ++){
      for (int j = 0 ; j < matriz[i].length; j ++){
        for (int k = 0; k < matriz[i][j].length ; k ++){
          tabelaTransicaoInt[i][j][k] = Integer.parseInt( matriz[i][j][k] + "");
          if(debug) System.out.println(tabelaTransicaoInt[i][j][k]);
        }
      }
    }
    return tabelaTransicaoInt;
  }

  private  void sizeOfMatriz3D(char [][][] matriz) {
    this.linha = matriz.length  ;
    this.coluna = 0;
    this.profundidade = 0 ;
    for (int i = 0; i < matriz.length; i ++){
      this.coluna = matriz[i].length > coluna ? matriz[i].length : coluna;
      for (int j = 0 ; j < matriz[i].length; j ++){
        this.profundidade = matriz[i][j].length > profundidade ? matriz[i][j].length : profundidade;
      }
    }
    if(debug){
      System.out.println("Tamanho max de linha        " + linha);
      System.out.println("Tamanho max de colunas      " + coluna);
      System.out.println("Tamanho max de profundidade " + profundidade);
    }
  }

  public  int [][][] convertMatriz3DCharToInt(char[][][] matriz,boolean debug) {
    this.debug = debug;
    return  convertMatriz3DCharToInt(matriz);
  }

  public  int [] convertArrayCharToArrayInt(char[] array) {
    int [] arrayInt = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      arrayInt[i] = Integer.parseInt( array[i] + "");;
    }
    return arrayInt;
  }
}
