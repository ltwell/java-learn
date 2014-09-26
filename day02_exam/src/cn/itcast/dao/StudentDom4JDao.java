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
			//�õ�Document����
			Document document = Dom4JUtil.getDocument();
			//�õ���Ԫ��
			Element root = document.getRootElement();
			//�����ݼ���<student>
			Element studentE = root.addElement("student")
				.addAttribute("examid", s.getExamid())
				.addAttribute("idcard", s.getIdcard());
			studentE.addElement("name").setText(s.getName());
			studentE.addElement("location").setText(s.getLocation());
			studentE.addElement("grade").setText(s.getGrade()+"");
			//д��XML�ĵ�
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
			//�õ�Document
			Document document = Dom4JUtil.getDocument();
			//ѡ�����е�nameԪ��
			List<Node> nodes = document.selectNodes("//name");
			//�������ж�Ԫ�ص������Ƿ������һ��
			for(Node n:nodes){
				if(n.getText().equals(name))
			//������үүɾ�����İְ�
					n.getParent().getParent().remove(n.getParent());
			}
			//д��XML�ĵ�
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
