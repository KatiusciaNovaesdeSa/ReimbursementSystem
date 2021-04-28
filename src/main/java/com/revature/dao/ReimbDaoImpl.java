package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.model.Login;
import com.revature.model.Reimbursement;
import com.revature.model.Role;
import com.revature.model.Status;
import com.revature.model.Type;
import com.revature.model.User;
import com.revature.util.DBConnection;
import com.revature.util.Log;
import com.revature.util.ReimConnection;

import java.util.List;

import org.postgresql.core.ConnectionFactory;

import java.util.ArrayList;
 
public class ReimbDaoImpl {
	

	Connection connection = ReimConnection.getConnection();

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

 
/*
 * add reimburseRequest (Employee)
 * view reimburse requests owned by employee showing their approval status (Employee)
 * View pending requests (Finance)
 * approve pending request
 * deny pending request	
 */

public boolean addReimbursementRequest(Reimbursement request) {
	String sql = "insert into ers_reimbursement "
			+ "(reimb_amount, reimb_submitted, reimb_description, "
			+ "reimb_author, reimb_status_id, reimb_type_id) "
			+ "values (?, ?, ?, ?, ?, ?);";

	PreparedStatement preparedStatement;
	
//	Connection connection = DBConnection.createConnection();  BEFORE
	
	Connection connection = ReimConnection.getConnection();
	
	//log.info("Creating a new account");
	
	
	try {
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDouble(1, request.getAmount());
		//uses current system time as Timestamp input
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		preparedStatement.setTimestamp(2, request.getSubmitted());
		preparedStatement.setString(3, request.getDescription());
		preparedStatement.setInt(4, request.getAuthor().getId());
		preparedStatement.setInt(5, request.getStatus().getId());
		preparedStatement.setInt(6, request.getType().getId());
		preparedStatement.execute();
		
		int generatedID = -1;
		Statement stmt = connection.createStatement();
		
		connection.close();
		return true;
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}


//view reimburse requests owned by employee
public List<Reimbursement> viewReimbursementRequests(User user){
	//try(Connection connection = DBConnection.createConnection()) {  BEFORE
	try(Connection connection = ReimConnection.getConnection()) {
		
		String command = "SELECT * FROM ers_reimbursement where reimb_author = ?";
		PreparedStatement statement = connection.prepareStatement(command);
		
		statement.setInt(1, user.getId());
		ResultSet result = statement.executeQuery();

		List<Reimbursement> reimbursmentList = new ArrayList<>();
		mapReimbursements(result, reimbursmentList);
		/*
		 * while(result.next()) { System.out.println("next Result  in DB " +
		 * result.getInt("reimb_id")); reimbursmentList.add(new Reimbursement(
		 * result.getInt("reimb_id"), result.getDouble("reimb_amount"),
		 * result.getTimestamp("reimb_submitted"),
		 * result.getTimestamp("reimb_resolved"),
		 * getUser(result.getInt("reimb_author")),
		 * result.getString("reimb_description"),
		 * getStatus(result.getInt("reimb_status")),
		 * getType(result.getInt("reimb_type")) // result.getString("user_first_name");
		 * // result.getString("user_last_name"); // result.getString("user_email"); ));
		 * }
		 */
		//int id, double amount, Timestamp submitted, Timestamp resolved, User author, Status status, Type type
		return reimbursmentList;
	} catch (SQLException e) {
		Log.logger.warn("Exception selecting all Reimbursment", e);
	} 
	return new ArrayList<>();

}

public boolean updateReimbursementStatus(User financePerson, Reimbursement request)  {
	// fetch the reimbursement with same id
	
	// if found, update the status to Approved, resolver and resolved  and save 
	String sql = "UPDATE ers_reimbursement SET reimb_resolver = ?, reimb_status_id = ? WHERE reimb_id = ?";

	PreparedStatement preparedStatement;
	int result = -1;
	try {	
		//Connection connection = DBConnection.createConnection();  BEFORE
		Connection connection = ReimConnection.getConnection();
		
		preparedStatement = connection.prepareStatement(sql);
	    

		preparedStatement.setInt(1, financePerson.getId());
		preparedStatement.setInt(2, request.getStatus().getId());
		preparedStatement.setInt(3, request.getId());
	
		result = preparedStatement.executeUpdate();
		
	} catch (SQLException e) {
		Log.logger.warn("Exception selecting all Reimbursment", e);
	} 
	return result == 1 ? true : false;
}


public List<Reimbursement> viewAllReimbursementRequests(Status status){
	String sql = "SELECT * FROM ers_reimbursement where reimb_status_id = ?";

  PreparedStatement preparedStatement;
	
//	Connection connection = DBConnection.createConnection(); BEFORE
  Connection connection = ReimConnection.getConnection();
	
	//log.info("Creating a new account");
	List<Reimbursement> reimbs = new ArrayList<>();
	
	try {
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, status.getId());
		ResultSet rs = preparedStatement.executeQuery();	    
	    mapReimbursements(rs, reimbs);
		//Log.logger.warn("Exception selecting all Reimbursment", e);
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	return reimbs;
		
}

public List<Reimbursement> viewAllUsersReimbursementRequests(){
	String sql = "SELECT * FROM ers_reimbursement";

  PreparedStatement preparedStatement;
	
//	Connection connection = DBConnection.createConnection(); BEFORE
  Connection connection = ReimConnection.getConnection();
	
	//log.info("Creating a new account");
	List<Reimbursement> reimbs = new ArrayList<>();
	
	try {
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();	    
	    mapReimbursements(rs, reimbs);
		//Log.logger.warn("Exception selecting all Reimbursment", e);
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	return reimbs;
		
}



private User getUser(int userId) throws SQLException{
	//Connection connection = DBConnection.createConnection();  BEFORE
	Connection connection = ReimConnection.getConnection();

    String sql =    "SELECT ers_user_id, " +
                        "ers_username, " +
                        "user_first_name, " +
                        "user_last_name, " +
                        "user_email, " +
                        "user_role_id, " +
                        "user_role " +
                    "FROM ers_user " +
                        "JOIN ers_user_roles " +
                            "ON user_role_id = ers_user_role_id " +
                    "WHERE ers_user_id = ? ";

    PreparedStatement stmt = connection.prepareStatement(sql);
    

    stmt.setInt(1, userId);
    
    ResultSet rs = stmt.executeQuery();

    List<User> users = new ArrayList<>();
    mapUsers(rs, users);
    return users.get(0);
}


public User getUserByName(String name) {
    List<User> users = new ArrayList<>();
	for(User user: users) {
		if(user.getUsername().equals(name)) {
			return user;
		}
	}
	return null;
}


/*
public List<Reimbursement> findByAuthorId(int author){
	try(Connection connection = DBConnection.createConnection()){
		String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY R_ID DESC";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		List<Reimbursement> reimbursementList = new ArrayList<>();
		
		while(((ResultSet) preparedStatement).next()) {
			Reimbursement r = new Reimbursement();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, request.getAmount());
			//uses current system time as Timestamp input
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			preparedStatement.setTimestamp(2, author.getSubmitted());
			preparedStatement.setString(3, author.getDescription());
			preparedStatement.setInt(4, author.getAuthor().getId());
			preparedStatement.setInt(5, author.getStatus().getId());
			preparedStatement.setInt(6, author.getType().getId());
		}			
	} catch (SQLException e) {
		e.printStackTrace();
	}		
	return reimbursementList;;
}
*/

List<Reimbursement> viewReimbursementsByUserId(int userId) throws SQLException {
    return viewReimbursements("userId", userId);
}

public List<Reimbursement> viewReimbursements(String sqlCondition, int id) throws SQLException {
    return viewReimbursements(sqlCondition, Integer.toString(id));
}

List<Reimbursement> viewReimbursements(String sqlCondition, String condition) throws SQLException {
//	Connection connection = DBConnection.createConnection(); BEFORE
	Connection connection = ReimConnection.getConnection();
    List<Reimbursement> result = new ArrayList<>();

    String sql = "SELECT " +
            "r.reimb_id, " +
            "r.reimb_amount, " +
            "r.reimb_submitted, " +
            "r.reimb_resolved, " +
            "r.reimb_description, " +
            "r.reimb_author, " +
            "u.user_first_name, " +
            "u.user_last_name, " +
            "u.user_email, " +
            "r.reimb_resolver, " +
            "resolver.ers_user_id resolver_id, " +
            "resolver.user_first_name resolver_first_name, " +
            "resolver.user_last_name resolver_last_name, " +
            "resolver.user_email resolver_email, " +
            "s.reimb_status_id, reimb_status, " +
            "t.reimb_type_id, reimb_type " +
            "FROM ers_reimbursement r " +
            "LEFT JOIN ers_user u " +
            "ON r.reimb_author = u.ers_user_id " +
            "LEFT JOIN ers_reimbursement_status s " +
            "ON r.reimb_status_id = s.reimb_status_id " +
            "LEFT JOIN ers_reimbursement_type t " +
            "ON r.reimb_type_id = t.reimb_type_id " +
            "LEFT JOIN (" +
            "SELECT " +
            "ers_user_id, " +
            "user_first_name, " +
            "user_last_name, " +
            "user_email " +
            "FROM ers_user) resolver " +
            "ON r.reimb_resolver = resolver.ers_user_id ";

    PreparedStatement stmt;

    switch (sqlCondition) {
        case "userId": {
            sql += "WHERE r.reimb_author = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(condition));
            break;
        }
        case "reimbId": {
            sql += "WHERE r.reimb_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(condition));
            break;
        }
        //for viewAllReimbursements
        default:
            stmt = connection.prepareStatement(sql);
            break;
    }

    ResultSet rs = stmt.executeQuery();

    //maps ResultSet into Reimbursement object
    while (rs.next()) {
        //take in resultset data
        //reimb and author data
        int id = rs.getInt("reimb_id");
        double amount = rs.getDouble("reimb_amount");
        Timestamp submitted = rs.getTimestamp("reimb_submitted");
        Timestamp resolved = rs.getTimestamp("reimb_resolved");
        String description = rs.getString("reimb_description");
        int authorId = rs.getInt("reimb_author");
        String authorFirstName = rs.getString("user_first_name");
        String authorLastName = rs.getString("user_last_name");
        String authorEmail = rs.getString("user_email");

        //resolver data
        int resolverId = rs.getInt("reimb_resolver");
        String resolverFirstName = rs.getString("resolver_first_name");
        String resolverLastName = rs.getString("resolver_last_name");
        String resolverEmail = rs.getString("resolver_email");

        //reimb data
        int statusId = rs.getInt("reimb_status_id");
        String status = rs.getString("reimb_status");

        int typeId = rs.getInt("reimb_type_id");
        String type = rs.getString("reimb_type");

        //assign data
        //creates all objects to be used
        Reimbursement reimbursement = new Reimbursement();
        User author = new User();
        User resolver = new User();
        Status statusObj = new Status();
        Type typeObj = new Type();

        reimbursement.setId(id);
        reimbursement.setAmount(amount);
        reimbursement.setSubmitted(submitted);
        reimbursement.setResolved(resolved);
        reimbursement.setDescription(description);

        author.setId(authorId);
        author.setFirstName(authorFirstName);
        author.setLastName(authorLastName);
        author.setEmail(authorEmail);
        reimbursement.setAuthor(author); //sets User object to author field

        resolver.setId(resolverId);
        resolver.setFirstName(resolverFirstName);
        resolver.setLastName(resolverLastName);
        resolver.setEmail(resolverEmail);
        reimbursement.setResolver(resolver); //sets User object to resolver field

        statusObj.setId(statusId);
        statusObj.setStatus(status);
        reimbursement.setStatus(statusObj); //sets Status object to status field

        typeObj.setId(typeId);
        typeObj.setType(type);
        reimbursement.setType(typeObj); //sets Type object to type field

        result.add(reimbursement);
    }
    return result;
} // viewReimbursements



Role getRoleById(int id) throws SQLException {

//	Connection connection = DBConnection.createConnection();  BEFORE
	Connection connection = ReimConnection.getConnection();

    String sql = "SELECT ers_user_role_id, user_role " +
            "FROM ers_user_roles " +
            "WHERE ers_user_role_id = ?";
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setInt(1, id);
    ResultSet rs = stmt.executeQuery();

    //map ResultSet to User object
    rs.next();
    Role role = new Role(
            rs.getInt("ers_user_role_id"),
            rs.getString("user_role"));

    return role;
}

private Status getStatus(int statusId) throws SQLException{
	//Connection connection = DBConnection.createConnection();  BEFORE
	Connection connection = ReimConnection.getConnection();

    String sql = "SELECT reimb_status_id, " +
                        "reimb_status " +
                 "FROM ers_reimbursement_status where reimb_status_id = ?";

    PreparedStatement stmt = connection.prepareStatement(sql);
    
    stmt.setInt(1, statusId);

    ResultSet rs = stmt.executeQuery();
    
    if (rs.next()) {
        int id = rs.getInt("reimb_status_id");
        String status = rs.getString("reimb_status");

        //map ResultSet to User object
        Status statusObj = new Status(id, status);
        return statusObj;
    }
	return null;
}

List<Status> getStatuses() throws SQLException {

//	Connection connection = DBConnection.createConnection();   BEFORE
	Connection connection = ReimConnection.getConnection();

    String sql = "SELECT reimb_status_id, " +
                        "reimb_status " +
                 "FROM ers_reimbursement_status";

    PreparedStatement stmt = connection.prepareStatement(sql);

    ResultSet rs = stmt.executeQuery();

    List<Status> statuses = new ArrayList<>();

    while (rs.next()) {
        int id = rs.getInt("reimb_status_id");
        String status = rs.getString("reimb_status");

        //map ResultSet to User object
        Status statusObj = new Status(id, status);
        statuses.add(statusObj);
    }
    return statuses;
}

private Type getType(int typeId) throws SQLException {
//	Connection connection = DBConnection.createConnection();   BEFORE
	Connection connection = ReimConnection.getConnection();

    String sql = "SELECT reimb_type_id, " +
                        "reimb_type " +
                 "FROM ers_reimbursement_type where reimb_type_id = ?";

    PreparedStatement stmt = connection.prepareStatement(sql);
    
    stmt.setInt(1, typeId);

    ResultSet rs = stmt.executeQuery();
    
    if (rs.next()) {
        int id = rs.getInt("reimb_type_id");
        String type = rs.getString("reimb_type");

        //map ResultSet to User object
        Type typeObj = new Type(id, type);
        return typeObj;
    }
	return null;
}

List<Type> getTypes() throws SQLException {

//	Connection connection = DBConnection.createConnection();   BEFORE
	Connection connection = ReimConnection.getConnection();

    String sql = "SELECT reimb_type_id, " +
                        "reimb_type " +
                 "FROM ers_reimbursement_type";

    PreparedStatement stmt = connection.prepareStatement(sql);

    ResultSet rs = stmt.executeQuery();

    List<Type> types = new ArrayList<>();

    while (rs.next()) {
        int id = rs.getInt("reimb_type_id");
        String type = rs.getString("reimb_type");

        //map ResultSet to User object
        Type typeObj = new Type(id, type);
        types.add(typeObj);
    }
    return types;
}

public User getUserByUserName2(String username) {

	//Connection connection = DBConnection.createConnection();   BEFORE
	Connection connection = ReimConnection.getConnection();

    String sql =    "SELECT ers_user_id, " +
                        "ers_username, " +
                        "ers_password, " +
                        "user_first_name, " +
                        "user_last_name, " +
                        "user_email " +
                        "FROM ers_user WHERE ers_username = ?"; 
    try {
    PreparedStatement stmt = connection.prepareStatement(sql);
    

    stmt.setString(1, username);

    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        int id = rs.getInt("ers_user_id");
        String usern = rs.getString("ers_username");
        String password = rs.getString("ers_password");
        String lastName = rs.getString("user_first_name");
        String firstName = rs.getString("user_first_name");
        String email = rs.getString("user_email");
        User user = new User(id, usern, password, lastName, firstName, email, null);
        return user;
    }
    }catch(SQLException e)
    {
        e.printStackTrace();
    }

    return null;
}

public static void main(String[] args) throws SQLException {
	ReimbDaoImpl dao = new ReimbDaoImpl();
	System.out.println(dao.getUserByUserName2("kati"));
}

public User getUserByUserName(String username) throws SQLException {

	//Connection connection = DBConnection.createConnection();   BEFORE
	Connection connection = ReimConnection.getConnection();

    String sql =    "SELECT ers_user_id, " +
                        "ers_username, " +
                        "ers_password, " +
                        "user_first_name, " +
                        "user_last_name, " +
                        "user_email, " +
                        "user_role_id, " +
                        "user_role " +
                    "FROM ers_user " +
                        "JOIN ers_user_roles " +
                            "ON user_role_id = ers_user_role_id " +
                    "WHERE ers_username = ? " +
                        "OR user_email = ?";

    PreparedStatement stmt = connection.prepareStatement(sql);
    

    stmt.setString(1, username);
    stmt.setString(2, username);

    ResultSet rs = stmt.executeQuery();

    List<User> users = new ArrayList<>();
    mapUsers(rs, users);

    if (users == null) {
        return null;
    }
    return users.get(0);
}

private void mapUsers(ResultSet rs, List<User> users) throws SQLException {

    while (rs.next()) {
        int id = rs.getInt("ers_user_id");
        String username = rs.getString("ers_username");
        String lastName = rs.getString("user_first_name");
        String firstName = rs.getString("user_first_name");
        String email = rs.getString("user_email");
        int roleId = rs.getInt("user_role_id");
        String role = rs.getString("user_role");

        Role roleObj = new Role();
        roleObj.setId(roleId);
        roleObj.setRole(role);

        //map ResultSet to User object
        User user = new User(id, username, null, lastName, firstName, email, roleObj);
        users.add(user);
    }
}

private void mapReimbursements(ResultSet rs, List<Reimbursement> reimbursements) throws SQLException {
	//"(reimb_amount, reimb_submitted, reimb_description, "
	//		+ "reimb_author, reimb_status_id, reimb_type_id) "
	
    while (rs.next()) {
    	int reimbursement_id = rs.getInt("reimb_id");
        double reimb_amount = rs.getDouble("reimb_amount");
        Timestamp reimb_submitted = rs.getTimestamp("reimb_submitted");
        String reimb_description = rs.getString("reimb_description");
        int reimb_author = rs.getInt("reimb_author");
        int reimb_status_id = rs.getInt("reimb_status_id");
        int reimb_type_id = rs.getInt("reimb_type_id");

        //map ResultSet to User object
        //int id, double amount, Timestamp submitted, Timestamp resolved, User author, String description, Status status, Type type
        Reimbursement reimb = new Reimbursement(reimbursement_id, reimb_amount, reimb_submitted, null, getUser(reimb_author), 
        		reimb_description, getStatus(reimb_status_id), getType(reimb_type_id));
        reimbursements.add(reimb);
    }
}

void hashExistingPassword(User user)throws SQLException {

//	Connection connection = DBConnection.createConnection();  BEFORE
	Connection connection = ReimConnection.getConnection();

    String unhashedPass = user.getPassword();
    String hashedPass = BCrypt.hashpw(unhashedPass, BCrypt.gensalt());

    String sql = "UPDATE ers_user " +
                 "SET ers_password = ? " +
                 "WHERE ers_user_id = ?";

    PreparedStatement stmt = connection.prepareStatement(sql);

    stmt.setString(1, hashedPass);
    stmt.setInt(2, user.getId());

    stmt.executeQuery();
}





	
public String authenticateUser(Login loginBean)
{
    String userName = loginBean.getUserName();
    String password = loginBean.getPassword();
 
    //Connection con = null;  BEFORE

    Statement statement = null;
    ResultSet resultSet = null;
 
    String userNameDB = "";
    String passwordDB = "";
    Integer roleDB = 0;
    
   
    try
    {   	
     //   con = DBConnection.createConnection();    BEFORE
    	Connection connection = ReimConnection.getConnection();
        System.out.println(connection == null ? "No connection" : "There is connection");
        statement = connection.createStatement();
       // resultSet = statement.executeQuery("select ers_username,ers_password, ers_role_id from ers_user");

        resultSet = statement.executeQuery("select ers_username, ers_password, user_role_id from ers_user");
      //  ers_user.user_role_id
        
        while(resultSet.next())
        {
            userNameDB = resultSet.getString("ers_username");
            passwordDB = resultSet.getString("ers_password");
          //  roleDB = resultSet.getInt("ers_role_id");
            roleDB = resultSet.getInt("user_role_id");
            
 
            if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals(1))
            return "Admin_Role";
            else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals(2))
            return "User_Role";
        }
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
    return "Invalid user credentials";
}


}
