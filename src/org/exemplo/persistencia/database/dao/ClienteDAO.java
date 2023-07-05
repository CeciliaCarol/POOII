package org.exemplo.persistencia.database.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.exemplo.persistencia.database.db.IConnection;
import org.exemplo.persistencia.database.model.Conta;
import org.exemplo.persistencia.database.model.Cliente;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.mysql.cj.xdevapi.SessionFactory;

public class ClienteDAO implements IEntityDAO<Cliente>{

	private IConnection conn;
	private IEntityDAO<Conta> contaDAO;
	
	public ClienteDAO(IConnection conn) {
		this.conn = conn;
		contaDAO = new ContaDAO(conn);
	}
	
	public void save(Cliente p) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(p);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(Cliente p) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
	}
	
	public void update(Cliente p) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(p);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public Cliente findById(Integer id) {
		Session session = conn.getSessionFactory().openSession();
		Cliente temp = session.find(Cliente.class, id);
		return temp;
	}

	
	public List<Cliente> findAll() {
		Session session = conn.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
        Root<Cliente> root = query.from(Cliente.class);
        query.select(root);
        return session.createQuery(query).getResultList();
	}

	
	public Cliente findByCpf(String cpf) {
		Session session = conn.getSessionFactory().openSession();
		String hql = "FROM Cliente WHERE cpf = :cpf";
		Query<Cliente> query = session.createQuery(hql, Cliente.class);
		query.setParameter("cpf", cpf);
		return query.uniqueResult();
	}
}
