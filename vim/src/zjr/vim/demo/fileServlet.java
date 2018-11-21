package zjr.vim.demo;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "fileServlet", urlPatterns = {"/fileDemo"})
public class fileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           String path = "D://VIM//";
           request.setCharacterEncoding("UTF-8");
           RequestContext requestContext = new ServletRequestContext(request);
           if(FileUpload.isMultipartContent(requestContext)){
               DiskFileItemFactory factory = new DiskFileItemFactory();
               ServletFileUpload fileUpload = new ServletFileUpload(factory);
               fileUpload.setHeaderEncoding("UTF-8");
               fileUpload.setFileSizeMax(41943040);
               List<FileItem> items = new ArrayList<FileItem>();
               items = fileUpload.parseRequest(request);
               Iterator<FileItem> iterator = items.iterator();
               File file = null;
               FileItem item = null;
               while (iterator.hasNext()){
                   FileItem fileItem = iterator.next();
                   if(fileItem.isFormField()){
                       if(fileItem.getFieldName().equals("Server")){
                           String server = new String(fileItem.getString().getBytes("ISO-8859-1"),"GBK");
                           path = path + server + "//";
                       }
                   }else{
                       file = new File(fileItem.getName());
                       item = fileItem;
                   }
               }
               File newFile = new File(path + file.getName());
               item.write(newFile);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
