package cn.itcast.test;

import cn.itcast.dao.IStudentDao;
import cn.itcast.dao.StudentDom4JDao;
import cn.itcast.domain.Student;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentDom4JDaoTest {
	private static IStudentDao dao;
	@BeforeClass
	public static void init(){
		dao = new StudentDom4JDao();
	}
	@AfterClass
	public static void destory(){
		dao = null;
	}
	@Test
	public void testCreateStudent() {
		Student s = new Student();
		s.setExamid("438");
		s.setIdcard("250");
		s.setName("张白痴");
		s.setLocation("日本");
		s.setGrade(100);
		boolean b = dao.createStudent(s);
        Assert.assertTrue(b);
	}
	@Test
	public void testFindStudent() {
		Student s = dao.findStudent("438");
		Assert.assertNotNull(s);
		s = dao.findStudent("834");
		Assert.assertNull(s);
	}
	@Test
	public void testDeleteStudent() {
		boolean b = dao.deleteStudent("张白痴");
		Assert.assertTrue(b);
		
	}

	

}
