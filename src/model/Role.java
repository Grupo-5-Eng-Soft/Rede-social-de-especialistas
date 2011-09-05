package model;

public enum Role {
	ADMIN("admin"), USER("user");

	private final String key;

	Role(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
