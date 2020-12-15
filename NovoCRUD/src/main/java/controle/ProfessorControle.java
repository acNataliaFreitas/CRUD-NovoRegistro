package controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ProfessorDAO;
import dominio.Professor;

public class ProfessorControle {
	@Autowired
	ProfessorDAO dao;

	@GetMapping("/professores")
	public String professoresTabela(Model modelo) {
		List<Professor> lista = dao.todos();
		modelo.addAttribute("lista", lista);
		return "professores";
	}

	@GetMapping("/professor-form")
	public String exibeForm(Model model) {
		model.addAttribute("professor", new Professor());
		return "professor-form";
	}

	@PostMapping("/professor-form")
	public String processaForm(Professor professor) {
		dao.inserir(professor);
		return "redirect:/professores";
	}

	@GetMapping("/editar-professor")
	public String exibeFormEdit(Model model, @RequestParam(name = "id", required = false) Integer cod) {
		Professor professor = dao.buscaPorId(cod);
		model.addAttribute("professor", professor);
		return "professor-form";
	}

	@PostMapping("/editar-professor")
	public String processaoFormEdit(Professor professor) {
		dao.atualizar(professor);
		return "redirect:/professores";
	}

	@GetMapping("/excluir-professor")
	public String excluir(@RequestParam(name = "id", required = true) Integer cod) {
		dao.excluir(cod);
		return "redirect:/professores";
	}
}
