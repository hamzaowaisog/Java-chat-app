package chat.app.event;

public class PublicEvent {

    private static PublicEvent instance;
    private EventImageView eventImageView;
    private EventChat eventChat;
    private EventLogin eventlogin;
    private EventMain eventmain;
    private EventMenuLeft eventMenuLeft;
    private EventInsertMessage insertmessage;
    private EventFetchMessage fetchmessage;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }

    public void addEventImageView(EventImageView event) {
        this.eventImageView = event;
    }

    public EventImageView getEventImageView() {
        return eventImageView;
    }
    
    public void addEventChat(EventChat event){
        this.eventChat = event;
    }
    public EventChat getEventChat(){
        return eventChat;
    }
    public void addEventLogin(EventLogin event){
        this.eventlogin = event;
    }
    public EventLogin getEventLogin(){
        return eventlogin;
    }
    public void addEventMain(EventMain event){
        this.eventmain = event;
    }
    public EventMain getEventMain(){
        return eventmain;
    }
    
    public void addEventMenuLeft (EventMenuLeft event){
        this.eventMenuLeft = event;
    }
    
    public EventMenuLeft getEventMenuLeft(){
        return eventMenuLeft;
    }

    public EventInsertMessage getInsertmessage() {
        return insertmessage;
    }

    public void setInsertmessage(EventInsertMessage insertmessage) {
        this.insertmessage = insertmessage;
    }

    public EventFetchMessage getFetchmessage() {
        return fetchmessage;
    }

    public void setFetchmessage(EventFetchMessage fetchmessage) {
        this.fetchmessage = fetchmessage;
    }
    
    
}
