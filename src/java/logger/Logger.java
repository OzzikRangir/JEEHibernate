/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import database.Notebook;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import javax.servlet.http.Cookie;

/**
 *
 * @author Windows
 */
public class Logger {

    SimpleDateFormat ft = 
      new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss");
    
    private Vector<Cookie> cookieVector = new Vector<Cookie>();

    public void setCookieVector(Cookie[] cookies) {
        if (cookies != null) {
            cookieVector = new Vector(Arrays.asList(cookies));
        } else {
            cookieVector = new Vector<Cookie>();
        }
    }

    public Vector<Cookie> getCookieVector() {
        return cookieVector;
    }
    
    public Vector<LogNotebook> getOperations(){
        Vector<LogNotebook> operations = new Vector<>();
        for(Cookie cookie : cookieVector){
            operations.add(decodeOperation(cookie.getValue()));
        }
        return operations;
    }

    private LogNotebook decodeOperation(String input) {
        String decoded = "";
        try {
            decoded = URLDecoder.decode(input, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] strings = decoded.split("\\;");

        String operation = strings[0];
        
        Date date = null;
        
        try {
            date = ft.parse(strings[1]);
        } catch (ParseException ex) {
        }

        
        int idZeszytu = 0;
        
        try {
            idZeszytu = Integer.valueOf(strings[2]);
        } catch (NumberFormatException e) {
        }
        
        String nazwa = strings[3];
        Integer liczbaStron = null;
        
        try {
            liczbaStron = Integer.valueOf(strings[4]);
        } catch (NumberFormatException e) {
        }
        
        boolean czyKartkiNumerowane = false;

        try {
            czyKartkiNumerowane = Boolean.valueOf(strings[5]);
        } catch (NumberFormatException e) {
        }
        
        LogNotebook notebook = new LogNotebook(operation, date, idZeszytu, nazwa, liczbaStron, czyKartkiNumerowane);
        return notebook;
    }

    private String encodeOperation(LogNotebook notebook) {
        try {
            return URLEncoder.encode(notebook.getOperation() + ";"
                    + ft.format(notebook.getDate()) + ";"
                    + notebook.getIdZeszytu() + ";"
                    + notebook.getNazwa() + ";"
                    + notebook.getLiczbaStron() + ";"
                    + notebook.getCzyKartkiNumerowane(),"UTF-8");
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void addCookie(LogNotebook notebook) {
        cookieVector.add(0, new Cookie("notebooks", encodeOperation(notebook)));
        if (cookieVector.size() >= 11) {
            cookieVector.remove(cookieVector.size() - 1);
        }

        for (int i = 0; i < cookieVector.size(); i++) {
            String name = Integer.toString(i + 1);

            cookieVector.set(i, new Cookie(name, cookieVector.get(i).getValue()));
            cookieVector.get(i).setMaxAge(60 * 60 * 24);
        }

    }
    public String getHTMLOperations(List<LogNotebook> list) {
        String str;
        str = ("<table><tr>");
        str = str.concat(
                "<td><b>Czas operacji</b></td>"
                + "<td><b>Operacja</b></td>"
                + "<td><b>ID Zeszytu</b></td>"
                + "<td><b>Nazwa</b></td>"
                + "<td><b>Liczba stron</b></td>"
                + "<td><b>Numeracja kartek</b></td>");
        str = str.concat("</tr>");
        for (LogNotebook nb : list) {
            str = str.concat("<tr>");
            str = str.concat("<td>" + ft.format(nb.getDate())+ "</td>");
            str = str.concat("<td>" + nb.getOperation()+ "</td>");
            str = str.concat("<td>" + nb.getIdZeszytu()+ "</td>");
            str = str.concat("<td>" + nb.getNazwa()+ "</td>");
            str = str.concat("<td>" + nb.getLiczbaStron()+ "</td>");
            str = str.concat("<td>" + nb.getYesNo(nb.getCzyKartkiNumerowane())+ "</td>");
            str = str.concat("</tr>");
        }
        str = str.concat("</table>");
        return str;
    }
    
}
