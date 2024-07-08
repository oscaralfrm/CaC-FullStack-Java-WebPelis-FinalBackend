package proyectoFinalCaC;

public class Registro {
	
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String fecha_nacimiento;
	private String pais_residencia;
	
	
	public Registro(int id, String nombre, String apellido, String email, String fecha_nacimiento, String pais_residencia) {
		
		this.id=id;
		this.nombre=nombre;
		this.email=email;
		this.fecha_nacimiento=fecha_nacimiento;
		this.pais_residencia=pais_residencia;
	}
	
	public Registro() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}


	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}


	public String getPais_residencia() {
		return pais_residencia;
	}


	public void setPais_residencia(String pais_residencia) {
		this.pais_residencia = pais_residencia;
	}
	
	
	
	
}
