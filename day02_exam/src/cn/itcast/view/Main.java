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
			System.out.println("a、添加学生\tb、删除学生\tc、查询成绩");
			System.out.println("请输入操作类型：");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String operation = br.readLine();//读取用户输入的操作类型
			if("a".equals(operation)){
				//添加操作
				Student s = addOperation(br);
				boolean b = dao.createStudent(s);
				if(b){
					System.out.println("-----添加成功-------");
				}else
					System.out.println("对不起！数据有误");
			}else if("b".equals(operation)){
				//删除操作
				System.out.println("请输入要删除的学生姓名：");
				String name = br.readLine();
				boolean b = dao.deleteStudent(name);
				if(b){
					System.out.println("-----删除成功-------");
				}else
					System.out.println("对不起！删除失败或者学生根本不存在");
			}else if("c".equals(operation)){
				//查询操作
				System.out.println("请输入学生的准考证号：");
				 String examid = br.readLine();
				 Student s = dao.findStudent(examid);
				 if(s==null)
					 System.out.println("对不起！您查询的学生不存在");
				 else
					 System.out.println(s);
			}else{
				System.out.println("请输入正确的操作类型");
			}
		}catch(Exception e){
			System.out.println("对不起！服务器忙");
		}
	}

	private static Student addOperation(BufferedReader br) throws IOException {
		System.out.println("请输入学生姓名：");
		String name = br.readLine();
		System.out.println("请输入学生的准考证号：");
		String examid = br.readLine();
		System.out.println("请输入学生的身份证号：");
		String idcard = br.readLine();
		System.out.println("请输入学生所在地：");
		String location = br.readLine();
		System.out.println("请输入学生的成绩：");
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
