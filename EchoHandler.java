import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

class EchoHandler implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        if(!"POST".equalsIgnoreCase(exchange.getRequestMethod())){
            exchange.sendResponseHeaders(405,-1);
            return;
        }
        InputStream is = exchange.getRequestBody();
        String body = new String(is.readAllBytes(),StandardCharsets.UTF_8);

        String jsonResponse = String.format("{\"receiveBody\": \"%s\"}", body.replace("\"","\\\""));
        byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type","application/json");
        exchange.sendResponseHeaders(200, responseBytes.length);

        try(OutputStream os = exchange.getResponseBody()){
            os.write(responseBytes);
        }
    }
}