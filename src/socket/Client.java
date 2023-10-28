package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import utils.LoggerUtils;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("127.0.0.1", 8978);

		// for reading input from client
		InputStream in = socket.getInputStream();

		// for sending response to client
		OutputStream out = socket.getOutputStream();

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// sending output back to client
		out.write("1".getBytes());
		byte[] input = new byte[1024];
		in.read(input);
		String msg = new String(input);
		LoggerUtils.log("Message from server : " + msg.trim());

		socket.close();
	}

}
