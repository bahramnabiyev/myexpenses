import beans.User;
import dao.UserDAOImpl;
import dao.UserDAOInter;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        UserDAOInter userDao = new UserDAOImpl();
        userDao.insert(new User("SARKHAN","RASULLU"));

    }
}
