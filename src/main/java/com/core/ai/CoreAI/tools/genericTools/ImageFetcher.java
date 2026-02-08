package com.core.ai.CoreAI.tools.genericTools;


import com.core.ai.CoreAI.tools.MCPTool;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@Component("ImageFetcher")
public class ImageFetcher implements MCPTool {


    @Tool(name = "image_fetcher", description = "Fetches an image from a URL and saves it to the host")
    public String fetchImageUrl(@ToolParam(description = "This is the url to the image to download") String imageUrl,
                                @ToolParam(description = "description of the image") String description,
                                @ToolParam(description = "name of the image file that will be saved on the host") String imgname)  {
        try {
            // Create URL object
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.connect();
            createDir("Downloads");
            // Get input stream
            try (InputStream in = connection.getInputStream()) {
                // Save file to a temp folder

                //String fileName = Paths.get(url.getPath()).getFileName().toString();
                File outputFile = new File( "Downloads/" +imgname);
                try (FileOutputStream out = new FileOutputStream(outputFile)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
                addtoRAG(outputFile,description,imgname);
                return "Image saved to: " + outputFile.getAbsolutePath();
            }

        } catch (Exception e) {

            return "Failed to fetch image: " + e.getMessage();
        }
    }


    private boolean createDir(String path)
    {
        try {
            File dir= new File((path));
            return dir.createNewFile();
        }
        catch (IOException ioException)
        {

            return false;
        }

    }
    private void addtoRAG(File outputFile,String description,String filename)
    {
        System.out.println("Adding to RAG :: file name :: "+filename+" des :: "+description );
    }
}

