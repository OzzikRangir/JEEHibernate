/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Windows
 */
public class NotebookQuery {

    private Session session = null;
    private Query q = null;
    private List<Notebook> notebookList = null;

    public NotebookQuery() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    
    
    public void insert(Notebook notebook){
        try {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(notebook);
        
        tx.commit();
        session.close();
                } catch (HibernateException e) {

        }
    }
    
    public void delete(Notebook notebook){
        try {
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(notebook);
        
        tx.commit();
        session.close();
                } catch (HibernateException e) {
        }
    }
    
    public Notebook getNotebook(int idZeszytu){
        Notebook notebook = null;
        try {
        org.hibernate.Transaction tx = session.beginTransaction();
        notebook = (Notebook) session.get(Notebook.class,idZeszytu);
        tx.commit();
        session.close();
                } catch (HibernateException e) {

        }   
        return notebook;
    }

    public List<Notebook> getNotebookList() {
        try {
            org.hibernate.Transaction tx = session.beginTransaction();

            q = session.createQuery("from Notebook order by id_zeszytu");
            notebookList = (List<Notebook>) q.list();
            tx.commit();
session.close();
        } catch (HibernateException e) {

        }

        return notebookList;
    }

    public int getLastId(){
        
        List<Notebook> list = getNotebookList();
        int last = list.size()+1;
        list.sort((o1, o2) -> Integer.valueOf(o1.getIdZeszytu()).compareTo(Integer.valueOf(o2.getIdZeszytu())));
        for(int i=0;i<list.size()-1;i++){
            if(list.get(i+1).getIdZeszytu()-list.get(i).getIdZeszytu()!=1){
                last=list.get(i).getIdZeszytu()+1;
                break;
            }
        }
        System.out.println(last);
        return last;
    }
   
    
    public static String getHTMLList(List<Notebook> list, boolean deleteButton) {
        String str;
        str = ("<table><tr>");
        str = str.concat(
                "<td><b>ID Zeszytu</b></td>"
                + "<td><b>Nazwa</b></td>"
                + "<td><b>Liczba stron</b></td>"
                + "<td><b>Numeracja kartek</b></td>");
        str = str.concat("</tr>");
        for (Notebook nb : list) {
            str = str.concat("<tr>");
            str = str.concat("<td>" + nb.getIdZeszytu()+ "</td>");
            str = str.concat("<td>" + nb.getNazwa()+ "</td>");
            str = str.concat("<td>" + nb.getLiczbaStron()+ "</td>");
            str = str.concat("<td>" + nb.getYesNo(nb.getCzyKartkiNumerowane())+ "</td>");
            if(deleteButton){
            str = str.concat("<td><form action=\"./usun\">\n"
                    + "<input type=\"hidden\" name=\"id\" value=\""+ nb.getIdZeszytu()+"\" />"
                    + "<input type=\"submit\" value=Usuń></form></td>");
            }
            str = str.concat("</tr>");
        }
        str = str.concat("</table>");
        return str;
    }

}
