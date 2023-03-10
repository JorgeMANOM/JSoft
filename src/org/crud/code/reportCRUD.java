package org.crud.code;

import java.util.List;

import org.crud.model.reportModel;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class reportCRUD {

	public static void main(String[] args) {
		//insertarReport("ACCIDENTE", "QWDQ121121", "ACCIDENTE MARINO");
		//actualizarReport(1, "MAL ENTENDIDO", "13212KKQW", "PELEA MAL INTENCIONADA");
		//eliminarReport(2);
		//mostrarPorID(1);
		selectALLReporte();
	}
	
	public static void insertarReport(String tipo, String folio, String desc) {
		AnnotationConfiguration annotationConfiguration = new AnnotationConfiguration();
		annotationConfiguration.configure("hibernate.cfg.xml");
		SessionFactory factory = annotationConfiguration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			reportModel report = new reportModel();
			report.setTipo(tipo);
			report.setFolio(folio);
			report.setDescripcion(desc);
			session.save(report);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static void actualizarReport(int id,String tipo, String folio, String desc) {
		AnnotationConfiguration annotationConfiguration = new AnnotationConfiguration();
		annotationConfiguration.configure("hibernate.cfg.xml");
		SessionFactory factory = annotationConfiguration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			reportModel report = new reportModel();
			report.setId(id);
			report.setTipo(tipo);
			report.setFolio(folio);
			report.setDescripcion(desc);
			session.update(report);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public static void eliminarReport(int id) {
		AnnotationConfiguration annotationConfiguration = new AnnotationConfiguration();
		annotationConfiguration.configure("hibernate.cfg.xml");
		SessionFactory factory = annotationConfiguration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			reportModel report = (reportModel) session.get(reportModel.class, id);
			session.delete(report);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static List<reportModel> selectALLReporte() {
		AnnotationConfiguration annotationConfiguration = new AnnotationConfiguration();
		annotationConfiguration.configure("hibernate.cfg.xml");
		SessionFactory factory = annotationConfiguration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(reportModel.class);
			@SuppressWarnings("unchecked")
			List<reportModel> lista = (List<reportModel>) criteria.list();
			for(reportModel x : lista) {
				System.out.println(x.getId()+ " || PRECIO:  " + x.getTipo()+ " || NOMBRE: " + x.getFolio());
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return null;
	}
	
	public static void mostrarPorID(int id) {
		AnnotationConfiguration annotationConfiguration = new AnnotationConfiguration();
		annotationConfiguration.configure("hibernate.cfg.xml");
		SessionFactory factory = annotationConfiguration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			reportModel report = (reportModel) session.get(reportModel.class, id);
			System.out.println(report.getId() + " || " + report.getTipo() + " || " + report.getFolio() + " || " + report.getDescripcion());
			session.getTransaction().commit();
		
			
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
}
