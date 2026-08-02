import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

class StatusHandler implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        if(!"GET".equalsIgnoreCase(exchange.getRequestMethod())){
            exchange.sendResponseHeaders(405,-1);
            return;
        }
        String jsonResponse = "{\"Status\":\"UP\", \"system\":\"Manufacturing-Core\"}";
        byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type","application/json");
        exchange.sendResponseHeaders(200, responseBytes.length);

        try(OutputStream os = exchange.getResponseBody()){
            os.write(responseBytes);
        }

    }
}