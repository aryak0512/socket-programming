package socket;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.LoggerUtils;

public class Server {

	public static void main(String[] args) throws IOException {

		// server will listen to client requests on this port
		ServerSocket serverSocket = new ServerSocket(7200);
		
		printBanner();
		
		LoggerUtils.log("Server is listening...");
		
		// populating product hashmap
		ProductService ps = new ProductService();
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		while (true) {

			LoggerUtils.log("Waiting for client ...");
			
			// waiting to accept connection request from client
			Socket socket = serverSocket.accept();
			
			// delegate request to separate thread from thread pool
			executorService.execute(new ServerThread(socket));

		}

	}

	private static void printBanner() throws IOException {

		FileReader fr = new FileReader("banner.txt");
		int i;
		while ((i = fr.read()) != -1)
			System.out.print((char) i);
		fr.close();
		System.out.println();

	}

}
