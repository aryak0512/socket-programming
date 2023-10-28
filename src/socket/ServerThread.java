package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import utils.ByteObjectConverter;
import utils.LoggerUtils;

public class ServerThread implements Runnable {

	Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {

			LoggerUtils.log("Client has arrived. " + socket.getInetAddress() + ":" + socket.getPort());
			// for reading input from client
			InputStream in = socket.getInputStream();
			// for sending response to client
			OutputStream out = socket.getOutputStream();

			// reading input sent by client
			byte[] input = new byte[1024];
			in.read(input);

			String id = new String(input);

			int productId = Integer.parseInt(id.trim());

			Object response = ProductService.getProduct((long) productId);

			// sending output back to client
			out.write(ByteObjectConverter.objectToByte(response));
			socket.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
