package interactive.tour.app.model;

public class Hotspot {
    private String name;
    private double x, y; // Relative position on the map (0-1)
    private String audioPath;
    private String videoPath;
    private String description;

    public Hotspot(String name, double x, double y, String audioPath, String videoPath, String description) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.audioPath = audioPath;
        this.videoPath = videoPath;
        this.description = description;
    }

    public String getName() { return name; }
    public double getX() { return x; }
    public double getY() { return y; }
    public String getAudioPath() { return audioPath; }
    public String getVideoPath() { return videoPath; }
    public String getDescription() { return description; }
} 