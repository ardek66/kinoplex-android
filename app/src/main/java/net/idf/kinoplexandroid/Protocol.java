package net.idf.kinoplexandroid;

public class Protocol {
    String kind = null;
    String name = null;
    String password = null;
    String joName = null;
    String role = null;
    String reason = null;

    public static Protocol auth(String name, String password) {
        Protocol p = new Protocol();

        p.kind = "Auth";
        p.name = name;
        p.password = password;

        return p;
    }
}
