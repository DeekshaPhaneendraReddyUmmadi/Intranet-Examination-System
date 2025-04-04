package in.ac.ksrmce.config.admin_config;

public class AdminEntity {
	private int id;
	private String user_name;
	private String password;

	public AdminEntity(String user_name, String password) {
		super();
		this.user_name = user_name;
		this.password = password;
	}

	public AdminEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AdminEntity [id=" + id + ", user_name=" + user_name + ", password=" + password + "]";
	}

}