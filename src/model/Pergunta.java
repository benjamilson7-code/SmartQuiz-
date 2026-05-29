package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Pergunta implements Serializable {

    private static final long serialVersionUID = 1L;
   static private int T_perguntas=00;
   private int Id;
   private String Enunciado;
   private ArrayList<String>Opcoes= new ArrayList<>();
   private char  respostaCoreta;
   private String Categoria;
   private String Dificuldade;
 
   
public Pergunta(String enunciado2, ArrayList<String> opcoes2,
		char respostaCoreta2,
		String categoria2,
		String dificuldade2) {
	   
	Pergunta.T_perguntas++;
	this.Id=Pergunta.T_perguntas;
	this.Enunciado = enunciado2;
	this.respostaCoreta = respostaCoreta2;
	this.Categoria = categoria2;
	this.Dificuldade = dificuldade2;
	this.Opcoes=opcoes2;
	
	
}
   public int getId() {
	return this.Id;
   }
   public void setId(int id) {
	   this.Id = id;
   }
   public String getEnunciado() {
	return this.Enunciado;
   }
   public void setEnunciado(String enunciado) {
	   this.Enunciado = enunciado;
   }

   public ArrayList<String> getOpcoes() {
	return Opcoes;
   }
   public void setOpcoes(ArrayList<String> opcoes) {
	Opcoes = opcoes;
   }
   
   public char getRespostaCoreta() {
	return this.respostaCoreta;
   }
   public void setRespostaCoreta(char respostaCoreta) {
	this.respostaCoreta = respostaCoreta;
   }
   public String getCategoria() {
	return this.Categoria;
   }
   public void setCategoria(String categoria) {
	   this.Categoria = categoria;
   }
   public String getDificuldade() {
	return this.Dificuldade;
   }
   public void setDificuldade(String dificuldade) {
	   this.Dificuldade = dificuldade;
   }
   public void Listaropcoes() {
	   for(String str : this.getOpcoes()) {
		   System.out.println(str);
	   }
   }
   @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Perguta: "+this.getId()+ "\nEnunciado: "+this.getEnunciado()+"\nOpcoes: "+this.getOpcoes()+
				"\nResposta "+this.getRespostaCoreta()+"\nCategoria: "+this.getCategoria()+"\nDificuldade: "+this.getDificuldade();
	}
   
}
