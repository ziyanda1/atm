package za.co.discovery.atm.util;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

public class ATMUtil {
    private ATMUtil() {
    }

    public static HttpHeaders getSuccessHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-atm-response-code", "0000");
        headers.set("x-atm-response-type", "SUCCESS");
        return headers;
    }

    public static HttpHeaders getSuccessHeaders(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-atm-response-code", "0000");
        headers.set("x-atm-response-message", message);
        headers.set("x-atm-response-type", "SUCCESS");
        return headers;
    }

    public static void setSuccessHeaders(HttpServletResponse response) {
        response.setHeader("x-atm-response-code", "0000");
        response.setHeader("x-atm-response-message", "");
        response.setHeader("x-atm-response-type", "SUCCESS");
    }

    public static void setSuccessHeaders(HttpServletResponse response, String message) {
        response.setHeader("x-atm-response-code", "0000");
        response.setHeader("x-atm-response-message", message);
        response.setHeader("x-atm-response-type",  "SUCCESS");
    }

    public static void setErrorHeaders(HttpServletResponse response) {
        response.setHeader("x-atm-response-code", "1337");
        response.setHeader("x-atm-response-message", "");
        response.setHeader("x-atm-response-type",  "ERROR");
    }

    public static void setErrorHeaders(HttpServletResponse response, String message) {
        response.setHeader("x-atm-response-code", "1337");
        response.setHeader("x-atm-response-message", message);
        response.setHeader("x-atm-response-type",  "ERROR");
    }
}
