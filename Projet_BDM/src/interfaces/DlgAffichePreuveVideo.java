/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.*;
import java.awt.event.*;
import javax.media.*;
import javax.swing.*;
/**
 *
 * @author ag092850
 */
public class DlgAffichePreuveVideo extends JFrame implements  ControllerListener
{
    private boolean closing = false;
    private Player player = null;
    private JPanel videoPanel = null;

    public DlgAffichePreuveVideo(String nomFilm)
    {
        super();
        this.setSize(500, 500);
        this.setLocation(200, 400);
        this.setTitle("Afficher une preuve vidéo");
        this.getContentPane().setLayout(new BorderLayout());
        this.addWindowListener(new WindowAdapter()
            {   
                public void windowClosing( WindowEvent we ) {  }
            }
        );
        if(nomFilm!=null)
            loadMovie(nomFilm);
    }

    private void loadMovie(String movieURL)
    {
        if(movieURL.indexOf(":")<3) 
            movieURL = "file:" + movieURL;
        try
        {   
            this.player = Manager.createPlayer(new MediaLocator(movieURL));
            this.player.addControllerListener(this); 
            this.player.realize();
        }
        catch (Exception e)
        {
            System.out.println("Error creating player");
            return;
        }
    }


    public void controllerUpdate(ControllerEvent ce)
    {  
        
        if(ce instanceof RealizeCompleteEvent)
        {   
            if(this.videoPanel==null)
            {   
                this.videoPanel = new JPanel();
                this.videoPanel.setLayout(new BorderLayout());
                this.getContentPane().add(this.videoPanel, BorderLayout.CENTER);
            }   
            else
                this.videoPanel.removeAll();
            Component vis = this.player.getVisualComponent();
            if(vis!=null)
            {   
                this.videoPanel.add(vis, BorderLayout.CENTER);
                this.videoPanel.setVisible(true);
                this.pack();
            }
            this.player.start();
        }
        else 
            if (ce instanceof EndOfMediaEvent)
            {
                if (this.player!=null)
                {   
                    this.player.setMediaTime(new Time(0));
                    if(this.player.getTargetState()<Player.Started)
                        this.player.prefetch();
                    this.player.start();
                }
            }
    }
}
