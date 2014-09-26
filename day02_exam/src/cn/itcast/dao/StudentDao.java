package cn.itcast.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.itcast.domain.Student;
import cn.itcast.util.DocumentUtil;
//�쳣���׵Ļ����ϲ��ܽ������
//��һ����������ˣ��Լ����봦��

public class StudentDao implements IStudentDao {
	/**
	 * ���ѧ����Ϣ��XML��
	 * @param s
	 * @return
	 */
	public boolean createStudent(Student s){
		System.out.println("JAXP");
		//Ŀ�꣺�ڸ�Ԫ��exam�����student��Ԫ��
		boolean result = false;
		try {
			Document document = DocumentUtil.getDocument();
			//����name��location��gradeԪ�ز���������������
			Element nameE = document.createElement("name");
			nameE.setTextContent(s.getName());
			Element locationE = document.createElement("location");
			locationE.setTextContent(s.getLocation());
			Element gradeE = document.createElement("grade");
			gradeE.setTextContent(s.getGrade()+"");
			//����studentԪ�أ�������������
			Element studentE = document.createElement("student");
			studentE.setAttribute("idcard", s.getIdcard());
			studentE.setAttribute("examid", s.getExamid());//CTRL+ALT+ARROW
			
			studentE.appendChild(nameE);
			studentE.appendChild(locationE);
			studentE.appendChild(gradeE);
			//�õ�examԪ�أ���student�ҽ���ȥ
			Node node = document.getElementsByTagName("exam").item(0);
			node.appendChild(studentE);
			//д��XML�ļ���
			DocumentUtil.write2xml(document);
			result = true;
		} catch (Exception e) {
			throw new RuntimeException(e);//�쳣ת�塣�쳣��
		}
		return result;
	}
	/**
	 * ����׼��֤�Ų�ѯѧ����Ϣ
	 * @param examid
	 * @return ���ѧ�������ڣ�����null
	 */
	public Student findStudent(String examid){
		Student s = null;
		
		try{
			//�õ�Document����
			Document document = DocumentUtil.getDocument();
			//�õ����е�studentԪ��
			NodeList nl = document.getElementsByTagName("student");
			//����studentԪ�أ��ж�����examid���Ե�ȡֵ�Ƿ������ƥ��
			for(int i=0;i<nl.getLength();i++){
				Node node = nl.item(i);
//				if(node.getNodeType()==Node.ELEMENT_NODE){
//					Element e = (Element)node;
				if(node instanceof Element){
					Element e = (Element)node;
					if(e.getAttribute("examid").equals(examid)){
					//���ƥ�䣺˵���ҵ���ѧ��������ѧ������
						s = new Student();
					//����ѧ������ĸ�������ȡֵ
						s.setExamid(examid);
						s.setIdcard(e.getAttribute("idcard"));
						s.setName(e.getElementsByTagName("name").item(0).getTextContent());
						s.setLocation(e.getElementsByTagName("location").item(0).getTextContent());
						s.setGrade(Float.parseFloat(e.getElementsByTagName("grade").item(0).getTextContent()));
					}
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		return s;
	}
	/**
	 * ����ѧ������ɾ��ѧ��
	 * @param name
	 * @return ����˲�����Ҳ����false
	 */
	public boolean deleteStudent(String name){
		boolean result = false;
		try{
			//�õ�Document����
			Document document = DocumentUtil.getDocument();
			//�õ����е�nameԪ��
			NodeList nl = document.getElementsByTagName("name");
			//����nameԪ�أ��ж������������Ƿ������һ��
			for(int i=0;i<nl.getLength();i++){
				//���һ�£��ҵ����İְֵİְ֣�ɾ�����İְ�
				Node n = nl.item(i);
				if(n.getTextContent().equals(name)){
					n.getParentNode().getParentNode().removeChild(n.getParentNode());
					//д��XML�ĵ�
					DocumentUtil.write2xml(document);
					result = true;
					break;
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
	}
}
