package com.center.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoThumbTakerTools {
	
    //ffmpeg解压路径
    private static String ffmpegApp;
    
    
    public VideoThumbTakerTools(String ffmpegApp)
    {
        this.ffmpegApp = ffmpegApp;
    }
    
	
    @SuppressWarnings("static-access")
	public static void main(String[] args)
    {
    	VideoThumbTakerTools videoThumbTakerTools = new VideoThumbTakerTools("D:\\ffmpeg\\bin\\ffmpeg.exe");
        try
        {
        	
        	//获取视频信息
        	VideoInfo videoInfo = new VideoInfo();
        	videoInfo = videoThumbTakerTools.getInfo("D:\\test.mp4");
        			
        	//获取第一帧
        	videoThumbTakerTools.getThumb("D:\\test.mp4", "D:\\thumbTest.png", 800, 600, 0, 0, 1);
        	
        
        	//获取最后一帧
        	videoThumbTakerTools.getThumb("D:\\test.mp4", "D:\\thumbTest.png", 800, 600, videoInfo.getHours(), videoInfo.getMinutes(),
                    videoInfo.getSeconds() - 0.2f);
        	
        	
            System.out.println("over");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	
	/*
	 * 获取视频基本信息
	 *  @param videoFilename:视频路径
	 */
	public static VideoInfo getInfo(String videoFilename) throws IOException,
    InterruptedException
	{
		VideoInfo videoInfo = new VideoInfo();
		int  width = 0;
		int  heigt = 0;
		int  hours = 0;
		int  minutes = 0;
		float  seconds = 0;
		
		
		String tmpFile = videoFilename + ".tmp.png";
		ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y",
		        "-i", videoFilename, "-vframes", "1", "-ss", "0:0:0", "-an",
		        "-vcodec", "png", "-f", "rawvideo", "-s", "100*100", tmpFile);
		
		Process process = processBuilder.start();
		
		InputStream stderr = process.getErrorStream();
		InputStreamReader isr = new InputStreamReader(stderr);
		BufferedReader br = new BufferedReader(isr);
		String line;
		
		//打印 sb，获取更多信息。 如 bitrate、width、heigt
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null)
		{
		    sb.append(line);
		}
		
		new File(tmpFile).delete();
		
//		System.out.println("video info:\n" + sb);
		
		Pattern pattern = Pattern.compile("Duration: (.*?),");
		Matcher matcher = pattern.matcher(sb);
		
		if (matcher.find())
		{
		    String time = matcher.group(1);		    
			String[] parts = time.split(":");
			hours = Integer.parseInt(parts[0]);
		    minutes = Integer.parseInt(parts[1]);
		    seconds = Float.parseFloat(parts[2]);
		    
		    videoInfo.setHours(hours);
		    videoInfo.setMinutes(minutes);
		    videoInfo.setSeconds(seconds);
		}
		
		pattern = Pattern.compile("w:\\d+ h:\\d+");
		matcher = pattern.matcher(sb);
		
		if (matcher.find())
		{
		    String wh = matcher.group();
		    //w:100 h:100
		    String[] strs = wh.split("\\s+");
		    if(strs != null && strs.length == 2)
		    {
		       width = Integer.parseInt(strs[0].split(":")[1]);
		       heigt = Integer.parseInt(strs[1].split(":")[1]);
		       
				videoInfo.setWidth(width);	
				videoInfo.setHeigt(heigt);
		    }
		}
		
		process.waitFor();
		if(br != null)
		    br.close();
		if(isr != null)
		    isr.close();
		if(stderr != null)
		    stderr.close();

		return videoInfo;
	}
		

    /****
     * 获取指定时间内的封面图片
     * @param videoFilename:视频路径
     * @param thumbFilename:图片保存路径
     * @param width:图片长
     * @param height:图片宽
     * @param hour:指定时
     * @param min:指定分
     * @param sec:指定秒
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getThumb(String videoFilename, String thumbFilename, int width,
            int height, int hour, int min, float sec) throws IOException,
            InterruptedException
    {
        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y",
                "-i", videoFilename, "-vframes", "1", "-ss", hour + ":" + min
                        + ":" + sec, "-f", "mjpeg", "-s", width + "*" + height,
                "-an", thumbFilename);

        Process process = processBuilder.start();

        InputStream stderr = process.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null)
            ;
        process.waitFor();
        
        if(br != null)
            br.close();
        if(isr != null)
            isr.close();
        if(stderr != null)
            stderr.close();
    }

    
}
