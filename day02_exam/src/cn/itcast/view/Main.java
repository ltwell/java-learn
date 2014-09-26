package cn.itcast.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.itcast.dao.IStudentDao;
import cn.itcast.dao.StudentDao;
import cn.itcast.dao.StudentDom4JDao;
import cn.itcast.domain.Student;

public class Main {

	public static void main(String[] args) {
		
		try{
			IStudentDao dao = new StudentDom4JDao();
			System.out.println("a�����ѧ��\tb��ɾ��ѧ��\tc����ѯ�ɼ�");
			System.out.println("������������ͣ�");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String operation = br.readLine();//��ȡ�û�����Ĳ�������
			if("a".equals(operation)){
				//��Ӳ���
				Student s = addOperation(br);
				boolean b = dao.createStudent(s);
				if(b){
					System.out.println("-----��ӳɹ�-------");
				}else
					System.out.println("�Բ�����������");
			}else if("b".equals(operation)){
				//ɾ������
				System.out.println("������Ҫɾ����ѧ��������");
				String name = br.readLine();
				boolean b = dao.deleteStudent(name);
				if(b){
					System.out.println("-----ɾ���ɹ�-------");
				}else
					System.out.println("�Բ���ɾ��ʧ�ܻ���ѧ������������");
			}else if("c".equals(operation)){
				//��ѯ����
				System.out.println("������ѧ����׼��֤�ţ�");
				 String examid = br.readLine();
				 Student s = dao.findStudent(examid);
				 if(s==null)
					 System.out.println("�Բ�������ѯ��ѧ��������");
				 else
					 System.out.println(s);
			}else{
				System.out.println("��������ȷ�Ĳ�������");
			}
		}catch(Exception e){
			System.out.println("�Բ��𣡷�����æ");
		}
	}

	private static Student addOperation(BufferedReader br) throws IOException {
		System.out.println("������ѧ��������");
		String name = br.readLine();
		System.out.println("������ѧ����׼��֤�ţ�");
		String examid = br.readLine();
		System.out.println("������ѧ�������֤�ţ�");
		String idcard = br.readLine();
		System.out.println("������ѧ�����ڵأ�");
		String location = br.readLine();
		System.out.println("������ѧ���ĳɼ���");
		String grade = br.readLine();
		
		Student s = new Student();
		s.setExamid(examid);
		s.setIdcard(idcard);
		s.setName(name);
		s.setLocation(location);
		s.setGrade(Float.parseFloat(grade));
		return s;
	}

}
