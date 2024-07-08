package proyectoFinalCaC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroService {
	private Conexion conexion;
	
	public RegistroService() {
		this.conexion = new Conexion();
	}
	
	public List<Registro> getAllRegistros() throws SQLException, ClassNotFoundException
	{
		List<Registro> usuarios = new ArrayList<>();
		Connection con = conexion.getConexion();
		String sql = "SELECT * FROM registro";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next())
		{
			Registro usuario = new Registro(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("email"),
						rs.getString("fecha_nacimiento"),
						rs.getString("pais_residencia")
					);
			usuarios.add(usuario);
		}
		rs.close();
		statement.close();
		con.close();
		return usuarios;
	}
	

	public Registro getRegistroById(int id) throws SQLException, ClassNotFoundException
	{
		Registro usuario = null;
		Connection con = conexion.getConexion();
		String sql = "SELECT * FROM registro WHERE id = ?";		
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next())
		{
			usuario = new Registro(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("apellido"),
					rs.getString("email"),
					rs.getString("fecha_nacimiento"),
					rs.getString("pais_residencia")
				);
		}
		rs.close();
		statement.close();
		con.close();
		return usuario;
	}
	

	public void addRegistro(Registro usuario) throws SQLException, ClassNotFoundException
	{
		Connection con=conexion.getConexion();
		String sql =
			"INSERT INTO registro"
			+ "(nombre, apellido, email, fecha_nacimiento, pais_residencia)"
			+ "VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, usuario.getNombre());
		statement.setString(2, usuario.getApellido());
		statement.setString(3, usuario.getEmail());
		statement.setString(4, usuario.getFecha_nacimiento());
		statement.setString(5, usuario.getPais_residencia());
		statement.executeUpdate();
		statement.close();	
		con.close();
	}
	
	public void updateRegistro(Registro usuario) throws SQLException, ClassNotFoundException 
    {
        Connection con = conexion.getConexion();
        String sql = "UPDATE registro "
        		+ "SET nombre = ?, apellido = ?, email = ?, "
        		+ "fecha_nacimiento = ?, pais_residencia = ? "
        		+ "WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, usuario.getNombre());
		statement.setString(2, usuario.getApellido());
		statement.setString(3, usuario.getEmail());
		statement.setString(4, usuario.getFecha_nacimiento());
		statement.setString(5, usuario.getPais_residencia());
		statement.setInt(6, usuario.getId());
		statement.executeUpdate();
		statement.close();	
        con.close();
    }
	
	public void deleteRegistro(int id) throws SQLException, ClassNotFoundException
	{
		Connection con = conexion.getConexion();
		String sql = "DELETE FROM registro WHERE id = ?";		
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
		statement.close();
		con.close();
	}
}
