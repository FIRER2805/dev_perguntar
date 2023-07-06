package controller;

import java.util.ArrayList;

import model.bo.CategoriaBO;
import model.vo.Categoria;

public class CategoriaController {
	private CategoriaBO bo = new CategoriaBO();
	public ArrayList<Categoria> buscaTodas()
	{
		return bo.buscaTodas();
	}
}
