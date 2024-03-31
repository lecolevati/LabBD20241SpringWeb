package br.edu.fateczl.ExemploSpringWeb.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.ExemploSpringWeb.model.Cliente;
import br.edu.fateczl.ExemploSpringWeb.persistence.ClienteDao;

@Controller
public class ClienteController {

	@Autowired
	private ClienteDao cDao;

	@RequestMapping(name = "cliente", value = "/cliente", 
			method = RequestMethod.GET)
	public ModelAndView ClienteGet(
			@RequestParam Map<String, String> param, ModelMap model ) {
		String id = param.get("id");
		String cmd = param.get("acao");

		Cliente cliente = new Cliente();
		String erro = "";
		String saida = "";

		try {
			if (id != null & cmd != null) {
				cliente.setId(Integer.parseInt(id));
				if (cmd.equalsIgnoreCase("EDITAR")) {
					cliente = cDao.buscar(cliente);
				}
				if (cmd.equalsIgnoreCase("EXCLUIR")) {
					cDao.excluir(cliente);
					saida = "Cliente excluido com sucesso";
					cliente = new Cliente();
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("cliente", cliente);
		}
		return new ModelAndView("cliente");
	}

	@RequestMapping(name = "cliente", value = "/cliente", 
			method = RequestMethod.POST)
	public ModelAndView ClientePost(
			@RequestParam Map<String, String> param, ModelMap model ) {
		String id = param.get("id");
		String nome = param.get("nome");
		String nascimento = param.get("nascimento");
		String cmd = param.get("botao");

		Cliente cliente = new Cliente();
		if (!cmd.equalsIgnoreCase("LISTAR")) {
			cliente.setId(Integer.parseInt(id));
		}
		if (cmd.equalsIgnoreCase("INSERT") || cmd.equalsIgnoreCase("UPDATE")) {
			cliente.setNome(nome);
			cliente.setNascimento(LocalDate.parse(nascimento));
		}

		List<Cliente> clientes = new ArrayList<>();
		String erro = "";
		String saida = "";

		try {
			if (cmd.equalsIgnoreCase("INSERT")) {
				cDao.inserir(cliente);
				saida = "Cliente inserido com sucesso";
				cliente = new Cliente();
			}
			if (cmd.equalsIgnoreCase("UPDATE")) {
				cDao.atualizar(cliente);
				saida = "Cliente atualizado com sucesso";
				cliente = new Cliente();
			}
			if (cmd.equalsIgnoreCase("DELETE")) {
				cDao.excluir(cliente);
				saida = "Cliente excluido com sucesso";
				cliente = new Cliente();
			}
			if (cmd.equalsIgnoreCase("BUSCAR")) {
				cliente = cDao.buscar(cliente);
			}
			if (cmd.equalsIgnoreCase("LISTAR")) {
				clientes = cDao.listar();
				cliente = new Cliente();
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally { // RETORNO
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("cliente", cliente);
			model.addAttribute("clientes", clientes);
		}
		return new ModelAndView("cliente");
	}

}
