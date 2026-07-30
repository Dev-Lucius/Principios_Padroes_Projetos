package negocio;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SoundPlayerFrame extends JFrame {

    public SoundPlayerFrame() {
        setTitle("Sound in JFrame");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton playButton = new JButton("Play Sound");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace "yourSound.wav" with the path to your sound file
                playSound("/home/iapereira/git/ppp2026_1sem/01_strategy/codigo/simulador_patos/src/main/resources/quack.wav"); 
            }
        });

        JPanel panel = new JPanel();
        panel.add(playButton);
        add(panel);
    }

    // Method to play a sound file
    public static void playSound(String soundFilePath) {
        try {
            // Obtain an audio input stream from the file
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            
            // Obtain a clip (which acts like a basic media player)
            Clip clip = AudioSystem.getClip();
            
            // Open the audio stream with the clip
            clip.open(audioInputStream);
            
            // Start playback
            clip.start();
            
            // Optional: loop continuously
            // clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error playing sound: " + e.getMessage(), "Sound Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Ensure the GUI creation is done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SoundPlayerFrame().setVisible(true);
            }
        });
    }
}

