package SWING2;

import java.sql.*;

public class PersonServerManager 
{
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	String url;
	String user;
	String pw;
	
	PersonServerManager()
	{
		conn = null;
		stmt = null;
		rs = null;

		url = "jdbc:mysql://127.0.0.1:3306/personcollector";

		user = "root";
		pw = "mafa5256";
	}
	
	public ResultSet getAllPerson()
	{
		String sql = "select * from inperson;";
		
		try
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return  rs;
		}
		catch(Exception e)
		{
			System.out.println("전체 데이터 가지고 오는 중 오류 발생 " + e);
			return null;
		}
		
	}
	
	public void putPerson(String name, String gender, int age)
	{
		String sql = String.format("insert into inperson(name, gender, age) value (\"%s\", \"%s\", \"%d\")", name, gender, age) ;
		try
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("인간을 넣는 중 오류 발생 " + e);
		}
		finally
		{
			close();
		}
	}
	
	public void excutionPerson(int number)
	{
		// 서버에서 이름에 해당하는 데이터를 찾아서 삭제 하기 전에 방출한 목록에 넣고 삭제
		
		String sql = String.format("select * from inperson where number=\"%d\"", number) ;
		try
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			outPerson(rs);
		}
		catch(Exception e)
		{
			System.out.println("인간을 넣는 중 오류 발생 " + e);
		}
		finally
		{
			close();
		}
		
		
		
	}
	
	// 방출된 사람을 확인하기 위한 데이터
	public void outPerson(ResultSet result)
	{
		
		int number = -1;
		String name = "이름없음";
		String gender= "특이";
		int age = 0 ;
		
		try {
			while(result.next())
			{
				number = rs.getInt(1);
				name = rs.getString(2);
				gender = rs.getString(3);
				age = rs.getInt(4);
			}
		} catch (SQLException e1) {
			System.out.println("방출 데이터 가져오는 중 오류 발생" + e1);
		}
		
		String sql = String.format("insert into outperson(name, gender, age) value (\"%s\", \"%s\", %d);", name, gender, age) ;
		try
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("인간 방출 중 오류 발생 " + e);
		}
		finally
		{
			close();
		}
		
		deletePerson(number);
	}
	
	public void deletePerson(int number)
	{
		String sql = String.format("delete from inperson where number=\"%d\"", number) ;
		try
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("인간 제거 중 오류 발생 " + e);
		}
		finally
		{
			close();
		}
	}
	
	public void close()
	{
		try
		{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		catch(Exception e)
		{
			System.out.println("연결 해제 중 오류 발생 : " + e);
		}
	}
	
}
