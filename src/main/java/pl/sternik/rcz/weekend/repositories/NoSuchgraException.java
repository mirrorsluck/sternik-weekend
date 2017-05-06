package pl.sternik.rcz.weekend.repositories;

public class NoSuchgraException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchgraException(String string) {
		super(string);
	}

	public NoSuchgraException() {
	}


}
