/*
 * 개발자들이 개발할때 다룰법한 데이터베이스 다루는 툴을 "데이터베이스 접속 클라이언트"라고 부른다.
 * 이러한 툴들중 꽤유명한 제품은 Toad ...등이있다.
 * 
 * Toad는 DBeaver 에 비해 기능이 막강하지만 유료이기에, 우리는 DBeaver를 사용한다.
 */

package day1116.dbclient;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.mariadb.jdbc.internal.com.read.dao.ColumnNameMap;



public class DBMSClientApp extends JFrame {
   JPanel p_west; //서쪽패널
   Choice ch_users; //유저명이 출력될 초이스 컴포넌트
   JPasswordField t_pass; //비밀번호 텍스트필드
   JButton bt_login; //접속버튼
   
   JPanel p_center; //그리드가 적용될 패널
   JTable t_tables; //유저의 테이블정보를 출력할 Jtable
   JTable t_seq; //유저의 시퀀스를 출력할 Jtable
   JScrollPane s1, s2;
   
   String driver = "oracle.jdbc.driver.OracleDriver";
   String url = "jdbc:oracle:thin:@localhost:1521:XE";
   String user = "system";
   String password = "1234";
   Connection con;
   
   //이차원배열
   Vector tableList = new Vector(); //이안에 또다른 벡터가 들어갈 예정임 이차원배열처럼..
   Vector<String> columnList = new Vector<String>();
   
   public DBMSClientApp() {
      p_west = new JPanel();
      ch_users = new Choice();
      t_pass = new JPasswordField();
      bt_login = new JButton("접속");

		p_center = new JPanel();
		p_center.setLayout(new GridLayout(2, 1));

		t_tables = new JTable(tableList, columnList);
		// 여기 초기벡터값을 넣어라
		// 사이즈가 0이지만 추후 메서드호출시 벡터의 크기가 변경될 것이고
		// JTable을 새로고침하면 된다
		t_seq = new JTable();
		s1 = new JScrollPane(t_tables);
		s2 = new JScrollPane(t_seq);

		// 스타일
		p_west.setPreferredSize(new Dimension(150, 350));
		ch_users.setPreferredSize(new Dimension(145, 30));
		t_pass.setPreferredSize(new Dimension(145, 30));
		bt_login.setPreferredSize(new Dimension(145, 30));

		// 조립
		p_west.add(ch_users);
		p_west.add(t_pass);
		p_west.add(bt_login);
      p_center.add(s1);
      p_center.add(s2);
      
      add(p_west, BorderLayout.WEST);
      add(p_center);
      
      
      setSize(700, 350);
      setVisible(true);
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            disConnect();
            System.exit(0);
         }
      });
      
      bt_login.addActionListener((e)->{
         login();
      });
//      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      connect();//호출
      getUserList();//유저목록 구해오기
      
     
   }
   
   
   //오라클접속
   public void connect() {
      try {
         Class.forName(driver);
         con = DriverManager.getConnection(url,user,password);
         if(con == null) {
            JOptionPane.showMessageDialog(this, user+"접속에 실패하였습니다.");
         }else {
            this.setTitle(user+"계정으로 접속 중...");//프레임 제목에 성공적으로 출력
         }
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   //유저목록 가져오기
   public void getUserList() {
      //pstmt와 resultset은 소모품이므로 매 쿼리문마다 1개씩 대응됨.
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select username from dba_users order by username asc";
      
      try {
         //쿼리문준비하기
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         
         //이제 rs에 있는 유저정보를 choice에 출력
         while(rs.next()) {
            ch_users.add(rs.getString("username"));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if(rs != null) {
            try {
               rs.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if(pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
   }
   
   //현재 접속유저의 테이블 목록 가져오기
   public void getTableList() {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   String sql="select table_name,tablespace_name from user_tables";
	   
	   try {
		pstmt = con.prepareStatement(sql);//쿼리준비
		rs = pstmt.executeQuery();//쿼리실행및 결과집합 받기
		
		
		
		while(rs.next()) {
			Vector vec = new Vector();//tableList벡터에 담겨질 벡터
			vec.add(rs.getNString("table_name"));
			vec.add(rs.getNString("tablespqce_name"));
			
			tableList.add(vec);//멤버변수 벡터에 벡터를 담았으니 , 이제 멤버변수 벡터는 이차원벡터가 됨
		}
		
		//완성된 이차워벡터를 JTable에 반영해야 함, 생성자의 인수로 넣어야함!!
		t_tables.updateUI();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
         if(rs != null) {
            try {
               rs.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if(pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
	   
   }
   
   public void login() {
      //현재 유지되고 있는 접속 객체인 Connection을 끊고, 다른유저로 접속을 시도한다!
      disConnect();
      user = ch_users.getSelectedItem();
      password = new String(t_pass.getPassword());
      connect();
      getTableList();
      System.out.println("보유한 테이블"+tableList.size());
   }
   
   //오라클 접속끊기
   public void disConnect() {
      if(con != null) {
         try {
            con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
   
   public static void main(String[] args) {
      new DBMSClientApp();
   }
   
}
