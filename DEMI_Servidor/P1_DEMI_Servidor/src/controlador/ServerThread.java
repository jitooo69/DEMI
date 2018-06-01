/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * Clase que crea hilos thread para cada cliente.
 * 
 * @author Sergio Márquez Rocha
 * @since 08/05/2018
 * @version 1.0
 */
public class ServerThread extends Thread {
	
	private Socket socket;
	
	/**
	 * Método constructor
	 * @param socket 
	 */
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			String mensaje;
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader bufferedReader = new BufferedReader
								(new InputStreamReader(socket.getInputStream()));
			System.out.println("usuario '"+ bufferedReader.readLine()+"' está ahora"
							+ "conectado al servidor...");
			while ((mensaje = bufferedReader.readLine()) != null) {
				System.out.println("En servidor: Mensaje del cliente: " + mensaje);
				printWriter.println("En cliente: mensaje del servidor => " +mensaje);
			}
			printWriter.flush();
			printWriter.close();
			socket.close();
		} catch (SocketException ex1) {
			ex1.printStackTrace();
		}	catch (IOException ex2) {
			ex2.printStackTrace();
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException ex3) {
				System.out.println("mensaje de finally");
				ex3.printStackTrace();
			}
		}
	}
}
