package dominio;

public class Disciplina {
	private Integer id, periodo;
	private String nome, codigo;
	private Professor professor;

	public Disciplina() {
		super();
	}

	public Disciplina(Integer periodo, String nome, Professor professor, String codigo) {
		super();
		this.periodo = periodo;
		this.nome = nome;
		this.professor = professor;
		this.codigo = codigo;
	}

	public Disciplina(Integer id, Integer periodo, String nome, Professor professor, String codigo) {
		super();
		this.id = id;
		this.periodo = periodo;
		this.nome = nome;
		this.professor = professor;
		this.codigo = codigo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

