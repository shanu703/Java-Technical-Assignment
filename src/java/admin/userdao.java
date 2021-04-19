/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.mysql.jdbc.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 *
 * @author ankush chandel
 */
public class userdao {
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(d);
//        int date1=Integer.parseInt(date);
    public static Connection getConnection(){
        Connection con=null;
        try{  
        Class.forName("com.mysql.jdbc.Driver");  
        con=(Connection) DriverManager.getConnection("jdbc:mysql:///library","root","123");  
    }catch(Exception e){System.out.println(e);}  
        return con;
    }
    public static int save(user u){
        int status=0;  
    try{  
        java.sql.Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("insert into addlibrary(name,email,password,mobile) values(?,?,?,?)");  
        ps.setString(1,u.getName());  
        ps.setString(2,u.getEmail());  
        ps.setString(3,u.getPassword());
        ps.setString(4,u.getMobile());
        status=ps.executeUpdate();  
    }catch(Exception e){System.out.println(e);}  
    return status;  
    }
    public static List<user> getallrecords(){
        List<user> list=new ArrayList<user>();
        try{  
        java.sql.Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from addlibrary");  
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){  
            user u=new user();  
            u.setId(rs.getInt("id"));  
            u.setName(rs.getString("name"));   
            u.setEmail(rs.getString("email")); 
            u.setPassword(rs.getString("password")); 
            u.setMobile(rs.getString("mobile")); 
            list.add(u);  
        }  
    }catch(Exception e){System.out.println(e);}  
    return list;  
    }
    public static user getrecordsbyid(int id){
        user u=null;  
    try{  
        java.sql.Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from addlibrary where id=?");  
        ps.setInt(1,id);  
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){  
            u=new user();  
            u.setId(rs.getInt("id"));  
            u.setName(rs.getString("name"));  
            u.setEmail(rs.getString("email"));  
            u.setPassword(rs.getString("password"));  
            u.setMobile(rs.getString("mobile"));
        }  
    }catch(Exception e){System.out.println(e);}  
    return u;
    }
        public static user getrecordsbyidbook(String bookid){
        user u=null;  
    try{  
        java.sql.Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from addbook where bookid=?");  
        ps.setString(1,bookid); 
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){  
            u=new user();
            u.setBookid(rs.getString("bookid"));  
            u.setBookname(rs.getString("bookname"));  
            u.setAuthor(rs.getString("author"));  
            u.setPublisher(rs.getString("publisher"));  
            u.setQuantity(rs.getString("quantity"));
        }  
    }catch(Exception e){System.out.println(e);}  
    return u;
    }
    public static int update(user u){  
    int status=0;  
    try{  
        Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("update addlibrary set name='"+u.getName()+"',email='"+u.getEmail()+"',password='"+u.getPassword()+"',mobile='"+u.getMobile()+"' where id='"+u.getId()+"'");        
        status=ps.executeUpdate();  
    }catch(Exception e){System.out.println(e);}  
    return status;  
}  
    public static int updatebook(user u){  
    int status=0;  
    try{  
        Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("update addbook set bookname='"+u.getBookname()+"',author='"+u.getAuthor()+"',publisher='"+u.getPublisher()+"',quantity='"+u.getQuantity()+"' where bookid='"+u.getBookid()+"'");        
        status=ps.executeUpdate();  
    }catch(Exception e){System.out.println(e);}  
    return status;  
}
    public static int delete(int id){  
    int status=0;  
    try{  
        Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("delete from addlibrary where id='"+id+"'");    
        status=ps.executeUpdate();  
    }catch(Exception e){System.out.println(e);}  
  
    return status;  
}
    public static int deletebook(String bookid){  
    int status=0;  
    try{  
        Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("delete from addbook where bookid='"+bookid+"'");    
        status=ps.executeUpdate();  
    }catch(Exception e){System.out.println(e);}  
  
    return status;  
}
    public static int librarlogin(user u){
        int status=0;
        String email1 = null,pass1=null;
        try{  
        Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from addlibrary where email='"+u.getEmail()+"'");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            email1=rs.getString("email");
            pass1=rs.getString("password");
        }
        if(u.getEmail().equals(email1) && u.getPassword().equals(pass1)){
            status=1;
        }else{
            status=0;
        }
    }catch(Exception e){System.out.println(e);}   
        return status;
    }
    public static int addbook(user u){
        int status=0;  
    try{  
        java.sql.Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("insert into addbook(bookid,bookname,author,publisher,quantity,issued) values(?,?,?,?,?,'0')");  
        ps.setString(1,u.getBookid());  
        ps.setString(2,u.getBookname());  
        ps.setString(3,u.getAuthor());
        ps.setString(4,u.getPublisher());
        ps.setString(5,u.getQuantity());
        status=ps.executeUpdate();  
    }catch(Exception e){System.out.println(e);}  
    return status;  
    }
        public static List<user> getallbooks(String bookname){
        List<user> list=new ArrayList<user>();
        try{  
        java.sql.Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from addbook where bookname='"+bookname+"'");  
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){  
            user u=new user();
            u.setBookid(rs.getString("bookid"));
            u.setBookname(rs.getString("bookname"));
            u.setAuthor(rs.getString("author"));
            u.setPublisher(rs.getString("publisher"));
            u.setQuantity(rs.getString("quantity"));
            u.setIssued(rs.getString("issued"));
            list.add(u);  
        }  
    }catch(Exception e){System.out.println(e);}  
    return list;  
    }
        public static List<user> getallissuebooks(String studentid){
        List<user> list=new ArrayList<user>();
        try{  
        java.sql.Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from issuebook where studentid='"+studentid+"'");  
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){  
            user u=new user();
            u.setBookid(rs.getString("bookid"));
            u.setStudentid(rs.getString("studentid"));
            u.setStudentname(rs.getString("studentname"));
            u.setStudentmobile(rs.getString("mobile"));
            u.setIssuedate(rs.getString("issuedate"));
            u.setReturnstatus(rs.getString("returnstatus"));
            u.setDuedate(rs.getString("duedate"));
            list.add(u);  
        }  
    }catch(Exception e){System.out.println(e);}  
    return list;  
    }
        public static List<user> getallbookname(String bookname){
        List<user> list=new ArrayList<user>();
        try{  
        Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from addbook where bookname='"+bookname+"'");  
        ResultSet rs=ps.executeQuery(); 
        while(rs.next()){  
            user u=new user();
            u.setBookid(rs.getString("bookid"));
            u.setBookname(rs.getString("bookname"));
            u.setAuthor(rs.getString("author"));
            u.setPublisher(rs.getString("publisher"));
            u.setQuantity(rs.getString("quantity"));
            list.add(u);  
        }  
    }catch(Exception e){System.out.println(e);}  
    return list;  
    }
    public static int issuebook(user u){
        int status=0;  
    try{  
        java.sql.Connection con=getConnection();  
        PreparedStatement ps=con.prepareStatement("insert into issuebook(bookid,studentid,studentname,mobile,issuedate,returnstatus,duedate) values(?,?,?,?,(select curdate()),'No',(select date_add(curdate(), interval 5 day)))");  
        PreparedStatement ps1=con.prepareStatement("update addbook set quantity=quantity-1,issued=issued+1 where bookid='"+u.getBookid()+"'");
        ps.setString(1,u.getBookid());  
        ps.setString(2,u.getStudentid());  
        ps.setString(3,u.getStudentname());
        ps.setString(4,u.getStudentmobile());
        status=ps.executeUpdate();  
        ps1.executeUpdate();
    }catch(Exception e){System.out.println(e);}  
    return status;  
    }
    public static int returnbook(user u){
        String due=null;
        int fine1=0;
        long fine=0;
        userdao u1=new userdao();
        String date1=u1.date;
    try{ 
        java.sql.Connection con=getConnection();
         PreparedStatement ps=con.prepareStatement("select duedate from issuebook where bookid='"+u.getBookid()+"' and studentid='"+u.getStudentid()+"'");
         ResultSet rs=ps.executeQuery();
         while(rs.next())
         {
            due=rs.getString("duedate"); 
         }
        if(due.compareTo(date1)<0){
            LocalDate d1= LocalDate.parse(due, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate d2=LocalDate.parse(date1, DateTimeFormatter.ISO_LOCAL_DATE);
            Duration diff=Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
            fine=diff.toDays();
        PreparedStatement ps1=con.prepareStatement("update addbook set quantity=quantity+1,issued=issued-1 where bookid='"+u.getBookid()+"'");
        PreparedStatement ps2=con.prepareStatement("delete from issuebook where bookid='"+u.getBookid()+"' and studentid='"+u.getStudentid()+"'");
        ps1.executeUpdate();
        ps2.executeUpdate();
            u.setFine(fine);
        }
        else{
            fine=0;
        PreparedStatement ps1=con.prepareStatement("update addbook set quantity=quantity+1,issued=issued-1 where bookid='"+u.getBookid()+"'");
        PreparedStatement ps2=con.prepareStatement("delete from issuebook where bookid='"+u.getBookid()+"' and studentid='"+u.getStudentid()+"'");
        ps1.executeUpdate();
        ps2.executeUpdate();
        }
        fine1=(int) fine;
    }catch(Exception e){System.out.println(e);}  
    return fine1;  
    }
    public static int renew(String bookid,String studentid,user u){
        String due=null;
        int fine1=0;
        long fine=0;
        userdao u1=new userdao();
        String date1=u1.date;
    try{  
        java.sql.Connection con=getConnection();
        PreparedStatement ps1=con.prepareStatement("select duedate from issuebook where bookid='"+bookid+"' and studentid='"+studentid+"'");
         ResultSet rs=ps1.executeQuery();
         while(rs.next())
         {
            due=rs.getString("duedate"); 
         }
        if(due.compareTo(date1)<0){
            LocalDate d1= LocalDate.parse(due, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate d2=LocalDate.parse(date1, DateTimeFormatter.ISO_LOCAL_DATE);
            Duration diff=Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
            fine=diff.toDays();
            PreparedStatement ps2=con.prepareStatement("update issuebook set duedate=(select date_add(curdate(),interval 5 day)) where bookid='"+bookid+"' and studentid='"+studentid+"'");
            ps2.executeUpdate();
            u.setFine(fine);
        }
        else{
            fine=0;
            PreparedStatement ps2=con.prepareStatement("update issuebook set duedate=(select date_add(curdate(),interval 5 day)) where bookid='"+bookid+"' and studentid='"+studentid+"'");
            ps2.executeUpdate();
        }
        fine1=(int) fine;
    }
    catch(Exception e){System.out.println(e);}  
    return fine1;
}    
}