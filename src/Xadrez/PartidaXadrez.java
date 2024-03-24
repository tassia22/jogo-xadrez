package Xadrez;

import PecasXadrez.Rei;
import PecasXadrez.Torre;
import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;
import Tabuleiro.Peca;
public class PartidaXadrez {
    private Tabuleiro tabuleiro;

    public PartidaXadrez(){
        tabuleiro = new Tabuleiro(8,8);
        iniciaPartida();
    }
    public static PecaXadrez[][] getPecas(){
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++){
            for (int j = 0; j < tabuleiro.getColunas(); j++){
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i,j);

            }
        }
        return mat;
    }
    public boolean[][] possivelMovimento(XadrezPosicao origemPosicao){
        Posicao posicao = origemPosicao.toPosicao();
        validaOrigemPosicao(posicao);
        return tabuleiro.peca(posicao).possivelMovimento();
    }
    public PecaXadrez executarMovimentoXadrez(XadrezPosicao origemPosicao, XadrezPosicao destinoPosicao){
        Posicao origem = origemPosicao.toPosicao();
        Posicao destino = destinoPosicao.toPosicao();
        validaOrigemPosicao(origem);
        validaAlvoPosicao(origem, destino);
        Peca capturaPeca = makeMove(origem, destino);
        return (PecaXadrez) capturaPeca;
    }
    private Peca makeMove(Posicao origem, Posicao destino){
        Peca p = tabuleiro.removePeca(origem);
        Peca capturaPeca = tabuleiro.removePeca(destino);
        tabuleiro.lugarPeca(p, destino);
        return capturaPeca;
    }
    public void  validaOrigemPosicao(Posicao posicao){
        if (!tabuleiro.existePosicao(posicao)){
            throw new XadrezException("nao existe peca na posicao de origem");

        }
        if (!tabuleiro.peca(posicao).existePossivelMovimento()){
            throw  new XadrezException("nao existe movimentos possiveis para a peça escolhida");
        }
    }
    private void validaAlvoPosicao( Posicao origem, Posicao destino){
        if (!tabuleiro.peca(origem).possivelMovimento(destino)){
            throw  new XadrezException("a peca escolhida nao pode se mover para a posicao de destino");
        }
    }

    private void lugarNovaPeca(char colunas, int linha, PecaXadrez peca){
        tabuleiro.lugarPeca(peca, new XadrezPosicao(colunas, linha).toPosicao());
    }
    private void iniciaPartida(){
        lugarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        lugarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('d', 8, new Torre(tabuleiro, Cor.PRETO));
    }
}
