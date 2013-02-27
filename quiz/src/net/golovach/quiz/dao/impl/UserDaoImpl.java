package net.golovach.quiz.dao.impl;

import net.golovach.quiz.dao.UserDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.user.NotUniqueUserEmailException;
import net.golovach.quiz.dao.exception.user.NotUniqueUserLoginException;
import net.golovach.quiz.dao.impl.jdbc.ConnectionFactory;
import net.golovach.quiz.dao.impl.jdbc.ConnectionFactoryFactory;
import net.golovach.quiz.dao.impl.jdbc.JdbcUtils;
import net.golovach.quiz.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final ConnectionFactory factory = ConnectionFactoryFactory.newConnectionFactory();

    public static final String SELECT_ALL_SQL = "SELECT id, login, email FROM User";
    public static final String DELETE_BY_ID_SQL = "DELETE FROM User WHERE id = ?";
    public static final String INSERT_SQL = "INSERT INTO User (login, email) VALUES (?, ?)";
    public static final String SELECT_BY_LOGIN = "SELECT id FROM User WHERE login=?";
    public static final String SELECT_BY_EMAIL = "SELECT id FROM User WHERE email=?";

    @Override
    public List<User> selectAll() throws DaoSystemException {
        Connection conn = getConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(SELECT_ALL_SQL);
            List<User> result = new ArrayList<User>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String email = rs.getString("email");
                User user = new User(id);
                user.setLogin(login);
                user.setEmail(email);
                result.add(user);
            }
            conn.commit();
            return result;
        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(conn);
            throw new DaoSystemException("Can't execute SQL = '" + SELECT_ALL_SQL + "'", e);
        } finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(statement);
            JdbcUtils.closeQuietly(conn);
        }
    }

    private Connection getConnection() throws DaoSystemException {
        try {
            Connection result = factory.newConnection();
            result.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            result.setAutoCommit(false);
            return result;
        } catch (SQLException e) {
            throw new DaoSystemException("Exception during 'factory.newConnection()', factory = " + factory, e);
        }

    }

    @Override
    public int deleteById(int id) throws DaoSystemException {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(DELETE_BY_ID_SQL);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            conn.commit();
            return result;
        } catch (SQLException e) {
            JdbcUtils.rollbackQuietly(conn);
            throw new DaoSystemException("Can't execute SQL = '" + DELETE_BY_ID_SQL + "'", e);
        } finally {
            JdbcUtils.closeQuietly(ps);
            JdbcUtils.closeQuietly(conn);
        }
    }

    @Override
    public void insert(User user) throws NotUniqueUserLoginException, NotUniqueUserEmailException, DaoSystemException {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            JdbcUtils.rollbackQuietly(conn);
            String error = GetUniqueConstraintErrorCodeIfExist0(conn).toLowerCase();
            String errorMessage =ex.getSQLState().toLowerCase();
            if (errorMessage.equals(error)){
                if (ex.getMessage().lastIndexOf("email")!= -1){
                    throw new NotUniqueUserEmailException("Email '" + user.getEmail() + "' doubled");
                }

                if (ex.getMessage().lastIndexOf("login")!= -1){
                    throw new NotUniqueUserLoginException("Login '" + user.getLogin() + "' doubled");
                }
            }
            throw new DaoSystemException("Can't execute SQL = '" + INSERT_SQL + "'", ex);
        } finally {
            JdbcUtils.closeQuietly(ps);
            JdbcUtils.closeQuietly(conn);
        }
    }

    private String GetUniqueConstraintErrorCodeIfExist0(Connection conn)  {
        try{
            DatabaseMetaData dbMetaData =  conn.getMetaData();
            String error = "";
            int sqlStateType = dbMetaData.getSQLStateType();
            if (sqlStateType == DatabaseMetaData.sqlStateXOpen)
                error =  "S1009";
            if (sqlStateType == DatabaseMetaData.sqlStateSQL)
                error   = "23000";
            return error;

        }catch (SQLException e){

            return "";
        }
    }


    private boolean existWithLogin0(Connection conn, String login) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(SELECT_BY_LOGIN);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    private boolean existWithEmail0(Connection conn, String email) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(SELECT_BY_EMAIL);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }


}

