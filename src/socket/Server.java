package socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import utils.LoggerUtils;

public class Server {

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(8978);
		LoggerUtils.log("Server is listening...");

		// waiting to accept connection request from client
		Socket socket = serverSocket.accept();
		LoggerUtils.log("Client has arrived. " + socket.getInetAddress() + ":" +socket.getPort());
			
		// for reading input from client
		InputStream in = socket.getInputStream();

		// for sending response to client
		OutputStream out = socket.getOutputStream();

		// reading input sent by client
		byte[] input = new byte[1024];
		in.read(input);
		String msg = new String(input);
		LoggerUtils.log("Message from client : " + msg);

		// sending output back to client
		out.write("hi from server".getBytes());

		socket.close();
		serverSocket.close();
	}

}
