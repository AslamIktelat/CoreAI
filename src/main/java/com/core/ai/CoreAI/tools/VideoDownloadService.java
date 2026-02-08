package com.core.ai.CoreAI.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


@Component("VideoFetcher")
public class VideoDownloadService implements MCPTool{

    @Tool(name = "video_fetcher", description = "Fetches a video from a URL and saves it to the host")
    public String videoFetcher(@ToolParam(description = "This is the url to the video to download") String url) {

        try  {
            Process process2 = new ProcessBuilder("yt-dlp",url).directory(new File("Downloads/")).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process2.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process2.waitFor();
            return "Video saved to Downloads";
        }
        catch  (Exception e) {
            System.out.println("Failed to fetch the video :: "+ e.getMessage());
            return "Failed to fetch the video :: "+ e.getMessage();

        }


    }
}
