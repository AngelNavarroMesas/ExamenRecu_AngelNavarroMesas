package biblioteca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class FichaMain {
	
	static TreeSet<Ficha> coleccionFichas = new TreeSet<Ficha>();
	static File fichero = new File("src/biblioteca/fichas.txt");
	static Scanner sc = new Scanner(System.in);
	static int codigo, año;
	static String titulo="", genero="", autor="", editorial="", director="";
	
	public static void main(String[] args) {
		int opc;
		//Damos la bienvenida
		System.out.println("-----Bienvenido-----");
		//Llamamos al metodo leerFichero para cargar el contenido de fichas.txt en la coleccionFichas
		leerFichero();
		
		do{
			//Dependiendo de lo que el usuario elija se llamara a un metodo u otro
			System.out.println();
			System.out.println("Que desea hacer?");
			System.out.println("1. Añadir ficha");
			System.out.println("2. Listar fichas");
			System.out.println("3. Modificar una ficha");
			System.out.println("4. Eliminar una ficha");
			System.out.println("5. Guardar");
			System.out.println("0. Salir");
			System.out.println();
			opc = sc.nextInt();
			sc.nextLine();
			switch(opc) {
			case 1:
				añadir();
				break;
			case 2:
				listar();
				break;
			case 3:
				modificar();
				break;
			case 4:
				eliminar();
				break;
			case 5:
				guardar();
				break;
			case 0:
				System.out.println("-----Saliendo-----");
				break;
			default:
				System.out.println("Elija una de las 6 opciones");
				break;
			}
			
		}while(opc!=0);
	}
	
	//Este metodo sirve para añadir otra ficha mas a la coleccion
	private static void añadir() {
		System.out.println("Introduzca el codigo de la ficha");
		codigo = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduzca el titulo de la ficha");
		titulo = sc.nextLine();
		
		//Le hemos pedido datos al usuario para crear un objeto
		Ficha f = new Ficha(codigo, titulo);
		
		//si el objeto coincide con alguno de la coleccion se le informa al usuario que ya esta creado 
		if(coleccionFichas.contains(f)) {
			System.out.println("La ficha introducida ya existe");
		}else {
			//si el objeto no coincide se le pide el genero al usuario y si es un libro o un dvd
			String opc="";
			System.out.println("Introduzca el genero (historia, literatura, ciencia)");
			genero = sc.nextLine();
			
			do {
				System.out.println("La ficha es de un libro o un DVD? (l/d)");
				opc = sc.nextLine();
				opc.toLowerCase();
				
				//si es un libro creamos un objeto libro y le introducimos la informacion que nos proporciona el usuario 
				if(opc.equals("l")) {
					System.out.println("Introduzca el autor");
					autor = sc.nextLine();
					System.out.println("Introduzca la editorial");
					editorial = sc.nextLine();
					Libro libro = new Libro(codigo, titulo, genero, autor, editorial);
					//por ultimo se añade a la coleccion
					coleccionFichas.add(libro);
					
					//si es un dvd creamos un objeto DVD y le introducimos la informacion que nos proporciona el usuario 
				}else if(opc.equals("d")) {
					System.out.println("Introduzca el director");
					director = sc.nextLine();
					System.out.println("Introduzca el año de estreno");
					año = sc.nextInt();
					sc.nextLine();					
					DVD dvd = new DVD(codigo, titulo, genero, director, año);
					//por ultimo lo añadimos a la coleccion
					coleccionFichas.add(dvd);
					
				}else {
					System.out.println("Introduzca una de las dos opciones");
				}
				//el usuario tiene que responder obligatoriamente si es un libro o un dvd
			}while(!opc.equals("l")&&!opc.equals("d"));
		}
	}
	
	//Este metodo nos listara la informacion de la coleccion 
	private static void listar() {
		//si la coleccion esta vacia lo indicara
		if (coleccionFichas.size() == 0) {
			System.out.println("No hay fichas");
			//si no esta vacia recorrera la coleccion imprimiendo las fichas con una separacion entre ellas
		} else {
			for (Ficha i:coleccionFichas) {
				System.out.println(i);
				System.out.println("-------------------------");
			}
		}
	}
	
	//este metodo nos permite modificar las fichas ya existentes
	private static void modificar() {
		int opc;
		System.out.println("Indique el codigo de la ficha a modificar");
		codigo = sc.nextInt();
		sc.nextLine();
		System.out.println("Indique el titulo de la ficha a modificar");
		titulo = sc.nextLine();
		
		Ficha f = new Ficha(codigo, titulo);
		//creamos un objeto con el codigo y el titulo que nos de el usuario y lo comparamos con las fichas de la coleccion
		
		for (Ficha i : coleccionFichas) {
			//si coincide con alguna ficha le preguntamos al usuario que dato desea cambiar
			if(i.equals(f)) {
				do {
					System.out.println("Que dato quiere modificar?");
					System.out.println("1. Genero");
					//Dependiendo de si la ficha es un libro o un dvd los datos cambian
					if(i instanceof Libro) {
						System.out.println("2. Autor");
						System.out.println("3. Editorial");
					}else if(i instanceof DVD) {
						System.out.println("2. Director");
						System.out.println("3. Año de estreno");
					}
					opc = sc.nextInt();
					sc.nextLine();
				}while(opc!=1&&opc!=2&&opc!=3);
				
				//los datos modificados dependeran de la opcion elejida
				switch(opc) {
				case 1:
					System.out.println("Introduzca el genero");
					i.setGenero(sc.nextLine());
					
					break;
				case 2:
					if(i instanceof Libro) {
						System.out.println("Introduzca el autor");
						((Libro) i).setAutor(sc.nextLine());
						
					}else if(i instanceof DVD) {
						System.out.println("Introduzca el director");
						((DVD) i).setDirector(sc.nextLine());
						
					}
					break;
				case 3:
					if(i instanceof Libro) {
						System.out.println("Introduzca la editorial");
						((Libro) i).setEditorial(sc.nextLine());
						
					}else if(i instanceof DVD) {
						System.out.println("Introduzca el año de estreno");
						((DVD) i).setAño(sc.nextInt());
						sc.nextLine();
						
					}
					break;
				}
				//si la ficha se modifica sin problemas se le indica al usuario
				System.out.println("Ficha modificada correctamente");
				break;
				
				//si llegamos al final de la coleccion sin ninguna coincidencia se le indica al usuario que la ficha introducida no existe
			}else if(i.equals(coleccionFichas.last())){
				System.out.println("La ficha introducida no existe");
			}
		}
	}
	
	//Este metodo elimina una ficha introducida por el usuario
	private static void eliminar() {
		System.out.println("Introduzca el codigo de la ficha a eliminar");
		codigo = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduzca el titulo de la ficha a eliminar");
		titulo = sc.nextLine();
		
		//Creamos un objeto con el codigo y el titulo introducidos por el usuario
		Ficha f = new Ficha(codigo, titulo);
		
		//si el objeto coincide con alguna ficha se borra y se le indica al usuario
		if(coleccionFichas.remove(f)){
			System.out.println("Ficha eliminada correctamente");
			//si no coincide se le indica que no existe
		}else {
			System.out.println("La ficha introducida no existe");
		}
	}
	
	//guarda la coleccion en un fichero txt
	private static void guardar() {
		try {
			//si el fichero no existe lo creamos
            if (!fichero.exists()) {
                fichero.createNewFile();
            }
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            
            //recorremos la coleccion
            for(Ficha i: coleccionFichas) {
            	//Si la ficha pertenece a un libro creamos un objeto libro y usamos el bufferedWriter para guardar la informacion en el txt
            	if(i instanceof Libro) {
            		Libro libro = (Libro) i;
            		bw.write("Libro|"+i.getCodigo()+"|"+i.getTitulo()+"|"+i.getGenero()+"|"+libro.getAutor()+"|"+libro.getEditorial());
            		
            		//Si la ficha pertenece a un dvd creamos un objeto dvd y usamos el bufferedWriter para guardar la informacion en el txt
            	}else if(i instanceof DVD) {
            		DVD dvd = (DVD) i;
            		bw.write("DVD|"+i.getCodigo()+"|"+i.getTitulo()+"|"+i.getGenero()+"|"+dvd.getDirector()+"|"+dvd.getAño());
            	}
            	bw.newLine();
            }
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	//Este metodo lee el fichero y pasa la informacion a la coleccion al iniciar el programa
	private static TreeSet<Ficha> leerFichero() {
		try {
			String linea;
			String[] info;
			Ficha ficha = null;
			Scanner scf = new Scanner(new FileReader(fichero));
			
			//este bucle se repetira mientras el txt tenga lineas
	        while (scf.hasNext()){
	            linea = scf.nextLine();
	            info = linea.split("[|]");
	            
	            if(info[0].equals("DVD")) {
	            	ficha = new DVD(Integer.parseInt(info[1]), info[2], info[3], info[4], Integer.parseInt(info[5]));
	            }else if(info[0].equals("Libro")) {
	            	ficha = new Libro(Integer.parseInt(info[1]), info[2], info[3], info[4], info[5]);
	            }
	            coleccionFichas.add(ficha);
	        }
		}catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		return coleccionFichas;
	}
	

}
