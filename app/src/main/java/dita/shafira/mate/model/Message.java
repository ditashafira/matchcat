package dita.shafira.mate.model;

public class Message {
    private String text; // message body
//    private User user; // data of the user that sent this message
    private boolean belongsToCurrentUser; // is this message sent by us?

    public Message(String text, boolean belongsToCurrentUser) {
        this.text = text;
//        this.user = user;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public String getText() {
        return text;
    }

//    public User getUser() {
//        return user;
//    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}

