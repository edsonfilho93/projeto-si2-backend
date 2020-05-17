package com.projetoSI.model;

import java.util.HashMap;
import java.util.Map;

public class Response<T> {

	private T dados;
	private int statusHTTP;
	private Map<String, String> erros;

	public T getDados() {
		return dados;
	}

	public void setDados(T dados) {
		this.dados = dados;
	}

	public int getStatusHTTP() {
		return statusHTTP;
	}

	public void setStatusHTTP(int statusHTTP) {
		this.statusHTTP = statusHTTP;
	}

	public Map<String, String> getErros() {
		if (this.erros == null)
			this.erros = new HashMap<String, String>();
		
		return erros;
	}

	public void setErros(Map<String, String> erros) {
		this.erros = erros;
	}

}
