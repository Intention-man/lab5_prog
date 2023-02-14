package classes;

public class FormField {
    private final int key;
    private final String expectedType;

    private final boolean isNessersary;
    private final String label;

    public FormField(int key, String expectedType, boolean isNessersary, String label) {
        this.key = key;
        this.expectedType = expectedType;
        this.isNessersary = isNessersary;
        this.label = label;
    }

    public int getKey() {
        return key;
    }

    public String getExpectedType() {
        return expectedType;
    }

    public boolean getIsNessersary() {return isNessersary;}
    public String getLabel() {
        return label;
    }
}
