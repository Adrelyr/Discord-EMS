package fr.dreregon.discordems.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.logging.Logger;

public interface Configuration {
    final Logger logger = null;
    final ObjectMapper objectMapper = new ObjectMapper();
    public boolean save();
    public Object get();
    public boolean exists();
    public void reset();
}
