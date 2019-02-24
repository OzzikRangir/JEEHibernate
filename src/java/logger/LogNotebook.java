/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import database.Notebook;
import java.util.Date;

/**
 *
 * @author Windows
 */
public class LogNotebook extends Notebook {

    private String operation;
    private Date date;

    public LogNotebook(String operation, Date date, int idZeszytu, String nazwa, Integer liczbaStron, Boolean czyKartkiNumerowane) {
        super(idZeszytu, nazwa, liczbaStron, czyKartkiNumerowane);
        this.operation = operation;
        this.date = date;
    }

    public LogNotebook(String operation, Date date, Notebook notebook) {
        super(notebook.getIdZeszytu(), notebook.getNazwa(), notebook.getLiczbaStron(), notebook.getCzyKartkiNumerowane());
        this.operation = operation;
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public Date getDate() {
        return date;
    }
}
