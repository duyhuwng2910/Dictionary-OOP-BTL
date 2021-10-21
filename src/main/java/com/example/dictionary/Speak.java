package com.example.dictionary;

import com.voicerss.tts.*;
import com.voicerss.tts.AudioFormat;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Speak {
    public static void mp3(String word) throws Exception {
        VoiceProvider tts = new VoiceProvider("fca817cf99144816bd7d018db145db14");
        VoiceParameters params = new VoiceParameters(word, Languages.English_UnitedStates);
        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);
        byte[] voice = tts.speech(params);
        FileOutputStream fos = new FileOutputStream("voice.mp3");
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();
    }

    public static void speak() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner scanner = new Scanner(System.in);
        File file = new File("voice.mp3");
        AudioInputStream audio = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }
}
