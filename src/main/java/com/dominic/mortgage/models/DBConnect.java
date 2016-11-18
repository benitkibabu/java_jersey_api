package com.dominic.mortgage.models;
import com.dominic.mortgage.encription.Passwords;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


public class DBConnect {
	Connection conn;
	DBManager db;
	private static final String TAG = DBConnect.class.getSimpleName();

	public DBConnect() {
		db = new DBManager();
		conn = null;

		try {
			Class.forName("");
			conn = DriverManager.getConnection("",
					"", "");
		} catch (SQLException sqle) {
			//Log.e(TAG, "Connection exception " + sqle.getMessage());
		} catch (ClassNotFoundException e) {
			//Log.e(TAG, "Driver Class Exception " + e.getMessage());
		}
	}

	public HashMap<String, String> addUser(String email, String name,
			String password) {
		HashMap<String, String> user = new HashMap<String, String>();
		if (checkIfUserExist(email)) {
			return null;
		} else {
			String uid = UUID.randomUUID().toString();
			byte[] salt = Passwords.getNextSalt();
			Date created_at = new Date();

			boolean result = false;
			try {
				PreparedStatement st = conn
						.prepareStatement("INSERT INTO users(unique_id, name, email, "
								+ "encrypted_password, salt, created_at) "
								+ "VALUES(?,?,?,?,?,?)");
				st.setString(1, uid);
				st.setString(2, name);
				st.setString(3, email);
				st.setString(4, encryptedPassword(password, salt));
				st.setString(5, byteToString(salt));
				st.setDate(6, new java.sql.Date(System.currentTimeMillis()));

				result = st.execute();
				st.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//Log.e(TAG, e.getMessage());
			}

			if (!result) {
				user.put("name", name);
				user.put("email", email);
				user.put("uid", uid);
				user.put("created_at", "" + created_at);
			}

			return user;
		}
	}

	private String encryptedPassword(String password, byte[] salt) {
		String p = byteToString(Passwords.hash(password.toCharArray(), salt));
		return p;
	}

	public HashMap<String, String> getUserDetails(String email, String password) {
		HashMap<String, String> user = new HashMap<String, String>();
		String query = "SELECT * FROM " + "TABLE NAME"
				+ " WHERE email='" + email + "'";

		ResultSet rs = db.SELECT_QUERY(query, conn);
		//Log.d(TAG, "Un Pa" + email + " " + password);
		try {
			if (rs.next()) {
				if (Passwords.isExpectedPassword(password.toCharArray(),
						stringToByte(rs.getString("salt")),
						stringToByte(rs.getString("encrypted_password")))) {
					user.put("name", rs.getString("name"));
					user.put("email", rs.getString("email"));
					user.put("uid", rs.getString("unique_id"));
					user.put("created_at", rs.getString("created_at"));
				}
			}
			
			//Log.d(TAG, "Fetching user from HashMap: " + user.toString());
			conn.close();
		} catch (SQLException ex) {
			//Log.e(TAG, ex.getMessage());
		}
		// return user

		return user;
	}

	private boolean checkIfUserExist(String email) {
		String query = "SELECT * FROM " + "TABLE NAME"
				+ " WHERE email='" + email + "'";
		boolean result = false;
		ResultSet rs = db.SELECT_QUERY(query, conn);

		try {
			if (rs != null && rs.next()) {
				result = true;
			} else {
				result = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//Log.e(TAG, "checkUserExist " + e.getMessage());
		}
		return result;
	}

	public byte[] stringToByte(String input) {
		return Base64.decode(input, Base64.DEFAULT);
	}

	public String byteToString(byte[] input) {
		return Base64.encodeToString(input, Base64.DEFAULT);
	}
}
