package br.edu.fateczl.ExemploSpringWeb.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
public class Cliente {
	
	private int id;
	private String nome;
	private LocalDate nascimento;
	private String nasc;
	
}
