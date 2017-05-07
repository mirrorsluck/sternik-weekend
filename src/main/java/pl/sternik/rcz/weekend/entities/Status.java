package pl.sternik.rcz.weekend.entities;


public enum Status {
    
    NOWA("Nowa"), 
    DO_SPRZEDANIA("Na sprzedaż"),
    DUBLET("Dublet");
    
    public static final Status[] ALL = { NOWA, DO_SPRZEDANIA, DUBLET };
    
    private final String name;

    private Status(final String name) {
    	this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
