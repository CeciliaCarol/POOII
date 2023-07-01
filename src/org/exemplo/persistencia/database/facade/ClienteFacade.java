package org.exemplo.persistencia.database.facade;

import java.util.List;

import org.exemplo.persistencia.database.dao.IEntityDAO;
import org.exemplo.persistencia.database.dao.ClienteDAO;
import org.exemplo.persistencia.database.db.ConexaoBancoHibernate;
import org.exemplo.persistencia.database.model.Conta;
import org.exemplo.persistencia.database.model.Cliente;

public class ClienteFacade {

	private IEntityDAO<Cliente> clienteDao;
	private static ClienteFacade instance;

	private ClienteFacade() {
		clienteDao = new ClienteDAO(new ConexaoBancoHibernate());
	}

	public static ClienteFacade getInstance() {

		if (instance != null)
			return instance;
		else {
			instance = new ClienteFacade();
			return instance;
		}
	}

	public void save( String cpf, String nome) {
		clienteDao.save(new Cliente(cpf, nome));
	}

	public void delete(Integer id) {
		clienteDao.delete(new Cliente(id));
	}

	public void update(String cpf, String nome, List<Conta> contas) {
		clienteDao.update(new Cliente(cpf, nome));

	}

	public Cliente findById(Integer id) {
		return clienteDao.findById(id);
	}

	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}
}
