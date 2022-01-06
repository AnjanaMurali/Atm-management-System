package anjana;
import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Atm{
String accnumber;
int password;
double balance;
int i=1;
public void checkLogin(){
int i=1;
while(i<=3){
System.out.print("Please enter your card number:");
Scanner sc=new Scanner(System.in);
String accnumber=sc.nextLine();
System.out.print("Please enter your password:");
int password=sc.nextInt();
try{
Class.forName("com.mysql.jdbc.Driver");
Connection  conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
Statement st= conn.createStatement();
String sql = "select * from acc where accnumber='"+accnumber+"'and password="+password;
ResultSet rs = st.executeQuery(sql);
if(rs.next()){
accnumber= rs.getString(1);
password = rs.getInt(2);
balance = rs.getDouble(3);
loadSys(); }
rs.close();
st.close();
conn.close();
}
catch (Exception e){
System.out.println(e);
}
i++;
if( i == 3){
System.out.print("You have entered the wrong password three times, the card is locked");
}
}
}
public void saveDb(){
try{
Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
Statement st = conn.createStatement();

String sql="update acc set password ="+password+" where accnumber='"+accnumber+"'";
String sql1="update acc set balance ="+balance+" where accnumber='"+accnumber+"'";
st.executeUpdate (sql);
st.executeUpdate(sql1);
st.close();
conn.close();
}
catch (Exception e){
System.out.println(e);
}
}
public static void welcome(){
System.out.println("----------Welcome to Bank Of Wonders-----------");
}
public void loadSys(){
System.out.println("------------------------------------");
System.out.println("1:Check balance");
System.out.println("2:Deposit");
System.out.println("3:Withdrawal");
System.out.println("4:Change Password ");
System.out.println("5:Quit");
System.out.println("------------------------------------");
System.out.print("Please enter the corresponding number:");
Scanner sz=new Scanner(System.in);
int num=sz.nextInt();
switch(num){
case 1:
balcheck();
break;
case 2:
depcheck();
break;
case 3:
withdcheck();
break;
case 4:
passchange();
break;
case 5:
quit();
break;
default:
System.out.println("The number you entered is incorrect!");
loadSys();
}
}
public void balcheck(){
System.out.println("The balance: " +balance);
loadSys();
}
public void depcheck(){
System.out.print("Please enter deposit amount:");
Scanner ck=new Scanner(System.in);
double balance11=ck.nextDouble();
balance=balance+balance11;
saveDb();
System.out.println("The balance is: "+balance);
System.out.println("Please accept your card!");
loadSys();
}
public void withdcheck(){
System.out.print("Please enter the withdrawal amount:");
Scanner qk=new Scanner(System.in);
double withdraw=qk.nextDouble();
if(withdraw>balance){
System.out.println("Your balance is not enough!");
withdcheck();
}else{
balance=balance-withdraw;
saveDb();
System.out.println("The balance is: "+balance);
System.out.println("Please collect your cash!");
loadSys();
}
}
public void passchange(){
int cs = 0;
System.out.print("Please enter the original password:");
Scanner mm=new Scanner(System.in);
int pass1=mm.nextInt();
System.out.print("Please enter a new password:");
int pass2=mm.nextInt();
System.out.print("Please enter a new password again:");
int pass3=mm.nextInt();
if((password==pass1)&&(pass2==pass3)){
System.out.println("Change Password Successful!");
password=pass2;
saveDb();
loadSys();
}else{
if(cs<2){
System.out.print("The password you entered is incorrect!");
cs++;
passchange();
}else{
quit();
}
}
}
public void quit(){
System.out.print("----------Thank you for choosing Bank Of Wonders!--------------");
System.exit(1);
}
public static void main(String[] args) throws ClassNotFoundException {
Class.forName("com.mysql.jdbc.Driver");
Connection con;
try {
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
Statement st = con.createStatement();
System.out.println("CONNECTED TO DATABASE");
st .execute("create table acc(accnumber varchar(20),password int,balance double)");
st.executeUpdate("insert into acc values('124857523678',2548,1256)");
st.executeUpdate("insert into acc values('888255789645',2362,12000)");
st.executeUpdate("insert into acc values('546445982665',2886,1252)");
st.executeUpdate("insert into acc values('280006925065',2585,1286)");
st.executeUpdate("insert into acc values('257232254932',2542,18462)");
PreparedStatement k=con.prepareStatement("DELETE FROM acc WHERE password=2542");
k.execute();
ResultSet rs=st.executeQuery("SELECT * FROM acc");
rs=k.executeQuery("SELECT * FROM acc");
while(rs.next()) {

System.out.println("accnumber: " + rs.getString(1) +"  "+"password:  " + rs.getInt(2)+"  "+ "Balance : " + rs.getDouble(3));
}
}
catch (SQLException e) {
System.err.println(e);
}
checklogin1 aws= new checklogin1();
aws.welcome();
aws.checkLogin();
}
}
