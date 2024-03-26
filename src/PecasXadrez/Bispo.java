package PecasXadrez;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

    public Bispo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    public String toString(){
        return "B";
    }


    public boolean[][] possivelMovimento(){
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        //noroeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna()-1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) ){
            mat[posicao.getLinha()][posicao.getColuna()] = true;
            p.setValores(p.getLinha() -1, p.getColuna() -1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaAdv(p)) {
            mat[posicao.getLinha()][posicao.getColuna()] = true;
        }

        // nordeste
        p.setValores(posicao.getLinha() -1 , posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) ){
            mat[posicao.getLinha()][posicao.getColuna()] = true;
            p.setValores(p.getLinha() -1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaAdv(p)) {
            mat[posicao.getLinha()][posicao.getColuna()] = true;
        }

        // sudeste
        p.setValores(posicao.getLinha() + 1 , posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) ){
            mat[posicao.getLinha()][posicao.getColuna()] = true;
            p.setValores(p.getLinha() + 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaAdv(p)) {
            mat[posicao.getLinha()][posicao.getColuna()] = true;
        }

        //sudo-oeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna()-1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) ){
            mat[posicao.getLinha()][posicao.getColuna()] = true;
            p.setValores(p.getLinha() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaAdv(p)) {
            mat[posicao.getLinha()][posicao.getColuna()] = true;
        }
        return null;

    }
}
