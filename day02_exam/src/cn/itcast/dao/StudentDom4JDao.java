package cn.itcast.dao;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import cn.itcast.domain.Student;
import cn.itcast.util.Dom4JUtil;

public class StudentDom4JDao implements IStudentDao{

	@Override
	public boolean createStudent(Student s) {
		System.out.println("Dom4j");
		boolean result = false;
		
		try{
			//得到Document对象
			Document document = Dom4JUtil.getDocument();
			//得到根元素
			Element root = document.getRootElement();
			//加数据即可<student>
			Element studentE = root.addElement("student")
				.addAttribute("examid", s.getExamid())
				.addAttribute("idcard", s.getIdcard());
			studentE.addElement("name").setText(s.getName());
			studentE.addElement("location").setText(s.getLocation());
			studentE.addElement("grade").setText(s.getGrade()+"");
			//写回XML文档
			Dom4JUtil.write2xml(document);
			result = true;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		return result;
	}

	@Override
	public boolean deleteStudent(String name) {
		boolean result = false;
		try{
			//得到Document
			Document document = Dom4JUtil.getDocument();
			//选择所有的name元素
			List<Node> nodes = document.selectNodes("//name");
			//遍历：判断元素的内容是否与参数一致
			for(Node n:nodes){
				if(n.getText().equals(name))
			//用他的爷爷删除它的爸爸
					n.getParent().getParent().remove(n.getParent());
			}
			//写回XML文档
			Dom4JUtil.write2xml(document);
			result = true;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public Student findStudent(String examid) {
		Student s = null;
		try{
			Document document = Dom4JUtil.getDocument();
			Node node = document.selectSingleNode("//student[@examid='"+examid+"']");
			if(node!=null){
				Element e = (Element)node;
				s = new Student();
				s.setExamid(examid);
				s.setIdcard(e.valueOf("@idcard"));
				s.setName(e.element("name").getText());
				s.setLocation(e.elementText("location"));
				s.setGrade(Float.parseFloat(e.elementText("grade")));
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return s;
	}
	
}
