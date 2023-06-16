package biblioteca;

public class Libro extends Ficha{
	String autor, editorial;
	
	//dentro de cada constructor se comprueba que los datos introducidos sean correctos
	public Libro(int codigo, String titulo) {
		super(codigo, titulo);
	}
	
	public Libro(int codigo, String titulo, String genero, String autor, String editorial) {
		super(codigo, titulo, genero);
		if(autor!=null&&!autor.isEmpty()) {
			this.autor=autor;
		}
		if(editorial!=null&&!editorial.isEmpty()) {
			this.editorial=editorial;
		}
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		if(autor!=null&&!autor.isEmpty()) {
			this.autor=autor;
		}
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		if(editorial!=null&&!editorial.isEmpty()) {
			this.editorial=editorial;
		}
	}
	
	@Override
	public int diasPrestamo() {
		return super.diasPrestamo();
	}
	
	@Override
	public String toString() {
		return "Codigo: "+codigo+"\n"+
				"Titulo: "+titulo+"\n"+
				"Genero: "+genero+"\n"+
				"Autor: "+autor+"\n"+
				"Editorial: "+editorial+"\n"+
				"Dias de prestamo: "+diasPrestamo(); 
	}
}
