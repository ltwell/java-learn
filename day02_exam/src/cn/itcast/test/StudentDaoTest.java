package cn.itcast.test;

import cn.itcast.dao.IStudentDao;
import cn.itcast.dao.StudentDao;

public class StudentDaoTest {

	public static void main(String[] args) {
		IStudentDao dao = new StudentDao();
//		
//		Student s = new Student();
//		s.setIdcard("555");
//		s.setExamid("666");
//		s.setName("������");
//		s.setLocation("̨��");
//		s.setGrade(100);
//		
//		dao.createStudent(s);
//		Student s = dao.findStudent("666");
//		System.out.println(s);
		boolean b = dao.deleteStudent("������");
		System.out.println(b);
	}

}
