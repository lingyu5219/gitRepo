package com.center.utils;

public class VideoInfo {
	
    //ffmpeg解压路径
    private String ffmpegApp;
    
    //视频时
    private int hours;
    
    //视频分
    private int minutes;
    
    //视频秒
    private float seconds;
    
    //视频width
    private int width;
    
    //视频height
    private int heigt;
    

    public VideoInfo() {}
    
    public VideoInfo(String ffmpegApp)
    {
        this.ffmpegApp = ffmpegApp;
    }
    
    public String toString()
    {
        return "time: " + hours + ":" + minutes + ":" + seconds + ", width = " + width + ", height= " + heigt;
    }
    
    public String getFfmpegApp()
    {
        return ffmpegApp;
    }

    public void setFfmpegApp(String ffmpegApp)
    {
        this.ffmpegApp = ffmpegApp;
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public float getSeconds()
    {
        return seconds;
    }

    public void setSeconds(float seconds)
    {
        this.seconds = seconds;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeigt()
    {
        return heigt;
    }

    public void setHeigt(int heigt)
    {
        this.heigt = heigt;
    }

}
