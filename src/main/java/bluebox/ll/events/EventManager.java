package bluebox.ll.events;

public class EventManager<T extends Event>{
    public EventManager(Class<T> eventType,int priority){
        this.eventType = eventType;
        this.priority = priority;
        init();
    }
    public EventManager(Class<T> eventType){
        this(eventType,0);
    }
    public final int priority;
    public final Class<T> eventType;
    private native void init();
    protected void onEvent(T event){
        
    }
}
