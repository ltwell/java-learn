package cn.itcast.dao;

import cn.itcast.domain.Student;

public interface IStudentDao {

	/**
	 * ���ѧ����Ϣ��XML��
	 * @param s
	 * @return
	 */
	boolean createStudent(Student s);

	/**
	 * ����׼��֤�Ų�ѯѧ����Ϣ
	 * @param examid
	 * @return ���ѧ�������ڣ�����null
	 */
	Student findStudent(String examid);

	/**
	 * ����ѧ������ɾ��ѧ��
	 * @param name
	 * @return ����˲�����Ҳ����false
	 */
	boolean deleteStudent(String name);

}