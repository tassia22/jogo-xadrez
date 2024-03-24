package PecasXadrez;

import Tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;
import Tabuleiro.Posicao;

public  abstract class Torre extends PecaXadrez {

    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }


    public String toString(){
        return "T";
    }

    @Override
    public boolean[][] possivelMovimento(){
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        //a cima
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) ){
            mat[posicao.getLinha()][posicao.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaAdv(p)) {
            mat[posicao.getLinha()][posicao.getColuna()] = true;
        }

        // a esquerda
        p.setValores(posicao.getLinha() , posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) ){
            mat[posicao.getLinha()][posicao.getColuna()] = true;
            p.setColunas(p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaAdv(p)) {
            mat[posicao.getLinha()][posicao.getColuna()] = true;
        }

        // a direita
        p.setValores(posicao.getLinha() , posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) ){
            mat[posicao.getLinha()][posicao.getColuna()] = true;
            p.setColunas(p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaAdv(p)) {
            mat[posicao.getLinha()][posicao.getColuna()] = true;
        }

        //a baixo
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) ){
            mat[posicao.getLinha()][posicao.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaAdv(p)) {
            mat[posicao.getLinha()][posicao.getColuna()] = true;
        }
        return null;

    }
}
