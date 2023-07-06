package model.bo;

import java.util.ArrayList;

import model.dao.CategoriaDAO;
import model.vo.Categoria;

public class CategoriaBO {
	private CategoriaDAO dao = new CategoriaDAO();
	public ArrayList<Categoria> buscaTodas()
	{
		return dao.buscarTodas();
	}
}
