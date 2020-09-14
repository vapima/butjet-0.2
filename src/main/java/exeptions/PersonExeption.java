package exeptions;

public class PersonExeption extends Exception {
    public PersonExeption(String message) {
        super(message);
        System.out.println(this.toString());
    }

    public String toStringWithSTack() {
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement s : this.getStackTrace()) {
            stringBuilder.append(" /" + s.toString());
        }
        return super.toString() + " STACKTRACE=>" + stringBuilder;
    }
}
