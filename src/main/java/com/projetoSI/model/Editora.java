package com.projetoSI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "editora")
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 60, nullable = false)
	@NotNull(message = "O campo não pode ser nulo")
	@NotEmpty(message = "O campo não pode ser vazio")
	private String nomeEditora;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}

}
