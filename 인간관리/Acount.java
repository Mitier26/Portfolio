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
		conn = null;	// ȸ�� ���� Ŀ�ؼ�
		stmt = null;	// SQL Query���� ������ �� ��ü
		rs = null;	// ������� ������ ��ý
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
			System.out.println("������ ���� �߻� : " + e);
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
			System.out.println("������ ������ ���� �߻� : " + e);
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
			System.out.println("������ ���� �� ���� �߻� : " + e);
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
			System.out.println("���� ���� �� ���� �߻� : " + e);
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
			System.out.println("�̸��� �ҷ����� �� ���� �߻� : " + e);
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
			System.out.println("��ȭ��ȣ �ҷ����� �� ���� �߻� : " + e);
			return "";
		}
		finally
		{
			close();
		}
	}
	
	public void getData()
	{
		// �̰��� ������ �ִ� ���� ���� ������ ���� ��.
		String sql = "select id, pw, email, phone from admin;";
		
		// �Էµ� ���� Ȯ�� �ϱ� ���ؼ��� 
		// id �� �˻��ϰ� pw �� �´��� Ȯ�� �ؾ� �Ѵ�.
		
		try // try/catch �� ���׾��Ѵ�.
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// �����͸� set �� ������ �Դ�.
			// �Էµ� ���� ������ �ִ� ���� ������ �α��� ����
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
			
			System.out.println("���� �� ���� �߻� " + e);
		}
		finally
		{
			close();
		}
	}// getData
	
	// �ش� ���̵� �˻��� ������ ��, ������ ����
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
	
	
	// ���� ���� �ִ� �� Ȯ�� �ϴ°�.
	public boolean check(String str)
	{
		boolean result = false;
		try 
		{
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(str);
			
			// ���� �ִٸ� �� ���ٸ� ����
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
			System.out.println("���� �߻� : " + e);
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
			System.out.println("���� �߻� : " + e);
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
			System.out.println("���� �߻� : " + e);
			return null;
		}
		
	}
}
