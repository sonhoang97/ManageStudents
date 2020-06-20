package ModelView;

public class UserViewModel {
    public UserViewModel() {
    }

    public UserViewModel(String username, String password) {
        this.Password = password;
        this.Username = username;
    }

    public String Username;
    public String Password;
}
