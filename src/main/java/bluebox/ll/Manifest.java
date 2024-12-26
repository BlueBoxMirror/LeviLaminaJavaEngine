package bluebox.ll;

import com.google.gson.annotations.Expose;

public class Manifest {
    @Expose public String name;
    @Expose public String entry;
    @Expose public String type;
    @Expose public String[] dependencies;
    @Expose public String version;
    @Expose public String description;
    @Expose public String author;
    @Expose public String entryClass;
}
