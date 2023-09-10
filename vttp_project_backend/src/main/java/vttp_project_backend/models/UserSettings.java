package vttp_project_backend.models;

public class UserSettings {
    private String userSettingsId;
    private String userDataId;
    private boolean emailNotif;
    private boolean teleNotif;

    public UserSettings() {
    }
    
    public UserSettings(String userSettingsId, String userDataId, boolean emailNotif, boolean teleNotif) {
        this.userSettingsId = userSettingsId;
        this.userDataId = userDataId;
        this.emailNotif = emailNotif;
        this.teleNotif = teleNotif;
    }

    public String getUserSettingsId() {
        return userSettingsId;
    }
    public void setUserSettingsId(String userSettingsId) {
        this.userSettingsId = userSettingsId;
    }
    public String getUserDataId() {
        return userDataId;
    }
    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }
    public boolean isEmailNotif() {
        return emailNotif;
    }
    public void setEmailNotif(boolean emailNotif) {
        this.emailNotif = emailNotif;
    }
    public boolean isTeleNotif() {
        return teleNotif;
    }
    public void setTeleNotif(boolean teleNotif) {
        this.teleNotif = teleNotif;
    }

    
}
