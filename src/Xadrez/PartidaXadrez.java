package Xadrez;

import PecasXadrez.Rei;
import PecasXadrez.Torre;
import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;
public class PartidaXadrez {
    private Tabuleiro tabuleiro;

    public PartidaXadrez(){
        tabuleiro = new Tabuleiro(8,8);
        iniciaPartida();
    }
    public PecaXadrez[][] getPecas(){
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++){
            for (int j = 0; j < tabuleiro.getColunas(); j++){
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i,j);

            }
        }
        return mat;
    }
    private void iniciaPartida(){
        tabuleiro.lugarPeca(new Torre(tabuleiro, Cor.PRETO), new Posicao(2, 1));
        tabuleiro.lugarPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(2, 4));
    }
}
