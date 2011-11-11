package model;

public enum QuestionStatus {
	OPEN("open"), ANSWERED("answered"), FINALIZED("finalized");
	
	private final String key;

	QuestionStatus(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
