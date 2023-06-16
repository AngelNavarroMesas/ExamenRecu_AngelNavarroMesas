package biblioteca;

public class DVD extends Ficha{
	
	String director;
	int año;
	
	//dentro de cada constructor se comprueba que los datos introducidos sean correctos
	public DVD(int codigo, String titulo) {
		super(codigo, titulo);
	}

	public DVD(int codigo, String titulo, String genero, String director, int año) {
		super(codigo, titulo, genero);
		if(director!=null&&!director.isEmpty()){
			this.director = director;
		}
		this.año = año;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		if(director!=null&&!director.isEmpty()){
			this.director = director;
		}
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}
	
	@Override
	public int diasPrestamo() {
		return super.diasPrestamo()+3;
	}
	
	@Override
	public String toString() {
		return "Codigo: "+codigo+"\n"+
				"Titulo: "+titulo+"\n"+
				"Genero: "+genero+"\n"+
				"Director: "+director+"\n"+
				"Año de estreno: "+año+"\n"+
				"Dias de prestamo: "+diasPrestamo(); 
	}
}
