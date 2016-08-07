package main.java.com.esport.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.com.esport.dao.exception.DaoException;
import main.java.com.esport.dao.interfaces.UserDao;
import main.java.com.esport.dao.pool.mysql.MysqlConnectionPool;
import main.java.com.esport.dao.pool.mysql.MysqlConnectionPoolException;
import main.java.com.esport.domain.User;
import main.java.com.esport.domain.UserRole;

public class MysqlUserDao implements UserDao {

	private final static String ADD_USER = "INSERT INTO user " + "(email, password, role, name) "
			+ "VALUES (?, ?, ?, ?)";
	private final static String UPDATE_USER = "UPDATE user "
			+ "SET email = ?, password = ?, role = ?, name = ?, WHERE id = ?";
	private final static String DELETE_USER = "DELETE FROM user WHERE id = ?";
	private final static String GET_USER_BY_ID = "SELECT `id`, `email`, `password`, `role`, `name` FROM user WHERE id = ?";
	private final static String GET_ALL_USERS = "SELECT `id`, `email`, `password`, `role`, `name` FROM user";
	private final static String GET_USER_BY_EMAIL = "SELECT `id`, `email`, `password`, `role`, `name` FROM user WHERE email = ?";

	@Override
	public void addUser(User user) throws DaoException {
		MysqlConnectionPool mySQLConnectionPool = MysqlConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = mySQLConnectionPool.getConnection();
		} catch (InterruptedException | MysqlConnectionPoolException e) {
			throw new DaoException("Cannot get a connection from Connection Pool", e);
		}

		try {
			PreparedStatement statement = connection.prepareStatement(ADD_USER);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getRole().toString());
			statement.setString(4, user.getName());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Exception in DAO layer when adding user", e);
		} finally {
			try {
				mySQLConnectionPool.freeConnection(connection);
			} catch (SQLException | MysqlConnectionPoolException e) {
				throw new DaoException("Cannot free a connection from Connection Pool", e);
			}
		}

	}

	@Override
	public void updateUser(User user) throws DaoException {
		MysqlConnectionPool mySQLConnectionPool = MysqlConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = mySQLConnectionPool.getConnection();
		} catch (InterruptedException | MysqlConnectionPoolException e) {
			throw new DaoException("Cannot get a connection from Connection Pool", e);
		}

		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getRole().toString());
			statement.setString(4, user.getName());
			statement.setInt(5, user.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Exception in DAO layer when updating user", e);
		} finally {
			try {
				mySQLConnectionPool.freeConnection(connection);
			} catch (SQLException | MysqlConnectionPoolException e) {
				throw new DaoException("Cannot free a connection from Connection Pool", e);
			}
		}

	}

	@Override
	public void deleteUser(int id) throws DaoException {
		MysqlConnectionPool mySQLConnectionPool = MysqlConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = mySQLConnectionPool.getConnection();
		} catch (InterruptedException | MysqlConnectionPoolException e) {
			throw new DaoException("Cannot get a connection from Connection Pool", e);
		}

		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_USER);
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Exception in DAO layer when deleting user", e);
		} finally {
			try {
				mySQLConnectionPool.freeConnection(connection);
			} catch (SQLException | MysqlConnectionPoolException e) {
				throw new DaoException("Cannot free a connection from Connection Pool", e);
			}
		}

	}

	@Override
	public List<User> getAllUsers() throws DaoException {
		MysqlConnectionPool mySQLConnectionPool = MysqlConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = mySQLConnectionPool.getConnection();
		} catch (InterruptedException | MysqlConnectionPoolException e) {
			throw new DaoException("Cannot get a connection from Connection Pool", e);
		}

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

			List<User> allUsers = new ArrayList<>();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(UserRole.valueOf(resultSet.getString(4)));
				user.setName(resultSet.getString(5));

				allUsers.add(user);
			}
			return allUsers;
		} catch (SQLException e) {
			throw new DaoException("Exception in DAO layer when getting user", e);
		} finally {
			try {
				mySQLConnectionPool.freeConnection(connection);
			} catch (SQLException | MysqlConnectionPoolException e) {
				throw new DaoException("Cannot free a connection from Connection Pool", e);
			}
		}
	}

	@Override
	public User getUserById(int id) throws DaoException {
		MysqlConnectionPool mySQLConnectionPool = MysqlConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = mySQLConnectionPool.getConnection();
		} catch (InterruptedException | MysqlConnectionPoolException e) {
			throw new DaoException("Cannot get a connection from Connection Pool", e);
		}

		try {
			PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(UserRole.valueOf(resultSet.getString(4)));
				user.setName(resultSet.getString(5));
			}
			return user;
		} catch (SQLException e) {
			throw new DaoException("Exception in DAO layer when getting user", e);
		} finally {
			try {
				mySQLConnectionPool.freeConnection(connection);
			} catch (SQLException | MysqlConnectionPoolException e) {
				throw new DaoException("Cannot free a connection from Connection Pool", e);
			}
		}
	}

	@Override
	public User getUserByEmail(String email) throws DaoException {
		MysqlConnectionPool mySQLConnectionPool = MysqlConnectionPool.getInstance();
		Connection connection = null;
		try {
			connection = mySQLConnectionPool.getConnection();
		} catch (InterruptedException | MysqlConnectionPoolException e) {
			throw new DaoException("Cannot get a connection from Connection Pool", e);
		}

		try {
			PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(UserRole.valueOf(resultSet.getString(4)));
				user.setName(resultSet.getString(5));
			}
			return user;
		} catch (SQLException e) {
			throw new DaoException("Exception in DAO layer when getting user", e);
		} finally {
			try {
				mySQLConnectionPool.freeConnection(connection);
			} catch (SQLException | MysqlConnectionPoolException e) {
				throw new DaoException("Cannot free a connection from Connection Pool", e);
			}
		}
	}

}
