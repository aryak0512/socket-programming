package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.LoggerUtils;

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(8978);
		LoggerUtils.log("Server is listening...");
		ProductService ps = new ProductService();
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		while (true) {

			LoggerUtils.log("Waiting for client ...");
			// waiting to accept connection request from client
			Socket socket = serverSocket.accept();
			executorService.execute(new ServerThread(socket));

		}

	}

}
