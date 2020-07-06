package fr.dreregon.discordems.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SystemConfiguration implements Configuration{
    @Getter @Setter
    private List<String> tokens;
    @Getter @Setter
    private String language;

    private final File CONFIG_FILE = new File(Core.CONFIG_DIR.getAbsolutePath()+File.separatorChar+"DISCORD-EMS.json");
    private final Logger logger = LoggerFactory.getLogger(SystemConfiguration.class);



    public SystemConfiguration(List<String> tokens, String language){
        this.tokens=tokens;
        this.language=language;
    }

    public SystemConfiguration(){}

    public boolean save() {
        try {
            objectMapper.writeValue(CONFIG_FILE, this);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public SystemConfiguration get() {
        try {
            return objectMapper.readValue(CONFIG_FILE, this.getClass());
        } catch (IOException e) {
            logger.error("IO Error : "+e.getMessage());
            return null;
        } catch (NullPointerException npe){
            logger.error("Null : "+npe.getMessage());
            return null;
        }
    }

    public boolean exists() {
        return CONFIG_FILE.exists();
    }

    public void reset() {
        try {
            objectMapper.writeValue(CONFIG_FILE, new SystemConfiguration(new ArrayList<>(), "en"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
