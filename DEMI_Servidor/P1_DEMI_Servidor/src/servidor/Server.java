/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import controlador.ServerThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase ejecutable encargada de iniciar el servidor.
 * 
 * @author Sergio Márquez Rocha
 * @since 08/05/2018
 * @version 1.0
 */
public class Server {
	
	public static final int PUERTO = 4444;
	
	/**
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new Server().runServer();
	}
	
	/**
	 * Método que inicia el servidor y acepta peticiones de conexión.
	 * @throws IOException 
	 */
	public void runServer() throws  IOException {
		ServerSocket serverSocket = new ServerSocket(PUERTO);
		System.out.println("Servidor activo y listo para aceptar conexiones...");
		while (true) {
			System.out.println("antes de serversocket.accept");
			Socket socket = serverSocket.accept();
			System.out.println("despues de serversocket.accept");
			System.out.println("antes de socket.start");
			new ServerThread(socket).start();
			System.out.println("despues de socket.start");
		}
	}
}
