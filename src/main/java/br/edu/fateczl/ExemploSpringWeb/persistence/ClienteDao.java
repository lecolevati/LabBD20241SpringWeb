package br.edu.fateczl.ExemploSpringWeb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.ExemploSpringWeb.model.Cliente;

@Repository
public class ClienteDao implements ICrud<Cliente> {

	@Autowired
	private GenericDao gDao;
	
	@Override
	public void inserir(Cliente cli) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO cliente VALUES (?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cli.getId());
		ps.setString(2, cli.getNome());
		ps.setString(3, cli.getNascimento().toString());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Cliente cli) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE cliente SET nome = ?, dt_nasc = ? WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cli.getNome());
		ps.setString(2, cli.getNascimento().toString());
		ps.setInt(3, cli.getId());
		ps.execute();
		ps.close();
		c.close();		
	}

	@Override
	public void excluir(Cliente cli) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE cliente WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cli.getId());
		ps.execute();
		ps.close();
		c.close();		
	}

	@Override
	public Cliente buscar(Cliente cli) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT id, nome, dt_nasc FROM cliente WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cli.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cli.setId(rs.getInt("id"));
			cli.setNome(rs.getString("nome"));
			cli.setNascimento(LocalDate.parse(rs.getString("dt_nasc")));
		}
		rs.close();
		ps.close();
		c.close();
		
		return cli;
	}

	@Override
	public List<Cliente> listar() throws SQLException, ClassNotFoundException {
		List<Cliente> clientes = new ArrayList<>(); 
		Connection c = gDao.getConnection();
		String sql = "SELECT id, nome, CONVERT(CHAR(10), dt_nasc, 103) AS nasc FROM cliente";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cliente cli = new Cliente();
			cli.setId(rs.getInt("id"));
			cli.setNome(rs.getString("nome"));
			cli.setNasc(rs.getString("nasc"));
			clientes.add(cli);
		}
		rs.close();
		ps.close();
		c.close();
		
		return clientes;
	}

}
