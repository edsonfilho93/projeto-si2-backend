package com.projetoSI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "livro")
public class Livro {
	
	private static final String MSG_NOT_NULL = "O campo não pode ser nulo";
	private static final String MSG_NOT_EMPTY = "O campo não pode ser vazio";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length = 60, nullable = false)
	@NotNull(message = MSG_NOT_NULL)
	@NotEmpty(message = MSG_NOT_EMPTY)
	private String titulo;
	
	@Column(nullable = false)
	@NotNull(message = MSG_NOT_NULL)
	private int codEditora;

	@Column(nullable = false)
	@NotNull(message = MSG_NOT_NULL)
	@NotEmpty(message = MSG_NOT_EMPTY)
	@Min(value = 20, message  = "O livro deve conter no mínimo 20 páginas.")
	private String qtdPaginas;


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCodEditora() {
		return codEditora;
	}
	
	public void setCodEditora(int codEditora) {
		this.codEditora = codEditora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getQtdPaginas() {
		return qtdPaginas;
	}

	public void setQtdPaginas(String qtdPaginas) {
		this.qtdPaginas = qtdPaginas;
	}

}
