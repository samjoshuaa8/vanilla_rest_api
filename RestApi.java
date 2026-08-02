import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class RestApi{
    public static void main(String[] args) throws IOException{
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port),0); //default os number of waiting connections

        server.createContext("/api/status",new StatusHandler());
        server.createContext("/api/echo",new EchoHandler());

        server.setExecutor(null); //requests one at a time sequentially
        System.out.println("Server running on port "+port);
        server.start();
    }
}


