package view;
import java.util.ArrayList;
import model.Usuario;
import model.Pergunta;
import model.Resultado;
import java.util.Scanner;

import helper.Utils;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SmartQuiz {
	
     static  ArrayList<Usuario> Usuarios = new ArrayList<>();
     static ArrayList<Pergunta> Perguntas = new ArrayList<>();
     static ArrayList<Resultado>Resultados= new ArrayList<>();
     static Scanner teclado = new Scanner(System.in);
     
       public static void gravarUsuarios(ArrayList<Usuario> pessoas, String ficheiro) {
         try (ObjectOutputStream out =
                 new ObjectOutputStream(new FileOutputStream(ficheiro))) {
             out.writeObject(pessoas);
             System.out.println("Usuarios gravada com sucesso!");
             
         } catch (IOException e) {
             System.out.println("Erro ao gravar: " + e.getMessage());
         }
       }
       
       
      public static ArrayList<Usuario> lerUsuarios(String ficheiro) {
    	    ArrayList<Usuario> usuarios = new ArrayList<>();
    	    
    	    try (ObjectInputStream in =
    	            new ObjectInputStream(new FileInputStream(ficheiro))) {
    	        usuarios = (ArrayList<Usuario>) in.readObject();
    	        System.out.println("Lista lida com sucesso!");
    	        
    	    } catch (IOException | ClassNotFoundException e) {
    	        System.out.println("Erro ao ler: " + e.getMessage()+":  "+ficheiro);
    	    }
    	    return usuarios;
    	 }
     
   public static void gravarPerguntas(ArrayList<Pergunta> perguntas, String ficheiro) {
          try (ObjectOutputStream out =
                  new ObjectOutputStream(new FileOutputStream(ficheiro))) {
              out.writeObject(perguntas);
              System.out.println("Usuarios gravada com sucesso!");
              
          } catch (IOException e) {
              System.out.println("Erro ao gravar: " + e.getMessage());
          }
      }
      
       public static ArrayList<Pergunta> lerPerguntas(String ficheiro) {
     	    ArrayList<Pergunta> perguntas = new ArrayList<>();
     	    
     	    try (ObjectInputStream in =
     	            new ObjectInputStream(new FileInputStream(ficheiro))) {
     	    	perguntas = (ArrayList<Pergunta>) in.readObject();
     	        System.out.println("Lista lida com sucesso!");
     	        
     	    } catch (IOException | ClassNotFoundException e) {
     	        System.out.println("Erro ao ler: " + e.getMessage() +":  "+ficheiro);
     	    }
     	    return perguntas;
     	}
       
       public static void gravarResultados(ArrayList<Resultado> Resultados, String ficheiro){
           try (ObjectOutputStream out =
                   new ObjectOutputStream(new FileOutputStream(ficheiro))) {
               out.writeObject(Resultados);
               System.out.println("Usuarios gravada com sucesso!");
               
           } catch (IOException e) {
               System.out.println("Erro ao gravar: " + e.getMessage());
           }
       }
       
       public static ArrayList<Resultado> lerResultados(String ficheiro) {

    	    ArrayList<Resultado> resultados = new ArrayList<>();

    	    try (ObjectInputStream in =
    	            new ObjectInputStream(new FileInputStream(ficheiro))) {

    	        resultados = (ArrayList<Resultado>) in.readObject();
    	        System.out.println("Lista lida com sucesso!");

    	    } catch (IOException | ClassNotFoundException e) {
    	        System.out.println("Arquivo ainda não existe. Criando novo...");
    	    }

    	    return resultados;
    	}
       
        public static Usuario login(){
        	Usuarios= lerUsuarios("usuarios.data");
        	System.out.println(Usuarios);
        	System.out.println("Introduza o Seu Email" );
        	String email=teclado.nextLine();
        	  for (Usuario us :  Usuarios) {
        		  if (us.getEmail().equals(email)) {
        			 System.out.println("Login feito");
        			  return us;
        		  }		  
        	  }
        	   System.out.println("Usuario não Identificado: ");
        	   return null;
        }
        
        
        public static void menu(Usuario User) {
        	
        	int opcao=0;
            Usuarios= lerUsuarios("usuarios.data");
            Resultados=lerResultados("Resultados.data");
            Perguntas=lerPerguntas("Perguntas.data");
             
                	 System.out.println("Benvino Usuario: "+User.getId());
                	 System.out.println("---------------------------------");
                	 System.out.println("Escolha uma das Opcoes");
                	 System.out.println("1-Jogar");
                	 System.out.println("2-Historico");
                	 System.out.println("0-Terminar");
                	 
                	 try {
                		 opcao=Integer.parseInt(SmartQuiz.teclado.nextLine());
					} catch (Exception e) {
						System.out.println("Erro: "+e.getMessage());
						Utils.pausar(3);
						menu(User);
					}
                	switch (opcao) {
					case 0: {
						System.out.println("Ate a proxima !!!");
						Utils.pausar(5);
						gravarPerguntas(Perguntas,"Perguntas.data");
						gravarResultados(Resultados,"Resultados.data");
						gravarUsuarios(Usuarios,"usuarios.data");
				        System.exit(0);
			             menu(User);
					}
					case 1:
						   Jogar(User);
						   break;
					case 2:
						 VerHistorico(User);
					default:
						menu(User);
						throw new IllegalArgumentException("Unexpected value: " + opcao);
					} 
                 
            
        	
        }
        
	private static void VerHistorico(Usuario Jogador) {
			int cont_res=0;
		    System.out.println(" Historico: ");
			for (Resultado res: Resultados) {
				if(res.getJogador().getId()==Jogador.getId()) {
					cont_res+=1;
					System.out.println(res);
				}
			}
			if(cont_res==0) {
				System.out.println("o Usuaro : "+Jogador.getId()+" Ainda nao tem historico");
			}
		}


    private static void GravarUsuario(){
    	System.out.println("Registrar Usuario :  ");
    	System.out.println("Introduza o Nome:  ");
    	     String Nome=teclado.nextLine();
    	System.out.println("Introduza o Email:  ");
    	     String email=teclado.nextLine();
    	Usuario p= new Usuario(Nome,email);
    	Usuarios.add(p);
    	gravarUsuarios(Usuarios,"usuarios.data");
    }
	
    private static void Jogar(Usuario Jogador) {

        System.out.println("Escolha a resposta certa");

        Resultado res = new Resultado(Jogador, 0);

        for (Pergunta p : Perguntas) {

            System.out.println(p.getEnunciado());
            p.Listaropcoes();

            char rep = teclado.next().charAt(0);
            teclado.nextLine(); // limpar buffer

            if (rep != p.getRespostaCoreta()) {

                System.out.println("Erro!!!");
                System.out.println("A resposta certa era: " + p.getRespostaCoreta());
                System.out.println("A tua pontuação foi de: " + res.getPontos());

                Resultados.add(res);
                gravarResultados(Resultados, "Resultados.data");

                Utils.pausar(5);
                menu(Jogador);
                return;
            }

            // Pontuação correta
            if (p.getDificuldade().equals("Difícil")) {
                res.setPontos((float) (res.getPontos() + 50));
            }
            else if (p.getDificuldade().equals("Normal")) {
                res.setPontos((float) (res.getPontos() + 35));
            }
            else if (p.getDificuldade().equals("Fácil")) {
                res.setPontos((float) (res.getPontos() + 20));
            }
        }

        // Se acertar todas
        System.out.println("Parabéns! Você terminou o quiz!");
        System.out.println("Pontuação final: " + res.getPontos());

        Resultados.add(res);
        gravarResultados(Resultados, "Resultados.data");

        Utils.pausar(5);
        menu(Jogador);
    }
	
	public static ArrayList<Pergunta> registrarPerguntas() {

	    ArrayList<Pergunta> lista = new ArrayList<>();

	    // PERGUNTA 1
	    ArrayList<String> op1 = new ArrayList<>();
	    op1.add("a) Porto");
	    op1.add("b) Lisboa");
	    op1.add("c) Coimbra");
	    op1.add("d) Faro");

	    lista.add(new Pergunta(
	            "Qual é a capital de Portugal?",
	            op1, 'b',
	            "Geografia", "Fácil"));

	    // PERGUNTA 2
	    ArrayList<String> op2 = new ArrayList<>();
	    op2.add("a) 5");
	    op2.add("b) 6");
	    op2.add("c) 7");
	    op2.add("d) 8");

	    lista.add(new Pergunta(
	            "Quantos dias tem uma semana?",
	            op2, 'c',
	            "Conhecimentos Gerais", "Fácil"));

	    // PERGUNTA 3
	    ArrayList<String> op3 = new ArrayList<>();
	    op3.add("a) Verde");
	    op3.add("b) Vermelho");
	    op3.add("c) Azul");
	    op3.add("d) Amarelo");

	    lista.add(new Pergunta(
	            "Qual é a cor do céu em um dia limpo?",
	            op3, 'c',
	            "Ciência", "Fácil"));

	    // PERGUNTA 4
	    ArrayList<String> op4 = new ArrayList<>();
	    op4.add("a) Tigre");
	    op4.add("b) Elefante");
	    op4.add("c) Leão");
	    op4.add("d) Leopardo");

	    lista.add(new Pergunta(
	            "Qual destes animais é conhecido como o rei da selva?",
	            op4, 'c',
	            "Natureza", "Fácil"));

	    // PERGUNTA 5
	    ArrayList<String> op5 = new ArrayList<>();
	    op5.add("a) Europa");
	    op5.add("b) Ásia");
	    op5.add("c) África");
	    op5.add("d) América");

	    lista.add(new Pergunta(
	            "Em que continente fica o Egito?",
	            op5, 'c',
	            "Geografia", "Normal"));

	    // PERGUNTA 6
	    ArrayList<String> op6 = new ArrayList<>();
	    op6.add("a) Pablo Picasso");
	    op6.add("b) Leonardo da Vinci");
	    op6.add("c) Van Gogh");
	    op6.add("d) Michelangelo");

	    lista.add(new Pergunta(
	            "Quem pintou a obra Mona Lisa?",
	            op6, 'b',
	            "Arte", "Normal"));

	    // PERGUNTA 7
	    ArrayList<String> op7 = new ArrayList<>();
	    op7.add("a) Júpiter");
	    op7.add("b) Marte");
	    op7.add("c) Vénus");
	    op7.add("d) Saturno");

	    lista.add(new Pergunta(
	            "Qual planeta é conhecido como o Planeta Vermelho?",
	            op7, 'b',
	            "Astronomia", "Normal"));

	    // PERGUNTA 8
	    ArrayList<String> op8 = new ArrayList<>();
	    op8.add("a) Atlântico");
	    op8.add("b) Índico");
	    op8.add("c) Ártico");
	    op8.add("d) Pacífico");

	    lista.add(new Pergunta(
	            "Qual é o maior oceano do mundo?",
	            op8, 'd',
	            "Geografia", "Normal"));

	    // PERGUNTA 9
	    ArrayList<String> op9 = new ArrayList<>();
	    op9.add("a) 1776");
	    op9.add("b) 1789");
	    op9.add("c) 1804");
	    op9.add("d) 1815");

	    lista.add(new Pergunta(
	            "Em que ano ocorreu a Revolução Francesa?",
	            op9, 'b',
	            "História", "Difícil"));

	    // PERGUNTA 10
	    ArrayList<String> op10 = new ArrayList<>();
	    op10.add("a) Prata");
	    op10.add("b) Cobre");
	    op10.add("c) Ouro");
	    op10.add("d) Alumínio");

	    lista.add(new Pergunta(
	            "Qual é o elemento químico representado pelo símbolo Au?",
	            op10, 'c',
	            "Química", "Difícil"));

	    // PERGUNTA 11
	    ArrayList<String> op11 = new ArrayList<>();
	    op11.add("a) Indonésia");
	    op11.add("b) Filipinas");
	    op11.add("c) Noruega");
	    op11.add("d) Suécia");

	    lista.add(new Pergunta(
	            "Qual país tem o maior número de ilhas no mundo?",
	            op11, 'd',
	            "Geografia", "Difícil"));

	    // PERGUNTA 12
	    ArrayList<String> op12 = new ArrayList<>();
	    op12.add("a) Fernando Pessoa");
	    op12.add("b) Luís de Camões");
	    op12.add("c) Eça de Queirós");
	    op12.add("d) José Saramago");

	    lista.add(new Pergunta(
	            "Quem escreveu a obra Os Lusíadas?",
	            op12, 'b',
	            "Literatura", "Difícil"));

	    return lista;
	}
	
	public static void main(String[] args) {
	
		
	   Usuario User;
		User=login();
		 if(User!=null){
			menu(User);
		 }
		 else {
		  SmartQuiz.main(args);
		 }
		 /*
		 Perguntas=registrarPerguntas();
		 gravarPerguntas(Perguntas, "Perguntas.data");
		SmartQuiz.GravarUsuario();*/
	}

}
