package org.exemplo.persistencia.database.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TelaPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar barraMenu;
	private JMenu menuPaciente;
	private JMenu menuExames;
	private JMenu menuSobre;
	
	private JMenuItem cadastrarPaciente;
	private JMenuItem editarPaciente;
	private JMenuItem localizarPaciente;
	
	private JLabel realTimeClock;
	private JPanel clockContainer;
	
	
	public TelaPrincipal()
	{
		setTitle("Tela Principal - Sistema de Cadastro");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(480, 320);
		setResizable(false);
		setLayout(new BorderLayout());
		carregarBarraMenu();
		realTimeClock = new JLabel();
		clockContainer = new JPanel();
		clockContainer.setLayout(new BorderLayout());
		clockContainer.setSize(480, 50);
		clockContainer.add(realTimeClock, BorderLayout.EAST);
		getContentPane().add(clockContainer, BorderLayout.SOUTH);
		currentTimeClock();
		setVisible(true);
	}
	
	private void carregarBarraMenu() {
		cadastrarPaciente = new JMenuItem("Cadastrar");
		cadastrarPaciente.addActionListener(new TelaCadastroPacienteAction());
		editarPaciente = new JMenuItem("Editar");
		localizarPaciente = new JMenuItem("Localizar");
		
		menuPaciente = new JMenu("Pacientes");
		menuExames = new JMenu("Exames");
		menuSobre = new JMenu("Sobre");
		
		barraMenu = new JMenuBar();
		
		menuPaciente.add(cadastrarPaciente);
		menuPaciente.add(editarPaciente);
		menuPaciente.add(localizarPaciente);
		
		barraMenu.add(menuPaciente);
		
		barraMenu.add(menuExames);
		barraMenu.add(menuSobre);
		
		getContentPane().add(barraMenu, BorderLayout.NORTH);
	}
	
	
	private void currentTimeClock() {
		Thread thread = new Thread(() -> {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (true) {
				String dateString = dateFormat.format(new Date());
				realTimeClock.setText(dateString);
				try {
					Thread.sleep(1000); // Update every second
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	private class TelaCadastroPacienteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new TelaCadastroPaciente();
		}
		
	}
	
}
