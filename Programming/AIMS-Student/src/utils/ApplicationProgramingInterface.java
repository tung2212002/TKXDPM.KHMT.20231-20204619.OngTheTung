package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This {@code ApplicationProgramingInterface} class contain method GET and POST in our AIMS
 * Software.
 * 
 * @author HaPham
 * @version 1.0
 *
 */
public class ApplicationProgramingInterface {

  public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

  /**
   * GET is used to request data from a specified resource.
   * @param url url link
   * @param token token
   * @return String
   * @throws Exception when exception happen
   */
  public static String get(final String url, final String token) throws Exception {
    LOGGER.info("Request URL: " + url + "\n");

    HttpURLConnection conn = setupConnection(url);

    conn.setRequestProperty("Authorization", "Bearer " + token);
    conn.setRequestMethod("GET");
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String inputLine;

    // using StringBuilder for the sake of memory and performance
    StringBuilder respone = new StringBuilder(); 
    while ((inputLine = in.readLine()) != null) {
      System.out.println(inputLine);
    }
    respone.append(inputLine + "\n");
    in.close();
    LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
    return respone.substring(0, respone.length() - 1).toString();
  }

  /**
   * Create a new Http URL connection.
   * @param url url
   * @return HttpURLConnection
   * @throws MalformedURLException when MalformedURLException
   * @throws IOException when IOException
   */
  private static HttpURLConnection setupConnection(final String url)
      throws MalformedURLException, IOException {

    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
    conn.setDoInput(true);
    conn.setDoOutput(true);
    conn.setRequestProperty("Content-Type", "application/json");
    return conn;

  }

  int var;

  /** POST is used to send data to a server to create/update a resource.
   * @param url url
   * @param data data 
   * @return String
   * @throws IOException when exception happen
   */
  public static String post(final String url, final String data) throws IOException {
    allowMethods("PATCH");

    String payload = data;
    LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");

    HttpURLConnection conn = setupConnection(url);
    conn.setRequestMethod("PATCH");

    // conn.setRequestProperty("Authorization", "Bearer " + token);
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
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    LOGGER.info("Respone Info: " + response.toString());
    return response.toString();
  }

  /**
   * This is {@code allowMethods} function.
   * 
   * @param methods method name
   */
  private static void allowMethods(final String... methods) {
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
