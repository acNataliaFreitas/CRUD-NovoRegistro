package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Repository;

import dominio.Professor;

@Repository
public class ProfessorDAO {

	@Autowired
	private JndiTemplate jdbcTemplate;

	private class ProfessorRowMapper implements RowMapper<Professor> {

		public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Professor professor = new Professor();
			professor.setNome(rs.getString("nome"));
			professor.setId(rs.getInt("id"));
			return professor;
		}
	}

	public List<Professor> todos() {
		return jdbcTemplate.query("select * from professor", new ProfessorRowMapper());
	}

	public void inserir(Professor professor) {
		String sql = "insert into professor(nome) values (?);";
		Object[] params = new Object[] { professor.getNome() };
		jdbcTemplate.update(sql, params);
	}

	public Professor buscaPorId(Integer cod) {
		return jdbcTemplate.queryForObject("select * from professor where id=" + cod, new ProfessorRowMapper());
	}

	public void atualizar(Professor professor) {
		String sql = "update professor set nome=? where id = ?;";
		Object[] params = new Object[] { professor.getNome(), professor.getId() };
		jdbcTemplate.update(sql, params);
	}

	public void excluir(Integer cod) {
		String sql = "delete from professor where id = ?;";
		Object[] params = new Object[] { cod };
		jdbcTemplate.update(sql, params);
	}
}
