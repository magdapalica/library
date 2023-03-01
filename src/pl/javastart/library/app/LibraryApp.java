package pl.javastart.library.app;

public class LibraryApp {
    public static void main(String[] args) {
        final String appName = "Library v2.5";
        System.out.println(appName);
        LibraryControl control = new LibraryControl();
        control.controlLoop();
    }
}
