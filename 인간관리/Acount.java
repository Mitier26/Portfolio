package SWING2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Acount 
{
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	String url;
	String user;
	String pw;
	
	Acount()
	{
		conn = null;	// 회선 연결 커넥션
		stmt = null;	// SQL Query문을 전달해 줄 객체
		rs = null;	// 결과값을 저장할 객첵
		//61.251.255.88
		url = "jdbc:mysql://127.0.0.1:3306/personcollector";
		
		user = "root";
		pw = "mafa5256";
	}
	
	public void InputData(String inputId, String inputPw, String inputEmail, String inputPhone)
	{
		String sql = String.format("insert into admin(id, pw, email, phone) value(\"%s\",\"%s\",\"%s\",\"%s\");", inputId, inputPw, inputEmail, inputPhone); ;
		
		try 
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			stmt.execute(sql);
		} 
		catch (SQLException e) 
		{
			System.out.println("전송중 오류 발생 : " + e);
		}
		finally
		{
			close();
		}
	}
	
	public void updateData(String currentId, String inputId, String inputPw, String inputEmail, String inputPhone)
	{
		String sql = String.format("update admin set id = \'%s\', pw = \'%s\', email = \'%s\', phone = \'%s\' where id=\'%s\';",
				inputId, inputPw, inputEmail, inputPhone, currentId);
		System.out.println(sql);
		
		try
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("데이터 수정중 오류 발생 : " + e);
		}
		finally
		{
			close();
		}
	}
	
	public void deleteData(String id)
	{
		String sql = String.format("delete from admin where id=\"%s\";", id);
		
		try
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("데이터 삭제 중 오류 발생 : " + e);
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
	
	public String getEmail(String id)
	{
		String result = "";
		String sql = String.format("select email from admin where id=\"%s\";", id);
		try
		{
			
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				result = rs.getString(1);
			}
			
			return result;
		}
		catch(Exception e)
		{
			System.out.println("이메일 불러오는 중 오류 발생 : " + e);
			return "";
		}
		finally
		{
			close();
		}
	}
	
	public String getPhone(String id)
	{
		String result = "";
		String sql = String.format("select phone from admin where id=\"%s\";", id);
		try
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				result = rs.getString(1);
			}
			
			return result;
		}
		catch(Exception e)
		{
			System.out.println("전화번호 불러오는 중 오류 발생 : " + e);
			return "";
		}
		finally
		{
			close();
		}
	}
	
	public void getData()
	{
		// 이것은 서버에 있는 것을 전부 가지고 오는 것.
		String sql = "select id, pw, email, phone from admin;";
		
		// 입력된 값만 확인 하기 위해서는 
		// id 를 검색하고 pw 가 맞는지 확인 해야 한다.
		
		try // try/catch 로 감쌓야한다.
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// 데이터를 set 에 가지고 왔다.
			// 입력된 값과 서버에 있는 값이 같으면 로그인 성공
			while(rs.next())
			{
				for(int i = 1 ; i <= 4 ; i++)
				{
					System.out.print(rs.getString(i) + "\t");
				}
			}
		} 
		catch (SQLException e)
		{
			
			System.out.println("연결 중 오류 발생 " + e);
		}
		finally
		{
			close();
		}
	}// getData
	
	// 해당 아이디를 검색해 있으면 참, 없으면 거짓
	public boolean idCheck(String id)
	{
		String sql = "select id from admin where id="+"\""+id+"\";";
		
		return check(sql);
	}//idCheck
	
	public boolean pwCheck(String pw)
	{
		String sql = "select pw from admin where pw="+"\""+pw+"\";";
		
		return check(sql);
	}//pwCheck
	
	
	// 같은 것이 있는 지 확인 하는것.
	public boolean check(String str)
	{
		boolean result = false;
		try 
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(str);
			
			// 값이 있다면 참 없다면 거짓
			if(rs.next())
			{
				result = true;
			}
			else
			{
				result = false;
			}
		}
		catch (SQLException e) 
		{
			System.out.println("오류 발생 : " + e);
			result = false;
		}
		return result;
	}
	
	public ResultSet getAllResult()
	{
		String sql = "select num, id, email, phone, date from admin;";
		
		try 
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			return rs;
		} 
		catch (SQLException e) 
		{
			System.out.println("오류 발생 : " + e);
			return null;
		}
		
	}
	
	public ResultSet getResult(String sql)
	{
		try 
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			return rs;
		} 
		catch (SQLException e) 
		{
			System.out.println("오류 발생 : " + e);
			return null;
		}
		
	}
}
