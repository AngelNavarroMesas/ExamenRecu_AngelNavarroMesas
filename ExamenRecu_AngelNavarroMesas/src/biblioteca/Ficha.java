package biblioteca;

public class Ficha implements Comparable<Ficha>{
	enum Genero{
		ciencia, historia, literatura
	}
	
	protected int codigo;
	protected String titulo;
	protected Genero genero = Genero.historia;
	
	//dentro de cada constructor se comprueba que los datos introducidos sean correctos
	public Ficha(int codigo, String titulo) {
		if(codigo>=0) {
			this.codigo = codigo;
		}
		if(titulo!=null &&!titulo.isEmpty()) {
			this.titulo = titulo;
		}
	}
	
	public Ficha(int codigo, String titulo, String genero) {
		if(codigo>=0) {
			this.codigo = codigo;
		}
		if(titulo!=null &&!titulo.isEmpty()) {
			this.titulo = titulo;
		}
		comprobarGenero(genero);
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		if(codigo>=0) {
			this.codigo = codigo;
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if(titulo!=null &&!titulo.isEmpty()) {
			this.titulo = titulo;
		}
	}

	public String getGenero() {
		return String.valueOf(genero);
	}
	
	public void setGenero(String genero) {
		comprobarGenero(genero);
	}
	
	@Override
	public String toString() {
		return "Codigo: "+codigo+"\n"+
				"Titulo: "+titulo+"\n"+
				"Genero: "+genero; 
	}
	
	//Solo seran iguales las fichas con el mismo codigo y titulo
	@Override
	public boolean equals(Object o) {
		boolean igual=false;
		Ficha e = (Ficha) o;
		if(this.codigo==e.codigo&&this.titulo.equals(e.titulo)) {
			igual=true;
		}
		return igual;
	}
	
	
	//Este metodo calcula los dias de prestamo de las fichas
	public int diasPrestamo() {
		int dias=0;
		
		switch(genero) {
		case historia:
			dias = 5;
			break;
		case ciencia:
			dias = 10;
			break;
		case literatura:
			dias = 2;
			break;
		}
		
		return dias;
	}
	
	//Este metodo comprueba que el genero introducido sea correcto
	private void comprobarGenero(String gen) {
		switch(gen.toLowerCase()) {
		case "ciencia", "historia", "literatura":
			genero = Genero.valueOf(gen);
			break;
		default:
			genero = Genero.historia;
			break;
		}
	}
	
	@Override
	public int compareTo(Ficha o) {
		int pos = 0;
		
		if(this.codigo==o.codigo) {
			pos = this.titulo.compareTo(o.titulo);
		}else if(this.codigo<o.codigo) {
			pos = -1;
		}else {
			pos =1;
		}
		
		return pos;
	}
}
