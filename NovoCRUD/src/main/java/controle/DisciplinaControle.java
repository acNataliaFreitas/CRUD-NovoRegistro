package controle;
	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;

import dao.DisciplinaDAO;
import dao.ProfessorDAO;
import dominio.Disciplina;

	

	@Controller
	public class DisciplinaControle {

		@Autowired
		DisciplinaDAO daoDisciplina;
		
		@Autowired
		ProfessorDAO daoProfessor;

		@GetMapping("/disciplinas")
		public String disciplinasTabela(Model modelo) {
			List<Disciplina> lista = daoDisciplina.todos();
			modelo.addAttribute("lista", lista);
			return "disciplinas";
		}

		@GetMapping("/disciplina-form")
		public String exibeForm(Model model) {
			model.addAttribute("disciplina", new Disciplina());
			model.addAttribute("lista", daoProfessor.todos());
			return "disciplina-form";
		}

		@PostMapping("/disciplina-form")
		public String processaForm(Disciplina disciplina) {
			daoDisciplina.inserir(disciplina);
			return "redirect:/disciplinas";
		}

		@GetMapping("/editar-disciplina")
		public String exibeFormEdit(Model model, @RequestParam(name = "id", required = false) Integer cod) {
			Disciplina disciplina = daoDisciplina.buscaPorId(cod);
			model.addAttribute("lista", daoProfessor.todos());
			model.addAttribute("disciplina", disciplina);
			return "disciplina-form";
		}

		@PostMapping("/editar-disciplina")
		public String processaoFormEdit(Disciplina disciplina) {
			daoDisciplina.atualizar(disciplina);
			return "redirect:/disciplinas";
		}

		@GetMapping("/excluir-disciplina")
		public String excluir(@RequestParam(name = "id", required = true) Integer cod) {
			daoDisciplina.excluir(cod);
			return "redirect:/disciplinas";
		}
	}
