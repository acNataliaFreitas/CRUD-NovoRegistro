package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dominio.Disciplina;
import dominio.Professor;


@Repository
public class DisciplinaDAO<jdbcTemplate> {

	@Autowired
	private jdbcTemplate jdbcTemplate;

	private class DisciplinaRowMapper implements RowMapper<Disciplina> {

		public Disciplina mapRow(ResultSet rs, int rowNum) throws SQLException {
			Disciplina disciplina = new Disciplina();
			disciplina.setCodigo(rs.getString("disciplina_cod_class"));
			disciplina.setId(rs.getInt("disciplina_id"));
			disciplina.setNome(rs.getString("disciplina_nome"));
			disciplina.setPeriodo(rs.getInt("disciplina_periodo"));
			disciplina.setProfessor(new Professor(rs.getString("professor_nome"), rs.getInt("professor_id")));
			return disciplina;
		}
	}

	public void inserir(Disciplina disciplina) {
		String sql = "insert into disciplina" + "(nome,prof,periodo,codigo_sala_classroom) values (?,?,?,?)";
		Object[] params = new Object[] { disciplina.getNome(), disciplina.getProfessor().getId(),
				disciplina.getPeriodo(), disciplina.getCodigo() };
		jdbcTemplate.update(sql, params);
	}

	public List<Disciplina> todos() {
		return jdbcTemplate.query(
				"select d.nome as disciplina_nome, d.id as disciplina_id, d.codigo_sala_classroom as disciplina_cod_class, "
						+ "d.prof as disciplina_professor_id, d.periodo as disciplina_periodo, p.nome as professor_nome, p.id as professor_id "
						+ "from professor p, disciplina d where d.prof=p.id;",
				new DisciplinaRowMapper());
	}

	public void excluir(int id) {
		String sql = "delete from disciplina where id = ?";
		Object[] params = new Object[] { id };
		jdbcTemplate.update(sql, params);
	}

	public void atualizar(Disciplina disciplina) {
		String sql = "update disciplina set nome=?,prof=?,periodo=?,codigo_sala_classroom=?" + " where id = ?";
		Object[] params = new Object[] { disciplina.getNome(), disciplina.getProfessor().getId(),
				disciplina.getPeriodo(), disciplina.getCodigo(), disciplina.getId() };
		jdbcTemplate.update(sql, params);
	}

	public Disciplina buscaPorId(int id) {
		return jdbcTemplate.queryForObject("select d.nome as disciplina_nome, d.id as disciplina_id, d.codigo_sala_classroom as disciplina_cod_class, "
				+ "d.prof as disciplina_professor_id, d.periodo as disciplina_periodo, p.nome as professor_nome, p.id as professor_id "
				+ "from professor p, disciplina d where d.prof=p.id and d.id="+id, new DisciplinaRowMapper());
	}
}
