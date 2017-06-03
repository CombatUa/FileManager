package ua.alex.web.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Starter {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(3000);

        // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // BufferedOutputStream bufferedOutputStream = new BufferedOutputStream((socket.getOutputStream()));
        // System.out.println(System.getenv());

        while (true) {
            Socket socket = serverSocket.accept();
            System.err.println("Client accepted");
            new Thread(new RequestProcessor(socket)).start();
            // System.out.println(bufferedReader.readLine());
        }

    }


    private static class RequestProcessor implements Runnable {

        private static final String INDEX_LOCATION = "./target/classes";
        private Socket s;
        private InputStream is;
        private OutputStream os;
        private String index = "index.html";
        private String fileToResponse;

        private RequestProcessor(Socket s) throws IOException {
            this.s = s;
            is = s.getInputStream();
            os = s.getOutputStream();
        }

        private static void copyStreams(InputStream is, OutputStream os) throws IOException {
            byte[] buffer = new byte[1024];
            int numOfBytes = 0;
            while ((numOfBytes = is.read(buffer)) > -1) {
                os.write(buffer, 0, numOfBytes);
            }
            os.flush();
        }

        @Override
        public void run() {
            try {
                readRequest();
                writeResponse(fileToResponse);
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void writeResponse(String fileName) throws IOException {
            String response = "HTTP/1.1 200 OK\r\n" +
                    getFileType(fileName) + "\r\n";
            String result = response;
            os.write(result.getBytes());
            os.flush();
            try (
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(INDEX_LOCATION + fileName))) {
                copyStreams(bufferedInputStream, os);
            }
            os.flush();

        }

        private String getFileType(String fileName) {
            if (fileName.endsWith(".html")) {
                return "Content-Type: text/html\r\n";

            } else if (fileName.endsWith(".css")) {
                return "Content-type: text/css\r\n";
            } else {
                return "Content-Type: application/octet-stream\r\n";
            }
        }

        private void readRequest() throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String s = bufferedReader.readLine();
                if (s == null || s.trim().length() == 0) {
                    break;
                } else {
                    if (s.startsWith("GET")) {
                        fileToResponse = s.split("\\s+")[1];
                        System.err.println(fileToResponse);
                    }
                    System.err.println(s);

                }
            }
        }

    }
}
