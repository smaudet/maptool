/*
 *  This software copyright by various authors including the RPTools.net
 *  development team, and licensed under the LGPL Version 3 or, at your
 *  option, any later version.
 *
 *  Portions of this software were originally covered under the Apache
 *  Software License, Version 1.1 or Version 2.0.
 *
 *  See the file LICENSE elsewhere in this distribution for license details.
 */

package net.rptools.maptool.client.tool.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import net.rptools.maptool.client.AppStyle;
import net.rptools.maptool.client.MapTool;
import net.rptools.maptool.client.tool.DefaultTool;
import net.rptools.maptool.client.ui.zone.ZoneOverlay;
import net.rptools.maptool.client.ui.zone.ZoneRenderer;
import net.rptools.maptool.language.I18N;
import net.rptools.maptool.model.Zone;


/**
 * Tool for filling in enclosed areas of topology
 */
public class FillTopologyTool extends DefaultTool implements ZoneOverlay {

	private static final long serialVersionUID = -2125841145363502135L;

	public FillTopologyTool() {
        try {
            setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("net/rptools/maptool/client/image/tool/top-blue-free.png"))));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    @Override
    public String getTooltip() {
        return "tool.filltopology.tooltip";
    }

	@Override
    public String getInstructions() {
    	return "tool.filltopology.instructions";
    }

	@Override
	public void mouseClicked(MouseEvent e) {

		if (!SwingUtilities.isLeftMouseButton(e)) {
			return;
		}
		
//		ZonePoint zp = new ScreenPoint(e.getX(), e.getY()).convertToZone(renderer);
		
//		Area holeArea = renderer.getTopologyAreaData().getHoleAt(zp.x, zp.y); 
//		if (holeArea == null) {
//			MapTool.showError("Must click in an enclosed area");
//			return;
//		}
//		
//        renderer.getZone().addTopology(holeArea);
//        MapTool.serverCommand().addTopology(renderer.getZone().getId(), holeArea);
        
        renderer.repaint();
	}
	
    public void paintOverlay(ZoneRenderer renderer, Graphics2D g2) {
    	Graphics2D g = (Graphics2D)g2.create();
    	
    	Color oldColor = g.getColor();

    	if (MapTool.getPlayer().isGM()) {
	    	Zone zone = renderer.getZone();
	    	Area topology = zone.getTopology();
	
	    	double scale = renderer.getScale();
	    	g.translate(renderer.getViewOffsetX(), renderer.getViewOffsetY());
	    	g.scale(scale, scale);

	    	g.setColor(AppStyle.topologyColor);

	    	g.fill(topology);
	
	    	g.setColor(oldColor);
    	}
    }
    
}
