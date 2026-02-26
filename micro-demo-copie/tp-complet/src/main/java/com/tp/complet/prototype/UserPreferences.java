package com.tp.complet.prototype;

public class UserPreferences implements Cloneable {
    private String preference1;
    private String preference2;

    public UserPreferences(String preference1, String preference2) {
        this.preference1 = preference1;
        this.preference2 = preference2;
    }

    @Override
    public UserPreferences clone() {
        try {
            UserPreferences cloned = (UserPreferences) super.clone();
            cloned.preference1 = this.preference1;
            cloned.preference2 = this.preference2;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning should be supported", e);
        }
    }
}
