package org.jhotdraw.geom;

import org.testng.annotations.Test;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BezierPathTest {


    @Test
    public void testAddNode() {
        BezierPath path = new BezierPath();
        Point2D.Double point = new Point2D.Double(1.0, 2.0);
        path.add(point);
        assertEquals(path.size(), 1);
        assertEquals(path.get(0).getControlPoint(0), point);
    }

    @Test
    public void testMoveTo() {
        BezierPath path = new BezierPath();
        path.moveTo(1.0, 2.0);
        assertEquals(path.size(), 1);
        assertEquals(path.get(0).getControlPoint(0), new Point2D.Double(1.0, 2.0));
    }

    @Test
    public void testLineTo() {
        BezierPath path = new BezierPath();
        path.moveTo(1.0, 2.0);
        path.lineTo(3.0, 4.0);
        assertEquals(path.size(), 2);
        assertEquals(path.get(1).getControlPoint(0), new Point2D.Double(3.0, 4.0));
    }

    @Test
    public void testQuadTo() {
        BezierPath path = new BezierPath();
        path.moveTo(1.0, 2.0);
        path.quadTo(3.0, 4.0, 5.0, 6.0);
        assertEquals(path.size(), 2);
        assertEquals(path.get(1).getControlPoint(0), new Point2D.Double(5.0, 6.0));
        assertEquals(path.get(1).getControlPoint(1), new Point2D.Double(3.0, 4.0));
    }

    @Test
    public void testCurveTo() {
        BezierPath path = new BezierPath();
        path.moveTo(1.0, 2.0);
        path.curveTo(3.0, 4.0, 5.0, 6.0, 7.0, 8.0);
        assertEquals(path.size(), 2);
        assertEquals(path.get(1).getControlPoint(0), new Point2D.Double(7.0, 8.0));
        assertEquals(path.get(1).getControlPoint(1), new Point2D.Double(5.0, 6.0));
        assertEquals(path.get(0).getControlPoint(2), new Point2D.Double(3.0, 4.0));
    }

    @Test
    public void testToGeneralPath() {
        BezierPath path = new BezierPath();
        path.moveTo(1.0, 2.0);
        path.lineTo(3.0, 4.0);
        Path2D.Double generalPath = path.toGeneralPath();
        assertNotNull(generalPath);
        assertEquals(generalPath.getCurrentPoint(), new Point2D.Double(3.0, 4.0));
    }

}
