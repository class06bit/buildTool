package com.myframework.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("*.do")
public class DispatchServlet extends HttpServlet {
	public static Map<String,String> controlls;
	Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		super.init();
		if(controlls==null){
			controlls=new HashMap<String, String>();
			//File f=new File("./");
			//System.out.println(f.getAbsolutePath());
			//String root=req.getRealPath("./");
			//FileInputStream fis = new FileInputStream(new File(root+"/WEB-INF/classes/bean.properties"));
			InputStream fis = getClass().getClassLoader().getResourceAsStream("bean.properties");
			
			Properties prop= new Properties();
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Set<String> key = prop.stringPropertyNames();
			for(String msg : key){
				log.info(msg);
				log.info(prop.get(msg));
				controlls.put(msg, prop.get(msg).toString());
			}
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug(req.getMethod());
//		doGet(req, resp);
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		log.debug(br.readLine());
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug(req.getMethod());
//		doGet(req, resp);
		 BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
         
	        String rawData;
			String rawData2="";
			while((rawData = br.readLine()) != null){
	            rawData2 += rawData;
	        }
	         
	        log.debug(rawData2);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug(req.getMethod());
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url=req.getRequestURI().substring(req.getContextPath().length());
		log.debug(req.getMethod());
		log.debug(url);
		MyController controller=null;
		if(url.startsWith("/")){
			try {
				log.debug(url.substring(1));
				log.debug(controlls.get(url.substring(1)));
				Class<?> clazz =Class.forName(controlls.get(url.substring(1)));
				controller=(MyController) clazz.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			log.debug(controller);
		}else{
			log.debug("index");
			resp.sendRedirect(url);
			return;
		}
		String view=controller.execute(req, resp);
		String prefix="/WEB-INF/view/";
		String suffix=".jsp";
		if(view.startsWith("redirect:")){
			resp.sendRedirect(url);
		}else{
			log.debug(prefix+view+suffix);
			req.getRequestDispatcher(prefix+view+suffix).forward(req, resp);
		}
		
	}
}









