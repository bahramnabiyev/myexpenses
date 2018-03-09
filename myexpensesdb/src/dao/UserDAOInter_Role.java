package dao;

import beans.User;
import java.util.List;

public interface UserDAOInter {

    public abstract int insert(User user);

    public abstract User find(int id);

    public abstract List<User> findAll(String name, String surname);

    public abstract boolean delete(int id);

    public abstract boolean update(int id, User user);
    
    public abstract List<User> selectAll();
    
    
    //About Roles
    
    public abstract List<User> selectRoles();
    
    public abstract int insertRole(User role);
    
    public abstract boolean deleteRole(int id);
    
    public abstract boolean updateRole (int id, User role);
    
}
