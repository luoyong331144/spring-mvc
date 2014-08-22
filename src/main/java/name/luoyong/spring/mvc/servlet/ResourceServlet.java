package name.luoyong.spring.mvc.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		java.util.Date date = new java.util.Date();  
		response.setDateHeader("Last-Modified",date.getTime());
		
		response.setHeader("Expires", "100000");
		response.setHeader("Cache-Control", "public");  
		response.addHeader("Pragma", "Pragma");
		
		Enumeration<String> es = request.getHeaderNames();
		 
		 while(es.hasMoreElements()) {
			 String headerName = es.nextElement();
			 String headerValue = request.getHeader(headerName);
			 System.out.println(headerName+" : "+headerValue);
		 }
		
		response.setStatus(304);
		//jpg(request, response);
	}
	
	private void jpg(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		 response.setContentType("image/jpeg");// 设定输出的类型  
         // 得到图片的真实路径  
		 
		 String uri = request.getRequestURI();
		 File file = new File("src/main/webapp/"+uri);
		 String imgPath = "src/main/webapp/"+uri;
		 
		 BufferedImage image = ImageIO.read(new FileInputStream(imgPath)); 

		 ImageIO.write(image, "jpg", response.getOutputStream());
		 
	}

}
