package model;
import java.io.Serializable;

import helper.Utils;
public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;
  static private int T_Usuarios=0;
  private int Id;
  private String Nome;
  private String Email;

  
  
  public Usuario(String nome,String email) {
	  Usuario.T_Usuarios++;
	  this.Id =Usuario.T_Usuarios;
	  this.Nome = nome;
	  this.Email=email;
   }
  public int getId() {
	return this.Id;
  }
  public void setId(int id) {
	  this.Id = id;
  }
  public String getNome() {
	return this.Nome;
  }
  public void setNome(String nome) {
	  this.Nome = nome;
  }
  public String getEmail() {
	return this.Email;
  }
  public void setEmail(String email) {
	this.Email = email;
  }
  @Override
	public String toString() {
		
		return "Id: "+this.getId()+"\nNome: "+this.getNome()+"\nEmail: "+this.getEmail();
	}
}
