package Aplicacao;

import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.XadrezPosicao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public  static XadrezPosicao lerPosicaoXadrez(Scanner scan){
        try {
            String s = scan.nextLine();
            char coluna = s.charAt(0);
            int linha = integer.parseInt(s.substring(1));
            return new XadrezPosicao(coluna, linha);
        }
        catch (RuntimeException x){
            throw  new InputMismatchException("erros lendo posicao de xadrez. Valores validos sao da posicao a1 ate h8");
        }
    }

    public static void printMatch(PartidaXadrez partidaXadrez, List<PecaXadrez> cap){
        printTabuleiro(partidaXadrez.getPecas());
        System.out.println();
        printCapturaPecas(cap);
        System.out.println();
        System.out.println("turno: "+partidaXadrez.getVez());

        if (!partidaXadrez.getCheckMate()){
            System.out.println("esperando o jogador jogar: "+partidaXadrez.getJogadorAtual());
            if (PartidaXadrez.getCheck()){
                System.out.println("check");
        }else{
                System.out.println("checkmate");
                System.out.println("vencedor: "+partidaXadrez.getJogadorAtual());
            }
        }
    }
    public static void printTabuleiro(PecaXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i)+" ");
            for (int j = 0; j < pecas.length; j++){
                printPeca(pecas[i][j], false);
            }
            System.out.print("\n");
        }
        System.out.println(" a b c d e f g h");
    }

    public static void printTabuleiro(PecaXadrez[][] pecas, boolean[][] possivelMovimento) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i)+" ");
            for (int j = 0; j < pecas.length; j++){
                printPeca(pecas[i][j], possivelMovimento[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println(" a b c d e f g h");
    }
    //imprimindo uma peça
    private static void printPeca(PecaXadrez peca, boolean fundo){
        if (fundo){
            System.out.println(ANSI_BLUE_BACKGROUND );
        }
        if (peca == null){
            System.out.print("-" + ANSI_RESET);
        }else{
            if (peca.getCor() == Cor.BRANCO){
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }else{
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }

        }
        System.out.print(" ");
    }
    private static void printCapturaPecas(List<PecaXadrez> cap){
        List<PecaXadrez> branco = cap.stream().filter(x -> getCor() == Cor.BRANCO ).collect(Collectors.toList());
        List<PecaXadrez> preto = cap.stream().filter(x -> getCor() == Cor.PRETO ).collect(Collectors.toList());
        System.out.println("captura de pecas:");
        System.out.print("BRANCA: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(branco.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("PRETO: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(preto.toArray()));
        System.out.print(ANSI_RESET);
    }
}