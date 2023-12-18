package group06.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import group06.entity.payment.CreditCard;
import group06.entity.payment.PaymentTransaction;

public class API {

    public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

    // Vi phạm SRP: Phương thức này thực hiện quá nhiều công việc, bao gồm cả gửi yêu cầu, nhận kết quả và logging.
    // Comment: Phương thức get vi phạm nguyên lý Single Responsibility Principle (SRP) vì thực hiện quá nhiều nhiệm vụ.
    // Nên xem xét tách thành các phương thức nhỏ, mỗi phương thức chỉ thực hiện một nhiệm vụ cụ thể.
    public static String get(String url, String token) throws Exception {
        LOGGER.info("Request URL: " + url + "\n");
        URL line_api_url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder respone = new StringBuilder(); // Using StringBuilder for the sake of memory and performance
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        respone.append(inputLine + "\n");
        in.close();
        LOGGER.info("Response Info: " + respone.substring(0, respone.length() - 1).toString());
        return respone.substring(0, respone.length() - 1).toString();
    }

    int var;
    // Vi phạm SRP: Phương thức này thực hiện quá nhiều công việc, bao gồm cả gửi yêu cầu, nhận kết quả và logging.
    // Comment: Phương thức post vi phạm nguyên lý Single Responsibility Principle (SRP) vì thực hiện quá nhiều nhiệm vụ.
    // Nên xem xét tách thành các phương thức nhỏ, mỗi phương thức chỉ thực hiện một nhiệm vụ cụ thể.
    public static String post(String url, String data) throws IOException {
        allowMethods("PATCH");
        URL line_api_url = new URL(url);
        String payload = data;
        LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");
        HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("PATCH");
        conn.setRequestProperty("Content-Type", "application/json");
        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(payload);
        writer.close();
        BufferedReader in;
        String inputLine;
        if (conn.getResponseCode() / 100 == 2) {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();
        LOGGER.info("Response Info: " + response.toString());
        return response.toString();
    }

    // Vi phạm SRP: Phương thức này thực hiện nhiều công việc không liên quan đến API.
    // Comment: Phương thức allowMethods không nên ở trong lớp API vì nó không thực hiện các thao tác liên quan đến API.
    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
            methodsField.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/* static field */, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

}
