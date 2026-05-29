  package model;
  import java.io.Serializable;
  import java.util.Date;

import helper.Utils;

  public class Resultado implements Serializable {

  private static final long serialVersionUID = 1L;
  private static int T_Res=00;
  private int Id;
  private Usuario jogador;
  private Date Data;
  private double pontos=0;
  
  public Resultado(Usuario jogador,double pontuacao) {
	  T_Res++;
    this.Id=T_Res;
	this.jogador = jogador;
	this.Data = new Date();
	this.pontos=pontuacao;
  }
  
  
  public Usuario getJogador() {
	return jogador;
}
  public double getPontos() {
	   return this.pontos;
    }
  public void setPontos(float pt) {
	  this.pontos=pt;
  }
  public void setJogador(Usuario jogador) {
	this.jogador = jogador;
  }
  public Date getData() {
	return Data;
  }

  public void setData(Date data) {
	Data = data;
  }

  @Override
	public String toString() {
		
		return "Usuario:\n"+ this.getJogador()+"\nPontos: "+Utils.doubleParaString(this.pontos) + "\nData: "+Utils.dateParString(this.getData());
	}
}
